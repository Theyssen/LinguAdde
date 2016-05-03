package linguadde.readerWriter;

import linguadde.exception.ReaderWriterException;
import linguadde.model.LangData;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    private static String delimiter = ";";

    public static void setDelimiter(String delimiter) {
        CsvReader.delimiter = delimiter;
    }

    public static LangData read(String filename) throws ReaderWriterException {
        BufferedReader file;
        LangData data = null;
        String line = "";
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "UTF-8");
            file = new BufferedReader(isr);
            List<String> langList = new ArrayList<String>();
            List<String> valList = new ArrayList<String>();
            langList.addAll(Arrays.asList(file.readLine().toLowerCase().split(delimiter)));
            valList.addAll(langList.subList(1, langList.size()));
            data = new LangData(langList.get(0), valList);
            while ((line = file.readLine()) != null) {
                langList = new ArrayList<String>();
                valList = new ArrayList<String>();
                langList.addAll(Arrays.asList(line.split(delimiter)));
                if (langList.size() > 0) {
                    valList.addAll(langList.subList(1, langList.size()));
                    data.addData(langList.get(0), valList);
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new ReaderWriterException("read", filename, "Unsupported file encoding!");
        } catch (FileNotFoundException e) {
            throw new ReaderWriterException("read", filename, "File not found!");
        } catch (IOException e) {
            throw new ReaderWriterException("read", filename, "File not readable!");
        }
        return data;
    }
}
