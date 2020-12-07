import java.io.*;
import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 7);
        Map<String, Bag> bags = new HashMap<String, Bag>();
        for (String data : input) {
            String[] bagData = data.trim().split(",");
            String curName = getBagName(bagData[0]);
            Bag cur;

            if(!bags.containsKey(curName)) {
                cur = new Bag(curName);
            } else {
                cur = bags.get(curName);
            }

            if(data.contains("no other bags")){
                bags.put(curName, cur);
                continue;
            }

            bagData[0] = bagData[0].substring(bagData[0].indexOf("contain")).replace("contain", "").trim();

            for(int i = 0; i < bagData.length; i++){
                bagData[i] = bagData[i].trim();
                String bagName = getBagName(bagData[i]).replaceAll("[0-9]", "").trim();
                Bag toAdd;
                if(!bags.containsKey(bagName)){
                    toAdd = new Bag(bagName);
                    bags.put(bagName, toAdd);
                }else{
                    toAdd = bags.get(bagName);
                }
                cur.addBag(toAdd, Integer.parseInt(bagData[i].replaceAll("[^0-9]", "").trim()));
            }
            bags.put(curName, cur);
        }

        int shinyGold = 0;
        for(Bag b : bags.values()) {
            if(containsShinyGold(b)) {
                shinyGold++;
            }
        }

        System.out.println(shinyGold);
        System.out.println(countBags(bags.get("shiny gold")));
    }

    public static boolean containsShinyGold(Bag b){
        boolean hasBag = false;
        for (Bag child : b.getBags().keySet()){
            if(child.getName().equals("shiny gold") || containsShinyGold(child)) {
                hasBag = true;
            }
        }
        return hasBag;
    }

    public static int countBags(Bag b){
        int bags = 0;
        for (Bag child : b.getBags().keySet()){
            bags += b.getBags().get(child);
            bags += b.getBags().get(child) * countBags(child);
        }
        return bags;
    }

    public static String getBagName(String s){
        return s.substring(0, s.indexOf("bag")).trim();
    }

}

class Bag implements Comparable<Bag>{
    String name;
    Map<Bag, Integer> bags;

    public Bag(String name) {
        this.name = name;
        bags = new HashMap<Bag, Integer>();
    }

    public String getName() {
        return name;
    }

    public Map<Bag, Integer> getBags() {
        return bags;
    }

    public void addBag(Bag b, int amount){
        bags.put(b, amount);
    }

    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Bag o) {
        return name.compareTo(o.name);
    }
}
