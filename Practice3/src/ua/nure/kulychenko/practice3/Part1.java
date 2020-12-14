package ua.nure.kulychenko.practice3;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part1 {
   private static final Pattern LOGIN_NAME_EMAIL = Pattern
              .compile("(([a-z\\u0410-\\u044F]+);([\\w\\u0410-\\u044F]+\\s[\\w\\u0410-\\u044F]+);(\\w+@(\\w+.\\w+)))", Pattern.MULTILINE);

      public static String convert1(String input) {
          return input.replaceAll(LOGIN_NAME_EMAIL.pattern(), "$2: $4").replaceFirst(".*"
                  + System.lineSeparator(), "");
      }
/*
      public static String convert2(String input) {
        return input.replaceAll(LOGIN_NAME_EMAIL.pattern(), "$3  (email: $4)").replaceFirst(".*"
                  + System.lineSeparator(), "");
      }
     */
      public static String convert2(String input) {
         String[] text = input.split(System.lineSeparator());
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < text.length; i++) {
                String[] a = text[i].split("[\\s\\;]");
                sb.append(a[2] + " " + a[1] + " (email: " + a[3] + ")" + System.lineSeparator());

            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

            public static String getInput(String fileName) {
                StringBuilder sb = new StringBuilder();
                try {
                    Scanner scanner = new Scanner(new File(fileName), "Cp1251");
                    while (scanner.hasNextLine()) {
                        sb.append(scanner.nextLine()).append(System.lineSeparator());
                    }
                    return sb.toString().trim();
                } catch (IOException ex) {
                    System.err.println("Part1 FileNotFound");
                }
                return sb.toString();
            }

      public static String convert3(String input) {
          Matcher matcher = LOGIN_NAME_EMAIL.matcher(input);
          Map<String, List<String>> listToMap = new LinkedHashMap<>();

          while (matcher.find()) {
              String email = matcher.group(5);
              List<String> list;
              if ((list = listToMap.get(email)) == null) {
                  list = new ArrayList<>();
                  listToMap.put(email, list);
              }
              list.add(matcher.group(2));
          }

          StringBuilder emails = new StringBuilder(input.length() / 2);


          for (Map.Entry<String, List<String>> key : listToMap.entrySet()) {
              emails = emails.append(key.getKey()).append(" ==> ");
              for (String name : key.getValue()) {
                  emails = emails.append(name).append(", ");
              }
              emails = emails.replace(emails.length() - 2, emails.length(), System.lineSeparator());
          }

          return emails.replace(emails.length() - 1,
                  emails.length(), "").toString();
      }
/*
      public static String convert4(String input) {
          Matcher matcher = LOGIN_NAME_EMAIL.matcher(input);
          StringBuffer sb = new StringBuffer();
          Random r = new SecureRandom();
          while (matcher.find()) {
              matcher.appendReplacement(sb, "$1;" + r.nextInt(9999));
          }
          return sb.toString().replaceFirst (".*",
              "Login;Name;Email;Password");
              */
      public static String convert4(String input) {
      Random rand = new Random();
      String[] text = input.split(System.lineSeparator());
      StringBuilder sb = new StringBuilder();
      sb.append(text[0] + ";Password" + System.lineSeparator());
      for (int i = 1; i < text.length; i++) {
          int random = rand.nextInt(9999);
          while (random < 1001) {
              random = rand.nextInt(9999);
          }
          sb.append(text[i] + ";" + random + System.lineSeparator());

      }
      return sb.toString();

      

      }

}



