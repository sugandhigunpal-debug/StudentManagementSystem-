 import java.util.ArrayList;

public class StudentManager {

    ArrayList<Student> students = new ArrayList<>();

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student Added Successfully");
    }

    // View Students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No Students Found");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    // Update Student
    public void updateStudent(int id, String newName, String newCourse) {

        for (Student s : students) {

            if (s.id == id) {
                s.name = newName;
                s.course = newCourse;

                System.out.println("Student Updated Successfully");
                return;
            }
        }

        System.out.println("Student Not Found");
    }

    // Delete Student
    public void deleteStudent(int id) {

        for (Student s : students) {

            if (s.id == id) {
                students.remove(s);
                System.out.println("Student Deleted Successfully");
                return;
            }
        }

        System.out.println("Student Not Found");
    }
}
