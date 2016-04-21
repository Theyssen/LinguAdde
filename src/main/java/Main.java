import java.io.File;

public class Main {
    public static void main(String[] args) {
        LangData data = CsvReader.read(new File("").getAbsolutePath() + "/data/json-data.csv");
    }
}
