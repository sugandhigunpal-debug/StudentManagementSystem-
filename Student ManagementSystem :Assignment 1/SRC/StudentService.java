import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import java.io.*;

public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    String fileName = "students.txt";

    // Constructor
    public StudentService() {

        loadFromFile();
    }

    // ADD STUDENT
    public void addStudent() {

        System.out.print("Enter Student ID: ");

        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Student Name: ");

        String name = sc.nextLine();

        System.out.print("Enter Course: ");

        String course = sc.nextLine();

        Student student =
                new Student(id, name, course);

        students.add(student);

        saveToFile();

        System.out.println(
                "Student Added Successfully!");
    }

    // VIEW STUDENTS
    public void viewStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No Students Found!");

            return;
        }

        System.out.println(
                "\n===== STUDENT RECORDS =====");

        for (Student s : students) {

            System.out.println(s);
        }
    }

    // UPDATE STUDENT
    public void updateStudent() {

        System.out.print(
                "Enter Student ID to Update: ");

        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.print(
                        "Enter New Name: ");

                String name = sc.nextLine();

                System.out.print(
                        "Enter New Course: ");

                String course = sc.nextLine();

                s.setName(name);

                s.setCourse(course);

                saveToFile();

                System.out.println(
                        "Student Updated Successfully!");

                return;
            }
        }

        System.out.println(
                "Student Not Found!");
    }

    // DELETE STUDENT
    public void deleteStudent() {

        System.out.print(
                "Enter Student ID to Delete: ");

        int id = sc.nextInt();

        for (Student s : students) {

            if (s.getId() == id) {

                students.remove(s);

                saveToFile();

                System.out.println(
                        "Student Deleted Successfully!");

                return;
            }
        }

        System.out.println(
                "Student Not Found!");
    }

    // SEARCH STUDENT
    public void searchStudent() {

        System.out.print(
                "Enter Student ID to Search: ");

        int id = sc.nextInt();

        for (Student s : students) {

            if (s.getId() == id) {

                System.out.println(
                        "\nStudent Found:");

                System.out.println(s);

                return;
            }
        }

        System.out.println(
                "Student Not Found!");
    }

    // SORT STUDENTS
    public void sortStudents() {

        Collections.sort(students,
                Comparator.comparing(Student::getName));

        System.out.println(
                "Students Sorted by Name!");
    }

    // SAVE DATA TO FILE
    public void saveToFile() {

        try {

            FileWriter fw =
                    new FileWriter(fileName);

            for (Student s : students) {

                fw.write(
                        s.getId() + "," +
                        s.getName() + "," +
                        s.getCourse() + "\n");
            }

            fw.close();

        } catch (Exception e) {

            System.out.println(
                    "Error Saving File!");
        }
    }

    // LOAD DATA FROM FILE
    public void loadFromFile() {

        try {

            File file = new File(fileName);

            if (!file.exists()) {

                return;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data =
                        line.split(",");

                int id =
                        Integer.parseInt(data[0]);

                String name = data[1];

                String course = data[2];

                Student s =
                        new Student(id,
                                name,
                                course);

                students.add(s);
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "Error Loading File!");
        }
    }
}
