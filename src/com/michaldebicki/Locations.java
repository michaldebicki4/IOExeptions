package com.michaldebicki;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer,Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
//        try(BufferedWriter localFile = new BufferedWriter(new FileWriter("locations.txt"));
//            BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))){
//            for(Location location : locations.values()){
//                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for(String direction : location.getExits().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
//                    }
//                }
//            }
//        }
//        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
//            for(Location location : locations.values()){
//                locFile.writeInt(location.getLocationID());
//                locFile.writeUTF(location.getDescription());
//                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
//                System.out.println("Writing " + (location.getExits().size()-1) + " exits.");
//                locFile.writeInt(location.getExits().size() - 1);
//                for(String direction : location.getExits().keySet()){
//                    if(!direction.equalsIgnoreCase("Q")) {
//                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
//                        locFile.writeUTF(direction);
//                        locFile.writeInt(location.getExits().get(direction));
//                    }
//                }
//            }
//        }
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()) {
                locFile.writeObject(location);
            }
        }

    }

    // 1. This first four bytes will contain the number of locations (0-30)
    // 2. The next four bytes will contain the start offset of the locations section (bytes 4-7)
    // 3. The next section of the file will contain the index (index is 1692 bytes long. It will start at byte 8 and end at byte 1699
    // 4. The final section of the file will contain the location records (the data). It will start at byte 1700
    static { //is executed before main method
//        try( Scanner scanner =new Scanner(new BufferedReader(new FileReader("locations_big.txt")))){
////            scanner.useDelimiter(",");
////            while (scanner.hasNextLine()){
////                int loc = scanner.nextInt();
////                scanner.skip(scanner.delimiter());
////                String description = scanner.nextLine();
////                System.out.println("Imported loc: " + loc + ": " + description);
////                Map<String,Integer> tempExit = new HashMap<>();
////                locations.put(loc,new Location(loc,description,tempExit));
////
////
////            }
////        }catch(IOException e){
////            e.printStackTrace();
////        }
////
////        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
////                String input;
////                while ((input = dirFile.readLine()) != null){
////                String[] data = input.split(",");
////                int loc = Integer.parseInt(data[0]);
////                String direction = data[1];
////                int destination = Integer.parseInt(data[2]);
////
////                System.out.println(loc + ": " + direction + ": " + destination);
////                Location location = locations.get(loc);
////                location.addExit(direction, destination);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }





//        Map<String, Integer> tempExit = new HashMap<String,Integer>();
//
//        locations.put(0, new Location(0,"You are sitting in front of a computer learning Java",tempExit));
//        tempExit = new HashMap<String,Integer>();
//        tempExit.put("W",2);
//        tempExit.put("E",3);
//        tempExit.put("S",4);
//        tempExit.put("N",5);
//        locations.put(1, new Location(1,"You are standing at the end of the road before small brick building",tempExit));
//
//        tempExit = new HashMap<String,Integer>();
//        tempExit.put("N",5);
//        locations.put(2, new Location(2,"You are at the top of the hill",tempExit));
//
//        tempExit = new HashMap<String,Integer>();
//        tempExit.put("W",1);
//        locations.put(3, new Location(3,"You are inside building, a well house fot a small spring",tempExit));
//
//        tempExit = new HashMap<String,Integer>();
//        tempExit.put("N",1);
//        tempExit.put("W",2);
//        locations.put(4, new Location(4,"You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<String,Integer>();
//        tempExit.put("S",1);
//        tempExit.put("W",2);
//        locations.put(5, new Location(5,"You are in the forest",tempExit));
//
//        Map<String,String> vocabulary = new HashMap<String, String>();
//        vocabulary.put("NORTH", "N");
//        //i potem sprawdzamy ten słownik/ można i tak lepsze niż moje , tylko trzeba zrobić warunek jeśli direction.lenght >1
//
//        //Immutable class means that once an object is created, we cannot change its content.
//        //In Java, all the wrapper classes (like Integer, Boolean, Byte, Short) and String class is immutable. ...
//        // Data members in the class must be declared as final (So that we can't change the value of it after object creation)

        try(ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream((new FileInputStream("locations.dat"))))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExits().size() + " exits");

                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        }catch (InvalidClassException e){
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException io){
            System.out.println("IO Exception");
        }catch (ClassNotFoundException e) {
            System.out.println("ClassNoFoundException " + e.getMessage());
        }
    }
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
