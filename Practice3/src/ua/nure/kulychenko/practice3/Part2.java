package ua.nure.kulychenko.practice3;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    private static final String REGEX = "([\\p{L}&&[^\\p{Nd}]]+)";

    private static final Pattern PATTERTN = Pattern.compile(REGEX);

    public static String convert(final String input) {
        Set<String> word = new LinkedHashSet<>();
        StringBuilder strMax = new StringBuilder();
        StringBuilder strMin = new StringBuilder();
        StringBuilder max = new StringBuilder("Max: ");
        StringBuilder min = new StringBuilder("Min: ");

        Matcher matcher = PATTERTN.matcher(input);
        while (matcher.find()) {
            word.add(matcher.group());
        }
        Iterator<String> it = word.iterator();
        StringBuilder temp = new StringBuilder();

        if (it.hasNext()) {
            strMax.append(it.next());
            strMin.append(strMax);
            while (it.hasNext()) {
                temp.append(it.next());
                if (temp.length() < strMin.length()) {
                    strMin.setLength(0);
                    strMin.append(temp);
                } else if (temp.length() > strMax.length()) {
                    strMax.setLength(0);
                    strMax.append(temp);
                }
                temp.setLength(0);
            }
        }
        for (String var : word) {
            if (var.length() == strMin.length()) {
                min.append(var + ", ");
            } else if (var.length() == strMax.length()) {
                max.append(var + ", ");
            }
        }
        min.setLength(min.length() - 2);
        max.setLength(max.length() - 2);
        return min.toString() + System.lineSeparator() + max.toString();


    }
}
