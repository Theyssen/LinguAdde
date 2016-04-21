

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonReader {
    public static JsonObject read(String filename) {
        try {
            return (new JsonParser()).parse(new FileReader(filename)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
        /*JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public static void write(JsonObject json, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(gson.toJson(json));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
