import java.io.*;
import java.util.*;

public class Day2 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 2);
        int valid = 0;
        for (String data : input) {
            String[] passwordData = data.split(" ");
            int locationA = Integer.parseInt(""+passwordData[0].split("-")[0]);
            int locationB = Integer.parseInt(""+passwordData[0].split("-")[1]);
            Character toFind = passwordData[1].charAt(0);
            String password = passwordData[2];

            boolean isValid = false;
            isValid = password.charAt(locationA-1) == toFind ? !isValid : isValid;
            isValid = password.charAt(locationB-1) == toFind ? !isValid : isValid;


            if(isValid){
                valid++;
            }
        }

        System.out.println(valid);
    }

}