package linguadde.readerWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;

public class JsonReader implements ReaderWriter {
    public String read(String filename) {
        try {
            return (new JsonParser()).parse(
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(filename), "UTF-8"))).toString();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String jsonStr, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "UTF-8"));
            JsonElement element = new JsonParser().parse(jsonStr);
            writer.write(gson.toJson(element));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
