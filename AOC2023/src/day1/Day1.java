package day1;

import util.ReadFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


public class Day1 {

    private static final Map<String, Integer> spellNum = new HashMap<>();

    static {
        spellNum.put("one", 1);
        spellNum.put("two", 2);
        spellNum.put("three", 3);
        spellNum.put("four", 4);
        spellNum.put("five", 5);
        spellNum.put("six", 6);
        spellNum.put("seven", 7);
        spellNum.put("eight", 8);
        spellNum.put("nine", 9);
//        spellNum.put("zero", 0);
    }

    public static void main(String[] args) {
        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day1\\puzzle.txt";
        String filePath2 = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day1\\puzzle2.txt";
        ReadFile fileReader = new ReadFile(filePath);

        // Puzzle 1
        try (Stream<String> linesStream = fileReader.getLinesStream()) {
            int digitSums = linesStream
                    .mapToInt(line -> concatenateFirstLastDigits(line))
                    .sum();
            System.out.println("solution day 1.1: " + digitSums);
        } catch (IOException e) {
            System.out.println("Error");
        }

        //Puzzle 2

        fileReader = new ReadFile(filePath2);

        try (Stream<String> linesStream = fileReader.getLinesStream()) {
            long digitSums = linesStream
                    .mapToLong(line -> enhancedConcatenateFirstLastDigits(line))
                    .sum();
            System.out.println("solution day 1.2: " + digitSums);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    // -----------------------------------------------------------------------------
    // Puzzle 1.1
    private static int concatenateFirstLastDigits(String line) {
        Optional<String> digits = digits(line);
        return digits.map(Integer::parseInt)
                .map(number -> {
                    String numStr = String.valueOf(number);
                    return  Integer.parseInt(numStr.charAt(0) + "" + numStr.charAt(numStr.length() - 1));
                })
                .orElse(0);
    }

    private static Optional<String> digits(String line) {
        String digits = line.replaceAll("\\D", "");
        return Optional.of(digits).filter(s -> !s.isEmpty());
    }
    // -----------------------------------------------------------------------------
    // Puzzle 1.2
    private static long enhancedConcatenateFirstLastDigits(String line) {
        StringBuilder numeric = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (Character.isDigit(c)) {
                numeric.append(c);
            } else {
                String currentWord = extractCurr(line, i);
                Long spelledOutNumber = findMatch(currentWord.toLowerCase());

                if (spelledOutNumber != null) {
                    numeric.append(spelledOutNumber);
                }
            }
        }

        Optional<Long> numericValue = Optional.of(numeric.toString())
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong);

        return numericValue.map(number -> {
                    String numStr = String.valueOf(number);
                    return  Long.parseLong(numStr.charAt(0) + "" + numStr.charAt(numStr.length() - 1));
                })
                .orElse(Long.valueOf(0));
    }

    private static String extractCurr(String line, int fidx) {
        int lidex = fidx;
        while (lidex < line.length() && Character.isLetter(line.charAt(lidex))) {
            lidex++;
        }
        return line.substring(fidx, lidex);
    }

    private static Long findMatch(String str) {
        for (String key : spellNum.keySet()) {
            if (str.toLowerCase().startsWith(key)) {
                return Long.valueOf(spellNum.get(key));
            }
        }
        return null;
    }
    // -----------------------------------------------------------------------------
}