import Collection.Map.IMap;
import Collection.Map.MapRealization;
import Container.LinkedList;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        IMap<Integer, Integer> newMap = new MapRealization<>(new LinkedList<>());
        Scanner reader = new Scanner(in);
        Random rand = new Random();

        out.println("The hash code result: " + new Adress("Kyiv", "Peremohy Ave", 37).hashCode());

        out.print("Enter the map size: ");

        int mapSize = reader.nextInt();

        for (int i = 1; i <= mapSize; i++) {
            newMap.put(i, rand.nextInt(100));
        }

        out.println(newMap);

        out.print("Enter the key, you want to delete: ");

        int deletable_element = reader.nextInt();

        newMap.remove(deletable_element);

        out.println(newMap);

        out.print("Enter the key, you want to check: ");

        int check_key = reader.nextInt();

        out.println("\nDoes entered key contains in map: " + newMap.contains(check_key) + "\n");

        out.print("Enter the space-separated key and value, you want to put in map: ");

        int key = reader.nextInt();
        int value = reader.nextInt();

        newMap.put(key, value);

        out.print(newMap);
    }
}
