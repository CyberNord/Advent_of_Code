package day2;

import util.ReadFile;

import java.io.IOException;

public class Day2 {

    public static void main(String[] args) throws IOException {
        // Puzzle input
        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day2\\puzzle.txt";
        String input = new ReadFile(filePath).getContentAsString();

        // Bag configuration
        final int redCubes = 12;
        final int greenCubes = 13;
        final int blueCubes = 14;

        final String red = "red";
        final String green = "green";
        final String blue = "blue";

        int totalSum = 0;

        // Split input into games
        String[] games = input.split("\n");

        for(String subgame:games){
            String[] parts = subgame.split(": ");

            //get curr game Number
            int currGame = Integer.parseInt(parts[0].replaceAll("\\D+",""));
            boolean valid = true;
            System.out.println(currGame);

            // split to sequences
            String[] sequences = parts[1].split("; ");


            //test each sequence
            for(String sequence:sequences){
                if (!valid){break;}
                String[] cubes = sequence.split(", ");

                label:
                for(String cube:cubes) {
                    int num = Integer.parseInt(cube.replaceAll("\\D+", ""));
                    String color = cube.replaceAll("[^a-zA-Z]", "");

                    System.out.println("Num: " + num + " - Color: " + color);

                    switch (color) {
                        case red:
                            if (num > redCubes) {
                                valid = false;
                                break label;
                            }
                            break;
                        case green:
                            if (num > greenCubes) {
                                valid = false;
                                break label;
                            }
                            break;
                        case blue:
                            if (num > blueCubes) {
                                valid = false;
                                break label;
                            }
                            break;
                        default:
                            System.out.println("Error: " + color);
                            valid = false;
                            break label;
                    }
                }
            }

            if(valid){
                totalSum += currGame;
            }
        }

        System.out.println("totalSum = " + totalSum);

    }

}
