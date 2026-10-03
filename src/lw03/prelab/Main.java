package lw03.prelab;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== Problem 1 =====");
        List<String> playlist = new ArrayList<>();

        Scanner sc1 = null;
        InputStream is1 = Main.class.getResourceAsStream("playlist.txt");
        if (is1 != null) {
            sc1 = new Scanner(is1);
        } else {
            try {
                sc1 = new Scanner(new File("playlist.txt"));
            } catch (Exception e) {
                try {
                    sc1 = new Scanner(new File("src/lw03/prelab/playlist.txt"));
                } catch (Exception ex) {
                    System.out.println("Error: File playlist.txt tidak ditemukan!");
                }
            }
        }

        if (sc1 != null) {
            while (sc1.hasNextLine()) {
                String line = sc1.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split(" ", 2);
                String command = parts[0];

                if (command.equals("ADD")) {
                    playlist.add(parts[1]);
                } else if (command.equals("INSERT")) {
                    String[] insertParts = parts[1].split(" ", 2);
                    int index = Integer.parseInt(insertParts[0]);
                    String song = insertParts[1];
                    playlist.add(index, song);
                } else if (command.equals("REMOVE")) {
                    playlist.remove(parts[1]);
                }
            }
            sc1.close();
        }

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println("\n===== Problem 2 =====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        Scanner sc2 = null;
        InputStream is2 = Main.class.getResourceAsStream("participants.txt");
        if (is2 != null) {
            sc2 = new Scanner(is2);
        } else {
            try {
                sc2 = new Scanner(new File("participants.txt"));
            } catch (Exception e) {
                try {
                    sc2 = new Scanner(new File("src/lw03/prelab/participants.txt"));
                } catch (Exception ex) {
                    System.out.println("Error: File participants.txt tidak ditemukan!");
                }
            }
        }

        if (sc2 != null) {
            while (sc2.hasNextLine()) {
                String name = sc2.nextLine().trim();
                if (name.isEmpty())
                    continue;

                if (!participants.add(name)) {
                    duplicateCount++;
                }
            }
            sc2.close();
        }

        System.out.println("Unique participants: " + participants.size());
        int rank = 1;
        for (String participant : participants) {
            System.out.println(rank + ". " + participant);
            rank++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println("\n===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner sc3 = null;
        InputStream is3 = Main.class.getResourceAsStream("inventory.txt");
        if (is3 != null) {
            sc3 = new Scanner(is3);
        } else {
            try {
                sc3 = new Scanner(new File("inventory.txt"));
            } catch (Exception e) {
                try {
                    sc3 = new Scanner(new File("src/lw03/prelab/inventory.txt"));
                } catch (Exception ex) {
                    System.out.println("Error: File inventory.txt tidak ditemukan!");
                }
            }
        }

        if (sc3 != null) {
            while (sc3.hasNextLine()) {
                String line = sc3.nextLine().trim();
                if (line.isEmpty())
                    continue;

                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }
            sc3.close();
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}