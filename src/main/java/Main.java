import linguadde.TranslationAdder;
import linguadde.exception.ReaderWriterException;
import linguadde.readerWriter.JsonReader;
import linguadde.readerWriter.ReaderWriter;
import linguadde.readerWriter.XmlReader;
import linguadde.replacer.JsonReplacer;
import linguadde.replacer.Replacer;
import linguadde.replacer.XmlReplacer;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static Options options;
    private static String translationFile = null;
    private static String sourceFolder = null;
    private static String resultFolder = null;
    private static String delimiter = null;

    public static void main(String[] args) {
        handleArguments(args);
        try {
            TranslationAdder translationAdder = new TranslationAdder()
                    .importTranslationData(translationFile, delimiter);
            Files.walk(Paths.get(sourceFolder)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String fileString = filePath.toString();
                    String fileEx = fileString.substring(fileString.lastIndexOf(".") + 1);
                    String fileResultString = fileString.substring(Paths.get(sourceFolder).toString().length());
                    ReaderWriter readerWriter = null;
                    Replacer replacer = null;
                    switch (fileEx) {
                        case "json":
                            readerWriter = new JsonReader();
                            replacer = new JsonReplacer();
                            break;
                        case "xliff":
                            readerWriter = new XmlReader();
                            replacer = new XmlReplacer();
                            break;
                        default:
                            System.out.println("Could not process " + fileString + ": Unknown file type!");
                    }
                    if (readerWriter != null) {
                        try {
                            translationAdder
                                    .readData(fileString, readerWriter)
                                    .translate(replacer)
                                    .writeData(Paths.get(resultFolder) + fileResultString);
                        } catch (ReaderWriterException e) {
                            handleError(e);
                        }
                        System.out.println(fileResultString + " updated");
                    }
                }
            });
            translationAdder.printWarnings();

        } catch (ReaderWriterException e) {
            handleError(e);
        } catch (IOException e) {
            handleError(new Exception("Unable to read from " + sourceFolder));
        }
        System.out.println("\n\nSuccess: processing finished");
    }

    private static void handleArguments(String[] args) {
        options = new Options();
        options.addOption(Option.builder("c").longOpt("csv-file")
                .desc("CSV file with translationdata")
                .required()
                .hasArg().argName("file")
                .build());
        options.addOption(Option.builder("t").longOpt("target")
                .desc("Target translation files")
                .required()
                .hasArg().argName("folder")
                .build());
        options.addOption(Option.builder("d").longOpt("delimiter").argName("delimiter")
                .desc("CSV delimiter Default: ;")
                .hasArg()
                .build());

        if (args[0].equals("help")) {
            showHelp();
            System.exit(0);
        }
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            translationFile = cmd.getOptionValue("c");
            if (!Files.isRegularFile(Paths.get(translationFile))) {
                throw new Exception("Argument " + translationFile + " is not a file!");
            }
            sourceFolder = cmd.getOptionValue("t");
            resultFolder = sourceFolder;
            if (!Files.isDirectory(Paths.get(sourceFolder))) {
                throw new Exception("Argument " + sourceFolder + " is not a directory!");
            }
            if (cmd.hasOption("d")) {
                delimiter = cmd.getOptionValue("d");
            }
        } catch (ParseException e) {
            handleError(new Exception("Parsing failed. Reason: " + e.getMessage()));
        } catch (Exception e) {
            handleError(e);
        }
    }

    private static void handleError(Exception e) {
        System.out.println(e.getMessage());
        showHelp();
        System.exit(1);
    }

    private static void showHelp() {
        String jarName = new java.io.File(Main.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath()).getName();
        String header = "Add translations in data files\n\n";
        String footer = "\n";

        System.out.println("\n");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar " + jarName, header, options, footer, true );
    }
}
