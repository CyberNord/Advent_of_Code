package day1;

import util.ReadFile;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;


public class Day1 {

    public static void main(String[] args) {
        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day1\\puzzle.txt";
        ReadFile fileReader = new ReadFile(filePath);

        try (Stream<String> linesStream = fileReader.getLinesStream()) {
            int digitSums = linesStream
                    .mapToInt(line -> concatenateFirstLastDigits(line))
                    .sum();
            System.out.println("solution day 1.1: " + digitSums);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

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
}