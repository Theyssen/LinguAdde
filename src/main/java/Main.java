import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        LangData data = CsvReader.read(new File("").getAbsolutePath() + "/data/json-data.csv");
        String jsonStr = JsonReader.read(new File("").getAbsolutePath() + "/data/leakage.json");
        jsonStr = Replacer.replaceJson(data, jsonStr);
        //System.out.println(jsonStr);
        JsonReader.write(jsonStr, new File("").getAbsolutePath() + "/result/leakage.json");
    }
}
