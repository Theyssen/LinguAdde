package linguadde.exception;

import java.util.ArrayList;
import java.util.List;

public class KeyNotFoundException extends Exception {
    private static final List<String> keysNotFound = new ArrayList<String>();

    public KeyNotFoundException(String message) {
        super(message);
        keysNotFound.add(message);
    }

    public static List<String> getKeysNotFound() {
        return keysNotFound;
    }
}
