package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        sortCards(cards, 2);

        for (int i = 0; i < n; i++) {
            System.out.print(cards[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int indexFound = findCard(cards, target);

            if (indexFound != -1) {
                System.out.println(target + ": FOUND at index " + indexFound);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }

    public static void sortCards(int[] arr, int passes) {
        for (int i = 0; i < passes && i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}