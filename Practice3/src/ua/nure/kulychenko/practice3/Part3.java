package ua.nure.kulychenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static String convert(String input) {
        StringBuffer result = new StringBuffer();
        Matcher matcher = Pattern.compile("(?<=^|\\s)[a-zA-Z\\u0430-\\u044F\\u0410-\\u042F](?=[\\w\\u0410-\\u044F])", Pattern.MULTILINE).matcher(input);
        while (matcher.find()) {
            for (int i = 0; i < matcher.group().length(); i++) {
            	if(ch.length() >= 3) {

                    char ch = matcher.group().charAt(i);
		//
                    if (Character.isLowerCase(ch.charAt(0))) {
                        matcher.appendReplacement(result, matcher.group().toUpperCase());
                    } else {
                        matcher.appendReplacement(result, matcher.group().toLowerCase());

                    }
                }
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }
}
