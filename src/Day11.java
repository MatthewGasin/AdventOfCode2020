import java.util.*;

public class Day11 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 11);
        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input) {
        Character[][] cur = new Character[input.length][input[0].length()];
        Character[][] prev = new Character[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length(); x++) {
                cur[y][x] = input[y].charAt(x);
                prev[y][x] = '.';
            }
        }

        while (!compareSeats(prev, cur)) {
            //update seats
            Character[][] next = new Character[cur.length][cur[0].length];
            for (int i = 0; i < cur.length; i++) {
                for (int j = 0; j < cur[i].length; j++) {
                    // floor... floor never changes
                    if (cur[i][j] == '.') {
                        next[i][j] = '.';
                    }

                    // get adjacent seats
                    ArrayList<Character> adjacent = new ArrayList<Character>();
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dy == 0 && dx == 0) {
                                continue;
                            }

                            if ((i + dy >= 0) && (j + dx >= 0) && (i + dy < cur.length) && (j + dx < cur[i].length)) {
                                adjacent.add(cur[i + dy][j + dx]);
                            }
                        }
                    }

                    int numOccupied = 0;
                    for (Character c : adjacent) {
                        if (c == '#') {
                            numOccupied++;
                        }
                    }

                    // apply rules
                    if (cur[i][j] == 'L' && numOccupied == 0) {
                        next[i][j] = '#';
                    } else if (cur[i][j] == '#' && numOccupied >= 4) {
                        next[i][j] = 'L';
                    } else {
                        next[i][j] = cur[i][j];
                    }
                }
            }

            //make cur -> prev, next -> cur
            prev = copySeats(cur);
            cur = copySeats(next);
        }

        int numOccupied = 0;
        for (int i = 0; i < cur.length; i++) {
            for (int j = 0; j < cur[i].length; j++) {
                if (cur[i][j] == '#') {
                    numOccupied++;
                }
            }
        }
        System.out.println(numOccupied);
    }

    public static void partTwo(String[] input) {
        Character[][] cur = new Character[input.length][input[0].length()];
        Character[][] prev = new Character[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length(); x++) {
                cur[y][x] = input[y].charAt(x);
                prev[y][x] = '.';
            }
        }

        while (!compareSeats(prev, cur)) {
            //update seats
            Character[][] next = new Character[cur.length][cur[0].length];
            for (int i = 0; i < cur.length; i++) {
                for (int j = 0; j < cur[i].length; j++) {
                    if (cur[i][j] == '.') {
                        next[i][j] = '.';
                    }

                    // get adjacent seats
                    ArrayList<Boolean> visibleSeats = new ArrayList<Boolean>();
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dy == 0 && dx == 0) {
                                continue;
                            }
                            visibleSeats.add(isOccupiedInDirection(i, j, dx, dy, cur));
                        }
                    }

                    int numOccupied = 0;
                    for (Boolean canSeeSeat : visibleSeats) {
                        if (canSeeSeat) {
                            numOccupied++;
                        }
                    }

                    // apply rules
                    if (cur[i][j] == 'L' && numOccupied == 0) {
                        next[i][j] = '#';
                    } else if (cur[i][j] == '#' && numOccupied >= 5) {
                        next[i][j] = 'L';
                    } else {
                        next[i][j] = cur[i][j];
                    }
                }
            }

            //make cur -> prev, next -> cur
            prev = copySeats(cur);
            cur = copySeats(next);
        }

        int numOccupied = 0;
        for (int i = 0; i < cur.length; i++) {
            for (int j = 0; j < cur[i].length; j++) {
                if (cur[i][j] == '#') {
                    numOccupied++;
                }
            }
        }
        System.out.println(numOccupied);
    }

    public static boolean isOccupiedInDirection(int i, int j, int dx, int dy, Character[][] seats){
        int y = i + dy, x = j + dx;
        while(inBounds(y, x, seats)){
            if(seats[y][x] == '#'){
                return true;
            }else if(seats[y][x] == 'L'){
                return false;
            }
            y += dy;
            x += dx;
        }
        return false;
    }

    public static boolean inBounds(int i, int j, Character[][] seats){
        return (i >= 0) && (j >= 0) && (i < seats.length) && (j < seats[i].length);
    }

    public static boolean compareSeats(Character[][] prev, Character[][] cur) {
        for (int i = 0; i < cur.length; i++) {
            for (int j = 0; j < cur[i].length; j++) {
                if (prev[i][j] != cur[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printSeats(Character[][] seats) {
        for (Character[] line : seats) {
            for (Character c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static Character[][] copySeats(Character[][] seats) {
        Character[][] next = new Character[seats.length][seats[0].length];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                next[i][j] = seats[i][j];
            }
        }
        return next;
    }
}

