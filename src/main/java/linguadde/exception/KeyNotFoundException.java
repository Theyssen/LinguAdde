package linguadde.exception;

import java.util.LinkedHashSet;
import java.util.Set;

public class KeyNotFoundException extends Exception {
    private static final Set<String> keysNotFound = new LinkedHashSet<String>();

    public KeyNotFoundException(String message) {
        super(message);
        keysNotFound.add(message);
    }

    public static Set<String> getKeysNotFound() {
        return keysNotFound;
    }
}
