package linguadde.exception;

import java.util.LinkedHashSet;
import java.util.Set;

public class ReaderWriterException extends Exception {

    public ReaderWriterException(String task, String fileName, String message) {
        super(String.format("Unable to %s %s: %s", task, fileName, message));
    }
}
