import linguadde.TranslationAdder;
import linguadde.readerWriter.JsonReader;
import linguadde.readerWriter.XmlReader;
import linguadde.replacer.JsonReplacer;
import linguadde.replacer.XmlReplacer;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        TranslationAdder translationAdder = new TranslationAdder()
                .importTranslationData(new File("").getAbsolutePath() + "/data/translations.csv");

        translationAdder
                .readData(new File("").getAbsolutePath() + "/data/leakage.json", new JsonReader())
                .translate(new JsonReplacer())
                .writeData(new File("").getAbsolutePath() + "/result/leakage.json");

        translationAdder
                .readData(new File("").getAbsolutePath() + "/data/fr.xliff", new XmlReader())
                .translate(new XmlReplacer())
                .writeData(new File("").getAbsolutePath() + "/result/fr.xliff");

        translationAdder.printErrors();
    }
}
