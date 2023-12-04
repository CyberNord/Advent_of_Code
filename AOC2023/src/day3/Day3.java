package day3;

import util.ReadFile;

import java.io.IOException;
import java.sql.SQLOutput;


public class Day3 {



    public static void main(String[] args) throws IOException {
        // Puzzle input
//        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day3\\testpuzzle.txt";
        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day3\\puzzle.txt";
//        String filePath = "D:\\OneDrive\\EigeneProjekte\\KleineProjekte\\AOC\\AOC2023\\src\\day3\\test.txt";

        String[] input = new ReadFile(filePath).getContentAsStringArray();

        // Puzzle 1
        System.out.println("Sum machine-parts = "+ extractNumbers(input));


    }

    public static int extractNumbers(String[] arr) {

        int sum = 0;

        for (int row = 0; row < arr.length; row++) {
            String line = arr[row];
            int curL = 0;
            for (int col = 0; col < line.length(); col++) {
                char currentChar = line.charAt(col);
                if (Character.isDigit(currentChar)) {
                    curL++;
                }else{
                    if(curL > 0){
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i <= curL; i++){
                            sb.append(arr[row].charAt(col-i));
                        }
                        String num = sb.reverse().toString();
                        int firstPos = col-curL;
                        if(isMaschinepart(firstPos,row, curL,arr)){
//                            System.out.println(num + "--> parseInt= " + Integer.parseInt(num));
                            System.out.print("Old Sum=" +sum);
                            sum += Integer.parseInt(num);
                            System.out.println("--> add new value (" + num +") new Sum=" +sum +"\n");
                        }
                        curL =0;
                    }

                }
            }
        }

        return sum;
    }

    private static boolean isMaschinepart(int firstPosLine, int row , int length, String[] arr) {
        System.out.println(length + " ["+row +"|"+ firstPosLine + "]");

        // check row above
        int above = row -1;
        if (above >= 0) {
            int arrAboveFirst = Math.max(0, firstPosLine - 1);
            int arrAboveLast = Math.min(arr[above].length(), firstPosLine + length);

            for (int col = arrAboveFirst; col <= arrAboveLast; col++) {
                char symbol = arr[above].charAt(col);
                if (symbol != '.' && !Character.isDigit(symbol)) {
                    System.out.println("[Above] Found Symbol [" + symbol +"] at pos [" +above+ "|" +col+"] --> return True.");
                    return true;
                }
            }
        }


        // check front & end
        if(firstPosLine-1 >= 0){
            int posFrontCol = firstPosLine-1;
            char symbol = arr[row].charAt(posFrontCol);
            if (symbol != '.' && !Character.isDigit(symbol)) {

                System.out.println("[front] Found Symbol [" + symbol +"] at pos [" +row+ "|" +posFrontCol+"] --> return True.");
                return true;
            }
        }


        if(firstPosLine+length+1 < arr[row].length()){
            int posAfterCol = firstPosLine+length;
            char symbol = arr[row].charAt(posAfterCol);
            if (symbol != '.' && !Character.isDigit(symbol)) {
                System.out.println("[end] Found Symbol [" + symbol +"] at pos [" +row+ "|" +posAfterCol+"] --> return True.");
                return true;
            }
        }

        // check row beneath
        int beneath = row + 1;
        if (beneath < arr.length) {
            int arrBeneathFirst = Math.max(0, firstPosLine - 1);
            int arrBeneathLast = Math.min(arr[beneath].length(), firstPosLine + length);

            for (int col = arrBeneathFirst; col <= arrBeneathLast; col++) {
                char symbol = arr[beneath].charAt(col);
                if (symbol != '.' && !Character.isDigit(symbol)) {
                    System.out.println("[Beneath] Found Symbol [" + symbol +"] at pos [" +beneath+ "|" +col+"] --> return True.");
                    return true;
                }
            }
        }
        System.out.println("No symbol Found --> return False.\n");
        return false;
    }

    // -----------------------------------------------------------------------------
}