package linguadde.readerWriter;

import linguadde.exception.ReaderWriterException;

public interface ReaderWriter {
    String read(String filename) throws ReaderWriterException;

    void write(String string, String filename) throws ReaderWriterException;
}
