import linguadde.TranslationAdder;
import linguadde.readerWriter.JsonReader;
import linguadde.readerWriter.ReaderWriter;
import linguadde.readerWriter.XmlReader;
import linguadde.replacer.JsonReplacer;
import linguadde.replacer.Replacer;
import linguadde.replacer.XmlReplacer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        handleArguments(args);

        String translationFile = args[0];
        String dataFolder = args[1];
        String resultFolder = dataFolder;

        TranslationAdder translationAdder = new TranslationAdder()
                .importTranslationData(translationFile);

        Files.walk(Paths.get(dataFolder)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                String fileString = filePath.toString();
                String fileEx = fileString.substring(fileString.lastIndexOf(".") + 1);
                String fileResultString = fileString.substring(Paths.get(dataFolder).toString().length());
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
                        System.out.println("Could not process " + fileString);
                }
                if (readerWriter != null && replacer != null) {
                    translationAdder
                            .readData(fileString, readerWriter)
                            .translate(replacer)
                            .writeData(Paths.get(resultFolder) + fileResultString);
                    System.out.println(fileResultString + " updated");
                }
            }
        });

        translationAdder.printErrors();

        System.out.println("\n\nSuccess: processing finished");
    }

    private static void handleArguments(String[] args) {
        if (args.length == 1 && args[0].equals("help")) {
            showHelp();
            System.exit(0);
        }
        if (args.length != 2) {
            System.out.println("Incorrect number of arguments!");
            showHelp();
            System.exit(1);
        }
        if (!Files.isRegularFile(Paths.get(args[0]))) {
            System.out.println("Argument " + args[0] + " is not a file!");
            showHelp();
            System.exit(1);
        }
        if (!Files.isDirectory(Paths.get(args[1]))) {
            System.out.println("Argument " + args[1] + " is not a directory!");
            showHelp();
            System.exit(1);
        }
    }

    private static void showHelp() {
        System.out.println(
                "\nHelp"
        );
    }
}
