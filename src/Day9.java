import java.io.*;
import java.util.*;

public class Day9 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 9);
        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input){
        ArrayList<Long> numbers = new ArrayList<Long>();
        int preambleLength = 25;
        for (String data : input) {
            numbers.add(Long.parseLong(data.trim()));
        }

        for(int i = 0; i < numbers.size(); i++){
            if(i < preambleLength){
                continue;
            }

            boolean valid = false;
            for (int j = i-preambleLength; j < i; j++) {
                for (int k = i-preambleLength; k < i; k++) {
                    if (numbers.get(j) + numbers.get(k) == numbers.get(i) && numbers.get(j) != numbers.get(k)) {
                        valid = true;
                    }
                }
            }

            if(!valid){
                System.out.println(numbers.get(i));
                break;
            }

        }

    }

    public static void partTwo(String[] input){
        ArrayList<Long> numbers = new ArrayList<Long>();
        int preambleLength = 25;
        for (String data : input) {
            numbers.add(Long.parseLong(data.trim()));
        }

        ArrayList<Long> invalidNumbers = new ArrayList<Long>();
        for(int i = 0; i < numbers.size(); i++){
            if(i < preambleLength){
                continue;
            }

            boolean valid = false;
            for (int j = i-preambleLength; j < i; j++) {
                for (int k = i-preambleLength; k < i; k++) {
                    if (numbers.get(j) + numbers.get(k) == numbers.get(i) && numbers.get(j) != numbers.get(k)) {
                        valid = true;
                    }
                }
            }

            if(!valid){
                invalidNumbers.add(numbers.get(i));
            }
        }

        for(Long invalid : invalidNumbers) {
            int min = 0;
            int max = 1;
            while(true) {
                int sum = 0;
                for(int i = min; i <= max; i++){
                    sum += numbers.get(i);
                }

                if(sum == invalid) {
                    ArrayList<Long> contiguous = new ArrayList<Long>();
                    for(int i = min; i < max; i++) {
                        contiguous.add(numbers.get(i));
                        Collections.sort(contiguous);
                    }
                    System.out.println(contiguous.get(0) + contiguous.get(contiguous.size()-1));
                    break;
                } else if(sum < invalid) {
                    max++;
                } else {
                    min++;
                }
            }
        }
    }
}