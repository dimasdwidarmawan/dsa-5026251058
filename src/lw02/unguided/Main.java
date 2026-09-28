
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        LinkedList<String[]> borrowingList = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();
        LinkedList<String[]> memberList = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> stack = new Stack<>();

        while (sc.hasNextLine()) {
            String[] borrow = new String[2];
            borrow[0] = sc.next();
            borrow[1] = sc.next();
            String name = borrow[0];
            borrowingList.add(borrow);

            memberList.add(new String[] { name, "0" });

        }

        bookList.add(new String[] { "Kalkulus", "2" });
        bookList.add(new String[] { "Fisika", "1" });
        bookList.add(new String[] { "Statistika", "2" });

        while (!queue.isEmpty()) {
            queue.addAll(borrowingList);
            String[] cust = queue.poll();
            String name = cust[0];
            String bookName = cust[1];

            if (bookName.equals("Kalkulus")) {
                String[] targetBook = bookList.get(0);
                int stock = Integer.parseInt(targetBook[1]);
                if (stock > 0) {
                    stock--;
                    targetBook[1] = String.valueOf(stock);
                    for (String[] member : memberList) {
                        if (member[0].equals(name)) {
                            int borrowed = Integer.parseInt(member[1]);
                            borrowed++;
                            member[1] = String.valueOf(borrowed);

                            if (borrowed > 2) {
                                stack.push(cust);
                            }
                            break;
                        }
                    }
                } else {
                    stack.push(cust);
                }

            } else if (bookName.equals("Fisika")) {
                String[] targetBook = bookList.get(1);
                int stock = Integer.parseInt(targetBook[1]);
                if (stock > 0) {
                    stock--;
                    targetBook[1] = String.valueOf(stock);
                    for (String[] member : memberList) {
                        if (member[0].equals(name)) {
                            int borrowed = Integer.parseInt(member[1]);
                            borrowed++;
                            member[1] = String.valueOf(borrowed);
                            if (borrowed > 2) {
                                stack.push(cust);
                            }
                            break;
                        }
                    }
                } else {
                    stack.push(cust);
                }

            } else if (bookName.equals("Statistika")) {
                String[] targetBook = bookList.get(2);
                int stock = Integer.parseInt(targetBook[1]);
                if (stock > 0) {
                    stock--;
                    targetBook[1] = String.valueOf(stock);
                    for (String[] member : memberList) {
                        if (member[0].equals(name)) {
                            int borrowed = Integer.parseInt(member[1]);
                            borrowed++;
                            member[1] = String.valueOf(borrowed);
                            if (borrowed > 2) {
                                stack.push(cust);
                            }
                            break;
                        }
                    }
                } else {
                    stack.push(cust);
                }
            }

            System.out.println("=== Successfully Processed Requests ===");
            for (String[] borrow : borrowingList) {
                System.out.println(borrow[0] + " : " + borrow[1]);
            }

            System.out.println("=== Remaining Book Stock ===");
            for (String[] books : bookList) {
                System.out.println(books[0] + " : " + books[1]);
            }

            System.out.println("=== Failed Requests ===");
            while (!stack.isEmpty()) {
                String[] failedRequest = stack.pop();
                System.out.println(failedRequest[0] + " : " + failedRequest[1]);
            }
        }

    }
}
