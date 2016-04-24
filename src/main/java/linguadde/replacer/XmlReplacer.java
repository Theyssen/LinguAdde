package linguadde.replacer;

import linguadde.exception.KeyNotFoundException;
import linguadde.exception.NoTranslationException;
import linguadde.model.LangData;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlReplacer implements Replacer {
    @Override
    public String replace(LangData data, String string) {
        Matcher matcher = Pattern.compile("source-language=\"" + data.getKeyLang() + "\"").matcher(string);
        if (!matcher.find()) {
            System.out.println("Could not find key language as source language!");
            return string;
        }

        matcher.usePattern(Pattern.compile("target-language=\"(.*?)\""));
        matcher.find();
        String targetLang = matcher.group(1);
        int targetLangId = data.getValueLangs().indexOf(targetLang);

        matcher.usePattern(Pattern.compile("((\\s*?)<source>(.*?)</source>)(\\s*?<target>.*?</target>)?"));
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            List<String> translations = data.getTranslations(matcher.group(3));
            if (translations != null) {
                if (translations.size() > targetLangId) {
                    matcher.appendReplacement(sb, String.format("$1$2<target>%s</target>}", translations.get(targetLangId)));
                } else {
                    new NoTranslationException(String.format("%s: %s", targetLang, matcher.group(3)));
                }
            } else {
                new KeyNotFoundException(matcher.group(3));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
