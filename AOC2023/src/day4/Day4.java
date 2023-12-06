package day4;

import util.ReadFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day4 {


    public static void main(String[] args) throws IOException {
        // Puzzle input
//        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day4\\testpuzzle.txt";
        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day4\\puzzle.txt";

        int finalResult = day4PartOne(new ReadFile(filePath).getContentAsStringArray());
        System.out.println("Final Result: " + finalResult);

    }

    private static int day4PartOne(String[] fullCardSet) {

        int scoreSum = 0;

        for(String cardSet : fullCardSet){

            int cardNumber = extractCardNumber(cardSet);
            int[] winningNumbers = extractWinningNumbers(cardSet);
            int[] scratchedNumbers = extractScratchedNumbers(cardSet);


            int score = calculateScore(winningNumbers,scratchedNumbers);
            scoreSum += score;

            System.out.println("Card no#"+cardNumber +": " + score + " --> total Score: " + scoreSum);

        }
        return scoreSum;
    }

    private static int calculateScore(int[] winningNumbers, int[] scratchedNumbers) {

        int score = 0;

        for(int number: scratchedNumbers){
            if(Arrays.stream(winningNumbers).anyMatch(element -> element == number)){
                if(score == 0){
                    score++;
                }else{
                    score *= 2;
                }
            }
        }
        return score;
    }

    private static int extractCardNumber(String cardSet){

        Pattern pattern = Pattern.compile("Card (\\d+):");
        Matcher matcher = pattern.matcher(cardSet);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return -1;
    }

    private static int[] extractWinningNumbers(String cardSet){
        Pattern pattern = Pattern.compile(": (.+?) \\|");
        Matcher matcher = pattern.matcher(cardSet);

        if (matcher.find()) {
            String numbersString = matcher.group(1).trim();
            String[] numbersArray = numbersString.split("\\s+");
            return Arrays.stream(numbersArray).mapToInt(Integer::parseInt).toArray();
        }

        return new int[0];
    }

    private static int[] extractScratchedNumbers(String cardSet){
        Pattern pattern = Pattern.compile("\\|\\s*(.+)$");
        Matcher matcher = pattern.matcher(cardSet);

        if (matcher.find()) {
            String numbersString = matcher.group(1).trim();
            String[] numbersArray = numbersString.split("\\s+");
            return Arrays.stream(numbersArray).mapToInt(Integer::parseInt).toArray();
        }

        return new int[0];
    }




}