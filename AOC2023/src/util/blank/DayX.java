package util.blank;

import util.ReadFile;

import java.io.IOException;
import java.util.stream.Stream;


public class DayX {



    public static void main(String[] args) {
        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\dayX\\puzzle.txt";
        ReadFile fileReader = new ReadFile(filePath);

        // Puzzle 1
        try (Stream<String> linesStream = fileReader.getLinesStream()) {
            // whatever
        } catch (IOException e) {
            System.out.println("Error");
        }

        //Puzzle 2

    }

    // -----------------------------------------------------------------------------
}