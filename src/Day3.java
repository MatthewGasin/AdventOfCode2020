import java.io.*;
import java.util.*;

public class Day3 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 3);
        Character[][] forest = new Character[input.length][input[0].length()-1];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length()-1; j++) {
                forest[i][j] = input[i].charAt(j);
            }
        }

        // printForest(forest);

        int[] trees   = new int[]{0, 0, 0, 0, 0};
        int[] xSlopes = new int[]{1, 3, 5, 7, 1};
        int[] ySlopes = new int[]{1, 1, 1, 1, 2};

        for(int i = 0; i < 5; i++){
            int x = 0;
            int y = 0;
            while (y < forest.length) {
                if (forest[y][x].equals('#')) {
                    trees[i]++;
                }
                x += xSlopes[i];
                y += ySlopes[i];
                if(x >= forest[0].length){
                    x -= forest[0].length;
                }
            }
        }

        long result = 1;
        for (int i = 0; i < trees.length; i++) {
            System.out.println(trees[i]);
            result *= trees[i];
        }
        System.out.println(result);

    }

    public static void printForest(Character[][] forest) {
        for (Character[] line : forest) {
            for (Character spot : line) {
                System.out.print(spot);
            }
            System.out.println();
        }
    }

}