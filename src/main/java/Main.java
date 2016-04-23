import linguadde.TranslationAdder;
import linguadde.readerWriter.JsonReader;
import linguadde.replacer.JsonReplacer;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        new TranslationAdder()
                .importTranslationData(new File("").getAbsolutePath() + "/data/translations.csv")
                .readData(new File("").getAbsolutePath() + "/data/leakage.json", new JsonReader())
                .translate(new JsonReplacer())
                .writeData(new File("").getAbsolutePath() + "/result/leakage.json")
                .printErrors();


        /*String jsonStr = JsonReader.read(new File("").getAbsolutePath() + "/data/leakage.json");
        jsonStr = JsonReplacer.replaceJson(langData, jsonStr);
        JsonReader.write(jsonStr, new File("").getAbsolutePath() + "/result/leakage.json");

        String xmlStr = XmlReader.read(new File("").getAbsolutePath() + "/data/fr.xliff");
        xmlStr = JsonReplacer.replaceXml(langData, xmlStr);
        XmlReader.write(xmlStr, new File("").getAbsolutePath() + "/result/fr.xliff");*/
    }
}
