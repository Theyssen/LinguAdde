package linguadde.readerWriter;

import java.io.*;

public class XmlReader implements ReaderWriter {
    public String read(String filename) {
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String xmlStr, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "UTF-8"));
            writer.write(xmlStr);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
