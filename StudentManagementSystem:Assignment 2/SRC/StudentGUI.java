 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGUI extends JFrame
        implements ActionListener {

    // Labels
    JLabel titleLabel;
    JLabel idLabel;
    JLabel nameLabel;
    JLabel courseLabel;
    JLabel searchLabel;

    // Text Fields
    JTextField idField;
    JTextField nameField;
    JTextField courseField;
    JTextField searchField;

    // Buttons
    JButton addButton;
    JButton viewButton;
    JButton updateButton;
    JButton deleteButton;
    JButton clearButton;
    JButton searchButton;
    JButton sortButton;

    // Text Area
    JTextArea displayArea;

    // Service Object
    StudentManager manager =
            new StudentManager();

    // Constructor
    StudentGUI() {

        setTitle(
                "Student Management System");

        setSize(750, 650);

        setLayout(null);

        getContentPane().setBackground(
                new Color(240, 248, 255));

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        // Title
        titleLabel =
                new JLabel(
                        "STUDENT MANAGEMENT SYSTEM");

        titleLabel.setBounds(
                170, 10, 450, 40);

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24));

        add(titleLabel);

        // Labels
        idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 80, 100, 30);
        add(idLabel);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 130, 100, 30);
        add(nameLabel);

        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 180, 100, 30);
        add(courseLabel);

        // Text Fields
        idField = new JTextField();
        idField.setBounds(180, 80, 200, 30);
        add(idField);

        nameField = new JTextField();
        nameField.setBounds(180, 130, 200, 30);
        add(nameField);

        courseField = new JTextField();
        courseField.setBounds(180, 180, 200, 30);
        add(courseField);

        // Buttons
        addButton = new JButton("Add");
        addButton.setBounds(450, 80, 120, 35);
        add(addButton);

        viewButton = new JButton("View");
        viewButton.setBounds(590, 80, 120, 35);
        add(viewButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(450, 130, 120, 35);
        add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(590, 130, 120, 35);
        add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(450, 180, 120, 35);
        add(clearButton);

        sortButton = new JButton("Sort");
        sortButton.setBounds(590, 180, 120, 35);
        add(sortButton);

        // Search
        searchLabel = new JLabel("Search ID:");
        searchLabel.setBounds(50, 280, 100, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(180, 280, 200, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(450, 280, 120, 35);
        add(searchButton);

        // Display Area
        displayArea = new JTextArea();

        displayArea.setFont(
                new Font("Monospaced",
                        Font.PLAIN,
                        14));

        JScrollPane scrollPane =
                new JScrollPane(displayArea);

        scrollPane.setBounds(
                50, 360, 660, 200);

        add(scrollPane);

        // Action Listeners
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        searchButton.addActionListener(this);
        sortButton.addActionListener(this);

        setVisible(true);
    }

    // DISPLAY STUDENTS
    public void displayStudents() {

        displayArea.setText("");

        for (Student s :
                manager.getStudents()) {

            displayArea.append(
                    s.toString() + "\n");
        }
    }

    // ACTION EVENTS
    @Override
    public void actionPerformed(
            ActionEvent e) {

        // ADD
        if (e.getSource() == addButton) {

            try {

                if (idField.getText().isEmpty()
                        ||
                        nameField.getText().isEmpty()
                        ||
                        courseField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please Fill All Fields!");

                    return;
                }

                int id =
                        Integer.parseInt(
                                idField.getText());

                String name =
                        nameField.getText();

                String course =
                        courseField.getText();

                Student s =
                        new Student(id,
                                name,
                                course);

                manager.addStudent(s);

                JOptionPane.showMessageDialog(
                        this,
                        "Student Added Successfully!");

                displayStudents();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!");
            }
        }

        // VIEW
        if (e.getSource() == viewButton) {

            displayStudents();
        }

        // UPDATE
        if (e.getSource() == updateButton) {

            try {

                int id =
                        Integer.parseInt(
                                idField.getText());

                String name =
                        nameField.getText();

                String course =
                        courseField.getText();

                boolean updated =
                       manager.updateStudent(
                                id,
                                name,
                                course);

                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Updated Successfully!");

                    displayStudents();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Not Found!");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!");
            }
        }

        // DELETE
        if (e.getSource() == deleteButton) {

            try {

                int id =
                        Integer.parseInt(
                                idField.getText());

                boolean deleted =
                        manager.deleteStudent(id);

                if (deleted) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Deleted Successfully!");

                    displayStudents();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Not Found!");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!");
            }
        }

        // SEARCH
        if (e.getSource() == searchButton) {

            try {

                int id =
                        Integer.parseInt(
                                searchField.getText());

                Student s =
                        manager.searchStudent(id);

                if (s != null) {

                    displayArea.setText(
                            s.toString());

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Not Found!");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!");
            }
        }

        // SORT
        if (e.getSource() == sortButton) {

            manager.sortStudents();

            displayStudents();

            JOptionPane.showMessageDialog(
                    this,
                    "Students Sorted Successfully!");
        }

        // CLEAR
        if (e.getSource() == clearButton) {

            idField.setText("");
            nameField.setText("");
            courseField.setText("");
            searchField.setText("");
            displayArea.setText("");
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        new StudentGUI();
    }
}