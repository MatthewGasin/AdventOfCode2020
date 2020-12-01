import java.io.*;
import java.util.*;

public class Day1 {
    private final static int DAY = 1;

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 1);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (String data : input) {
            nums.add(Integer.parseInt(data.trim()));
        }

        for (Integer i : nums) {
            for (Integer j : nums) {
                for (Integer k : nums) {
                    if(i + j + k == 2020){
                        System.out.println(i * k * j);
                    }
                }
            }
        }
    }
}