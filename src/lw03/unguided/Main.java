package lw03.unguided;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        List<String> courses = new ArrayList<>();
        Map<String, Integer> courseEnrollments = new HashMap<>();

        int rejectedCount = 0;

        while (sc1.hasNextLine()) {
            String line = sc1.nextLine().trim();
            if (line.isEmpty())
                continue;

            String[] parts = line.split(" ", 2);
            String command = parts[0];

            if (command.equals("REGISTER")) {
                String[] courseParts = parts[1].split(" ", 2);
                String course = courseParts[0];
                int student = Integer.parseInt(courseParts[1]);

                if (student <= 0) {
                    rejectedCount++;
                }

                if (courseEnrollments.containsKey(course)) {
                    int currentStudent = courseEnrollments.get(course);
                    courseEnrollments.put(course, currentStudent + student);
                } else {
                    courseEnrollments.put(course, student);

                }

            } else if (command.equals("WITHDRAW")) {
                String[] courseParts = parts[1].split(" ", 2);
                String courseWithdraw = courseParts[0];
                int studentWithdraw = Integer.parseInt(courseParts[1]);

                if (courseEnrollments.containsKey(courseWithdraw)) {
                    int currentStudent = courseEnrollments.get(courseWithdraw);
                    courseEnrollments.put(courseWithdraw, currentStudent - studentWithdraw);
                } else {
                    rejectedCount++;
                }

            } else if (command.equals("CHECK")) {
                String course = parts[1];
                int enrolledStudents = courseEnrollments.getOrDefault(course, 0);
                System.out.println("===== Enrollment Checks =====");
                System.out.println("Rejected registrations: " + rejectedCount);

            }
        }
    }
}
