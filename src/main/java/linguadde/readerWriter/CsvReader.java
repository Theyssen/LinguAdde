package linguadde.readerWriter;

import linguadde.model.LangData;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    static final private String DELIMITER = ";";

    public static LangData read(String filename) {
        BufferedReader file;
        LangData data = null;
        String line = "";
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "UTF-8");
            file = new BufferedReader(isr);
            List<String> langList = new ArrayList<String>();
            List<String> valList = new ArrayList<String>();
            langList.addAll(Arrays.asList(file.readLine().toLowerCase().split(DELIMITER)));
            valList.addAll(langList.subList(1, langList.size()));
            data = new LangData(langList.get(0), valList);
            while ((line = file.readLine()) != null) {
                langList = new ArrayList<String>();
                valList = new ArrayList<String>();
                langList.addAll(Arrays.asList(line.split(DELIMITER)));
                valList.addAll(langList.subList(1, langList.size()));
                data.addData(langList.get(0), valList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


}
