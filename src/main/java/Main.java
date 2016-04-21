import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        LangData data = CsvReader.read(new File("").getAbsolutePath() + "/data/json-data.csv");
        JsonObject json = JsonReader.read(new File("").getAbsolutePath() + "/data/leakage.json");
        System.out.println(json);
        JsonReader.write(json, new File("").getAbsolutePath() + "/result/leakage.json");
    }
}
