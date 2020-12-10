import java.util.*;

public class Day10 {


    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 10);
        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (String data : input) {
            numbers.add(Integer.parseInt(data.trim()));
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        int cur = 0, smallJump = 0, bigJump = 0;
        while (path.size() < numbers.size()) {
            ArrayList<Integer> possibleJumps = new ArrayList<Integer>();
            for (Integer i : numbers) {
                if (i - cur >= 1 && i - cur <= 3) {
                    possibleJumps.add(i);
                }
            }

            Collections.sort(possibleJumps);
            int diff = possibleJumps.get(0) - cur;

            switch (diff) {
                case 1:
                    smallJump++;
                    break;
                case 3:
                    bigJump++;
                    break;
            }
            cur = possibleJumps.get(0);
            path.add(cur);
        }
        bigJump++;

        System.out.println(smallJump * bigJump);
    }


    public static void partTwo(String[] input) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Map<Integer, Adapter> adapters = new HashMap<Integer, Adapter>();
        for (String data : input) {
            numbers.add(Integer.parseInt(data.trim()));
        }

        numbers.add(0);
        numbers.add(Collections.max(numbers) + 3);

        // Organize the data into the map, Number: Object that holds Jump data and eventually Distance data
        for (Integer i : numbers) {
            if (!adapters.containsKey(i)) {
                adapters.put(i, new Adapter(i));
            }

            Adapter cur = adapters.get(i);
            for (Integer j : numbers) {
                if (i - j >= 1 && i - j <= 3) {
                    if (!adapters.containsKey(j)) {
                        adapters.put(j, new Adapter(j));
                    }
                    adapters.get(j).addJump(cur);
                }
            }
        }

        // Evaluate the distance from initial adapter to end goal
        System.out.println(evalAdapters(Collections.min(adapters.values()), Collections.max(adapters.values())));
    }

    // Memoization
    public static long evalAdapters(Adapter adapter, Adapter goal) {
        // Goal state
        if (adapter.equals(goal)) {
            return 1;
        // Distance already evaluated
        } else if (adapter.getDistance() != -1) {
            return adapter.getDistance();
        // Evaluate distance recursively
        } else {
            long dist = 0;
            for (Adapter next : adapter.getPossibleJumps()) {
                dist += evalAdapters(next, goal);
            }

            adapter.setDistance(dist);
            return dist;
        }
    }
}

class Adapter implements Comparable<Adapter> {
    int number;
    long distance;
    ArrayList<Adapter> possibleJumps;

    public Adapter(int number) {
        this.number = number;
        distance = -1;
        possibleJumps = new ArrayList<Adapter>();
    }

    public ArrayList<Adapter> getPossibleJumps() {
        return possibleJumps;
    }

    public void addJump(Adapter adapter) {
        possibleJumps.add(adapter);
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Adapter o) {
        return Integer.compare(number, o.number);
    }

    public String toString() {
        return "" + number;
    }
}
