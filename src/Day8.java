import java.io.*;
import java.util.*;

public class Day8 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 8);
        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input){
        ArrayList<Command> instructions = new ArrayList<Command>();
        for (String data : input) {
            String instruction = data.split(" ")[0].trim();
            int num = Integer.parseInt(data.split(" ")[1].substring(1).trim());
            num = data.split(" ")[1].charAt(0) == '-' ? num * -1 : num;
            instructions.add(new Command(instruction, num));
        }


        Set<Command> visited = new HashSet<Command>();
        int accumulator = 0, i = 0;
        Command cur = instructions.get(0);
        while(!visited.contains(cur)) {
            visited.add(cur);
            switch(cur.instruction){
                case "acc":
                    accumulator += cur.getAcc();
                    i++;
                    break;
                case "jmp":
                    i += cur.getAcc();
                    break;
                case "nop":
                    i++;
                    break;
            }
            cur = instructions.get(i);
        }

        System.out.println(accumulator);

    }

    public static void partTwo(String[] input){
        ArrayList<Command> instructions = new ArrayList<Command>();
        ArrayList<Command> instructionsTemp = new ArrayList<Command>();
        for (String data : input) {
            String instruction = data.split(" ")[0].trim();
            int num = Integer.parseInt(data.split(" ")[1].substring(1).trim());
            num = data.split(" ")[1].charAt(0) == '-' ? num * -1 : num;
            instructions.add(new Command(instruction, num));
            instructionsTemp.add(new Command(instruction, num));
        }

        for (int i = 0; i < instructions.size(); i++) {
            //skip accumulate commands
            if(instructions.get(i).getInstruction().equals("acc")){
                continue;
            }

            for(int j = 0; j < instructions.size(); j++){
                instructionsTemp.set(j, new Command(instructions.get(j)));
            }


            //determine if this command, when flipped, saves the loop
            if(instructions.get(i).getInstruction().equals("nop")){
                instructionsTemp.get(i).setInstruction("jmp");
            } else {
                instructionsTemp.get(i).setInstruction("nop");
            }

            Set<Command> visited = new HashSet<Command>();
            int accumulator = 0, j = 0;
            Command cur = instructionsTemp.get(0);
            while(!visited.contains(cur)) {
                visited.add(cur);
                switch(cur.getInstruction()){
                    case "acc":
                        accumulator += cur.getAcc();
                        j++;
                        break;
                    case "jmp":
                        j += cur.getAcc();
                        break;
                    case "nop":
                        j++;
                        break;
                }
                if(j < instructionsTemp.size()){
                    cur = instructionsTemp.get(j);
                } else {
                    System.out.println(accumulator);
                }
            }
        }
    }
}

class Command {
    String instruction;
    int acc;

    public Command(String instruction, int acc) {
        this.instruction = instruction;
        this.acc = acc;
    }

    public Command(Command c) {
        this.instruction = c.getInstruction();
        this.acc = c.getAcc();
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getAcc() {
        return acc;
    }
}