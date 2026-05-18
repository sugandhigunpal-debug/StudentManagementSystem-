import java.util.Scanner;

public class StudentCLI {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService();

        int choice;

        do {

            System.out.println("\n_______ STUDENT MANAGEMENT SYSTEM _______");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Exit");

            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    service.addStudent();
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    service.updateStudent();
                    break;

                case 4:
                    service.deleteStudent();
                    break;

                case 5:
                    service.searchStudent();
                    break;

                case 6:
                    service.sortStudents();
                    break;

                case 7:
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}
