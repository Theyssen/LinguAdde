package linguadde.readerWriter;

import linguadde.exception.ReaderWriterException;

import java.io.*;

public class XmlReader implements ReaderWriter {
    public String read(String filename) throws ReaderWriterException {
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filename), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new ReaderWriterException("read", filename, "File not found!");
        } catch (UnsupportedEncodingException e) {
            throw new ReaderWriterException("read", filename, "Unsupported file encoding!");
        } catch (IOException e) {
            throw new ReaderWriterException("read", filename, "File not readable!");
        }
    }

    public void write(String xmlStr, String filename) throws ReaderWriterException {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "UTF-8"));
            writer.write(xmlStr);
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
