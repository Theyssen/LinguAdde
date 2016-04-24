package linguadde.exception;

import java.util.LinkedHashSet;
import java.util.Set;

public class NoTranslationException extends Exception {
    private static final Set<String> messages = new LinkedHashSet<String>();

    public NoTranslationException(String message) {
        super(message);
        messages.add(message);
    }

    public static Set<String> getMessages() {
        return messages;
    }
}
