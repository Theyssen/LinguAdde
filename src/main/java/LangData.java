import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LangData {
    private String keyLang;
    private List<String> valueLangs;
    private Map<String, List<String>> langData;
    public LangData(String keyLang, List<String> valueLangs) {
        this.keyLang = keyLang;
        this.valueLangs = valueLangs;
        langData = new TreeMap<String, List<String>>();
    }

    public String getKeyLang() {
        return keyLang;
    }

    public List<String> getValueLangs() {
        return valueLangs;
    }

    public void addData(String key, List<String> values) {
        this.langData.put(key, values);
    }

    public List<String> getTranslations(String key) {
        return langData.get(key);
    }

}
