import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        // 1. Membaca N dan elemen array stability
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }
        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        // 2. Membaca M dan target yang dicari
        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        // 3. Menjalankan Insertion Sort (Descending) tepat 3 Pass (i = 1 sampai 3)
        insertionSortThreePasses(branches);

        // 4. Menampilkan array setelah 3 pass
        for (int i = 0; i < n; i++) {
            System.out.print(branches[i] + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        // 5. Melakukan Linear Search untuk setiap target
        for (int i = 0; i < m; i++) {
            int target = targets[i];
            int indexFound = linearSearch(branches, target);
            if (indexFound != -1) {
                System.out.println(target + ": FOUND at index " + indexFound);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }

    /**
     * Melakukan Insertion Sort secara Descending tepat 3 Pass (i = 1..3).
     */
    private static void insertionSortThreePasses(int[] arr) {
        int passes = 3;
        for (int i = 1; i <= passes && i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Geser elemen yang lebih kecil dari key ke kanan (Descending)
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Melakukan Linear Search manual untuk menemukan indeks elemen target.
     */
    private static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}