import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        // Membaca file transactions.txt
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty())
                continue;

            String[] parts = line.split("\\s+");
            if (parts.length == 3) {
                transactionList.add(parts);

                String name = parts[0];
                boolean exists = false;
                for (String[] cust : customerList) {
                    if (cust[0].equals(name)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    customerList.add(new String[] { name, "0" });
                }
            }
        }
        sc.close();

        // 1. Pindahkan transaksi dari LinkedList ke Queue (FIFO)
        Queue<String[]> transactionQueue = new LinkedList<>();
        for (String[] trans : transactionList) {
            transactionQueue.add(trans);
        }

        // 2. Stack untuk menyimpan transaksi pengeluaran yang gagal (LIFO)
        Stack<String[]> failedStack = new Stack<>();

        // 3. Process transaksi dari Queue
        while (!transactionQueue.isEmpty()) {
            String[] trans = transactionQueue.poll();
            String name = trans[0];
            String type = trans[1];
            int amount = Integer.parseInt(trans[2]);

            String[] targetCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equalsIgnoreCase("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equalsIgnoreCase("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedStack.push(trans);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        // 4. Cetak Saldo Akhir
        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        // 5. Cetak Transaksi Gagal (LIFO)
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}