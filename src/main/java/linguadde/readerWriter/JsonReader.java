package linguadde.readerWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import linguadde.exception.ReaderWriterException;

import java.io.*;

public class JsonReader implements ReaderWriter {
    public String read(String filename) throws ReaderWriterException {
        try {
            return (new JsonParser()).parse(
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(filename), "UTF-8"))).toString();
        } catch (FileNotFoundException e) {
            throw new ReaderWriterException("read", filename, "File not found!");
        } catch (UnsupportedEncodingException e) {
            throw new ReaderWriterException("read", filename, "Unsupported file encoding!");
        }
    }

    public void write(String jsonStr, String filename) throws ReaderWriterException {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "UTF-8"));
            JsonElement element = new JsonParser().parse(jsonStr);
            writer.write(gson.toJson(element));
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            throw new ReaderWriterException("write", filename, "Unsupported file encoding!");
        } catch (FileNotFoundException e) {
            throw new ReaderWriterException("write", filename, "File not found!");
        } catch (IOException e) {
            throw new ReaderWriterException("write", filename, "File not writable!");
        }
    }
}
