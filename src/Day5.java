import java.io.*;
import java.util.*;

public class Day5 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 5);
        //partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input) {
        ArrayList<Integer[]> seats = new ArrayList<>();
        for (String data : input) {
            int rowMin = 0, rowMax = 127, colMin = 0, colMax = 7;
            for (Character c : data.trim().toCharArray()){

                switch (c) {
                    case 'F':
                        rowMax = (int) Math.floor((rowMin + rowMax) / 2.0);
                        break;
                    case 'B':
                        rowMin = (int) Math.ceil((rowMin + rowMax) / 2.0);
                        break;
                    case 'R':
                        colMin = (int) Math.ceil((colMin + colMax) / 2.0);
                        break;
                    case 'L':
                        colMax = (int) Math.floor((colMin + colMax) / 2.0);
                        break;
                }
            }
            int row = data.trim().charAt(6) == 'F' ? rowMax : rowMin;
            int col = data.trim().charAt(9) == 'R' ? colMax : colMin;
            seats.add(new Integer[]{row, col});
        }

        ArrayList<Integer> seatIDs = new ArrayList<Integer>();
        for(Integer[] seat : seats){
            seatIDs.add(seat[0]*8 + seat[1]);
        }

        Collections.sort(seatIDs);
        System.out.println(seatIDs.get(seatIDs.size()-1));
    }

    public static void partTwo(String[] input){
        ArrayList<Integer[]> seats = new ArrayList<>();
        for (String data : input) {
            int rowMin = 0, rowMax = 127, colMin = 0, colMax = 7;
            for (Character c : data.trim().toCharArray()){
                switch (c) {
                    case 'F':
                        rowMax = (int) Math.floor((rowMin + rowMax) / 2.0);
                        break;
                    case 'B':
                        rowMin = (int) Math.ceil((rowMin + rowMax) / 2.0);
                        break;
                    case 'R':
                        colMin = (int) Math.ceil((colMin + colMax) / 2.0);
                        break;
                    case 'L':
                        colMax = (int) Math.floor((colMin + colMax) / 2.0);
                        break;
                }
            }
            int row = data.trim().charAt(6) == 'F' ? rowMax : rowMin;
            int col = data.trim().charAt(9) == 'R' ? colMax : colMin;
            seats.add(new Integer[]{row, col});
        }

        ArrayList<Integer> seatIDs = new ArrayList<Integer>();
        for(Integer[] seat : seats){
            seatIDs.add(seat[0]*8 + seat[1]);
        }

        Collections.sort(seatIDs);

        for(int i = Collections.min(seatIDs); i < Collections.max(seatIDs); i++) {
            if(!seatIDs.contains(i) && seatIDs.contains(i-1) && seatIDs.contains(i+1)) {
                System.out.println(i);
            }
        }
    }
}