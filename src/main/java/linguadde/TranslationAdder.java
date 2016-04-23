package linguadde;

import linguadde.exception.KeyNotFoundException;
import linguadde.model.LangData;
import linguadde.readerWriter.CsvReader;
import linguadde.readerWriter.ReaderWriter;
import linguadde.replacer.Replacer;

import java.util.Set;

public class TranslationAdder {
    private LangData langData;
    private ReaderWriter readerWriter;
    private String stringData;
    private Replacer replacer;

    public TranslationAdder importTranslationData(String filename) {
        this.langData = CsvReader.read(filename);
        return this;
    }

    public TranslationAdder readData(String filename, ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
        this.stringData = readerWriter.read(filename);
        return this;
    }

    public TranslationAdder translate(Replacer replacer) {
        this.replacer = replacer;
        this.stringData = this.replacer.replace(this.langData, this.stringData);
        return this;
    }

    public TranslationAdder writeData(String filename) {
        this.readerWriter.write(this.stringData, filename);
        return this;
    }

    public TranslationAdder printErrors() {
        Set<String> keysNotFound = KeyNotFoundException.getKeysNotFound();
        if (keysNotFound.size() > 0) {
            System.out.println("\nKeys not found");
            System.out.println("--------------");
            for (String key : keysNotFound) {
                System.out.println(key);
            }
        }
        return this;
    }
}
