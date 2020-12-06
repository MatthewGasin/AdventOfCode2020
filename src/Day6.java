import java.io.*;
import java.util.*;

public class Day6 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 6);
        //partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input){
        ArrayList<Group> groups = new ArrayList<Group>();
        Group cur = new Group();
        for (String data : input) {
            cur.addPerson(data.trim());

            if(data.trim().equals("")){
                groups.add(cur);
                cur = new Group();
            }
        }
        groups.add(cur);

        int result = 0;

        for(Group g : groups){
            result += g.getUniqueVotes().size();
        }

        System.out.println(result);
    }

    public static void partTwo(String[] input){
        ArrayList<Group> groups = new ArrayList<Group>();
        Group cur = new Group();
        for (String data : input) {

            if(data.trim().equals("")){
                groups.add(cur);
                cur = new Group();
                continue;
            }

            cur.addPerson(data.trim());

        }

        groups.add(cur);

        int result = 0;
        for(Group g : groups) {
            for(Character c : g.getUniqueVotes()) {
                boolean everybodyHas = true;
                for(String person : g.getPeople()) {
                    System.out.println(person);
                    if(!person.contains(""+c)){
                        everybodyHas = false;
                    }
                }

                if (everybodyHas) {
                    result++;
                }
            }
        }

        System.out.println(result);

    }
}

class Group {
    ArrayList<String> people;
    Set<Character> uniqueVotes;

    public Group() {
        this.people = new ArrayList<String>();
        uniqueVotes = new HashSet<Character>();
    }

    public ArrayList<String> getPeople() {
        return people;
    }
    public Set<Character> getUniqueVotes() {
        return uniqueVotes;
    }

    public void addPerson(String person){
        people.add(person);
        for(Character c : person.toCharArray()){
            uniqueVotes.add(c);
        }


    }
}