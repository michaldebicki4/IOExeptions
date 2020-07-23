package com.michaldebicki;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int loc = 1;
        while (true){
            System.out.println(locations.get(loc).getDescription());
//            tempExit.remove("S");
            if(loc == 0){
                break;
            }

            Map<String,Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");
            for (String exit: exits.keySet()){
                System.out.print(exit + ",");
            }
            System.out.println();

            int checkLoc = loc;
            String direction = scanner.nextLine();
            String[] splitDirection =  direction.split("");

//            for (String j : splitDirection) {
//            System.out.println(j);
//        }

            for (String i : splitDirection) {
                if(exits.containsKey(i)){
                    loc = exits.get(i);
                }
        }
            if(checkLoc == loc){
                System.out.println("You cannot go in that direction");
            }

//            if(exits.containsKey(direction)){
//                loc = exits.get(direction);
//            }else{
//                System.out.println("You cannot go in that direction");
//            }
        }
//        String[] road = "You are standing at the end of a road before a small brick building".split(" ");
//        for (String i : road) {
//            System.out.println(i);
//        }
//        System.out.println("==================");
//
//        String[] building = "You are inside a building, a well house for a small spring".split(", ");
//        for (String i : building){
//            System.out.println(i);
//        }
    }
}
