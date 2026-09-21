import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner();

        sc.getResourceAsStream("jobs.txt");

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();

            Rental rental;

            if (type.equals("LAPTOP")) {
                rental = new LaptopRental(id, days);
            } else if (type.equals("PROJECTOR")) {
                rental = new ProjectorRental(id, days);
            } else {
                System.out.println("Tipe rental tidak valid.");
                continue;
            }

            System.out.println(rental.summary());
        }

        sc.close();
    }
}
