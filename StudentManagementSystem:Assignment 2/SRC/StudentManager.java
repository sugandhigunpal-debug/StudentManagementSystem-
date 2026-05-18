import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.*;

public class StudentManager{

    ArrayList<Student> students = new ArrayList<>();

    String fileName = "students.txt";

    // Constructor
    public StudentManager() {

        loadFromFile();
    }

    // ADD STUDENT
    public void addStudent(Student s) {

        students.add(s);

        saveToFile();
    }

    // VIEW STUDENTS
    public ArrayList<Student> getStudents() {

        return students;
    }

    // UPDATE STUDENT
    public boolean updateStudent(int id,
                                 String name,
                                 String course) {

        for (Student s : students) {

            if (s.getId() == id) {

                s.setName(name);

                s.setCourse(course);

                saveToFile();

                return true;
            }
        }

        return false;
    }

    // DELETE STUDENT
    public boolean deleteStudent(int id) {

        for (Student s : students) {

            if (s.getId() == id) {

                students.remove(s);

                saveToFile();

                return true;
            }
        }

        return false;
    }

    // SEARCH STUDENT
    public Student searchStudent(int id) {

        for (Student s : students) {

            if (s.getId() == id) {

                return s;
            }
        }

        return null;
    }

    // SORT STUDENTS
    public void sortStudents() {

        Collections.sort(students,
                Comparator.comparing(Student::getName));
    }

    // SAVE FILE
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

    // LOAD FILE
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
