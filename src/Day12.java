import java.awt.*;
import java.io.*;
import java.util.*;

public class Day12 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 12);
        partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input){
        Point location = new Point(0, 0);
        int dir = 0;
        for (String data : input) {
            Character action = data.charAt(0);
            Integer value = Integer.parseInt(data.substring(1).trim());
            switch (action){
                case 'N':
                    location.y += value;
                    break;
                case 'S':
                    location.y -= value;
                    break;
                case 'E':
                    location.x += value;
                    break;
                case 'W':
                    location.x -= value;
                    break;
                case 'R':
                    dir -= value;
                    dir = fixDir(dir);
                    break;
                case 'L':
                    dir += value;
                    dir = fixDir(dir);
                    break;
                case 'F':
                    switch(dir){
                        case 90:
                            location.y += value;
                            break;
                        case 270:
                            location.y -= value;
                            break;
                        case 0:
                            location.x += value;
                            break;
                        case 180:
                            location.x -= value;
                            break;
                    }
                    break;
            }
        }

        System.out.println(manhattanDistance(new Point(0, 0), location));
    }

    public static void partTwo(String[] input){
        Point location = new Point(0, 0);
        Point waypoint = new Point(10, 1);
        for (String data : input) {
            Character action = data.charAt(0);
            Integer value = Integer.parseInt(data.substring(1).trim());
            Point temp = new Point(waypoint.x, waypoint.y);
            switch (action){
                case 'N':
                    waypoint.y += value;
                    break;
                case 'S':
                    waypoint.y -= value;
                    break;
                case 'E':
                    waypoint.x += value;
                    break;
                case 'W':
                    waypoint.x -= value;
                    break;
                case 'R':
                    switch(value){
                        case 90:
                            waypoint.y = -temp.x;
                            waypoint.x = temp.y;
                            break;
                        case 270:
                            waypoint.y = temp.x;
                            waypoint.x = -temp.y;
                            break;
                        case 0:
                            waypoint.y = temp.y;
                            waypoint.x = temp.x;
                            break;
                        case 180:
                            waypoint.y = -temp.y;
                            waypoint.x = -temp.x;
                            break;
                    }
                    break;
                case 'L':
                    switch(value){
                        case 90:
                            waypoint.y = temp.x;
                            waypoint.x = -temp.y;
                            break;
                        case 270:
                            waypoint.y = -temp.x;
                            waypoint.x = temp.y;
                            break;
                        case 0:
                            waypoint.y = temp.y;
                            waypoint.x = temp.x;
                            break;
                        case 180:
                            waypoint.y = -temp.y;
                            waypoint.x = -temp.x;
                            break;
                    }
                    break;
                case 'F':
                    for(int i = 0; i < value; i++){
                        location.x += waypoint.x;
                        location.y += waypoint.y;
                    }
                    break;
            }
        }

        System.out.println(manhattanDistance(new Point(0, 0), location));
    }

    //abandon ship! this is overengineering
    public static Point rotate(Point loc, Point waypoint, double angle){
        angle = Math.toRadians(angle);
        int x = (int) (Math.cos(angle) * (waypoint.x-loc.x) - Math.sin(angle) * (waypoint.y-loc.y) + loc.x);
        int y = (int) (Math.sin(angle) * (waypoint.x-loc.x) - Math.cos(angle) * (waypoint.y-loc.y) + loc.y);
        return new Point(x, y);
    }

    public static int fixDir(int dir){
        while(dir >= 360){
            dir -= 360;
        }

        while(dir < 0){
            dir += 360;
        }
        return dir;
    }

    public static int manhattanDistance(Point a, Point b){
        return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
    }


}