import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Cetak lokasi direktori kerja aktif tempat Java mencari file
        System.out.println("Current Working Directory: " + new File(".").getAbsolutePath());

        File file = new File("transactions.txt");
        System.out.println("Mencari file di: " + file.getAbsolutePath());
        System.out.println("Apakah file ada? " + file.exists());

        if (!file.exists()) {
            System.out.println("Gagal: File tidak ditemukan di path di atas!");
            return;
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}