package linguadde.replacer;

import linguadde.exception.KeyNotFoundException;
import linguadde.model.LangData;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonReplacer implements Replacer {
    @Override
    public String replace(LangData data, String string) {
        Matcher matcher = Pattern.compile("(\"" + data.getKeyLang() + "\":\"(.*?)\").*?}").matcher(string);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            List<String> valueLangs = data.getValueLangs();
            List<String> translations = data.getTranslations(matcher.group(2));
            if (translations != null) {
                String transStr = "";
                for (int i = 0; i < valueLangs.size(); i++) {
                    transStr += String.format(",\"%s\":\"%s\"", valueLangs.get(i), translations.get(i));
                }
                matcher.appendReplacement(sb, String.format("$1%s}", transStr));
            } else {
                new KeyNotFoundException(matcher.group(2));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
