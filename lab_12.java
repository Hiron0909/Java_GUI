
import java.sql.*;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) throws Exception{
        
            // Database Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/StudentDB",
                "root",
                "0195592@Ab"
            );

            System.out.println("Database Connected Successfully!");

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Insert");
                System.out.println("2. Delete");
                System.out.println("3. Edit");
                System.out.println("4. Show");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int ch = sc.nextInt();

                if (ch == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO Student VALUES (?, ?, ?, ?)"
                    );

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, email);
                    ps.setString(4, phone);

                    ps.executeUpdate();
                    System.out.println("Data Inserted Successfully!");
                }

                else if (ch == 2) {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM Student WHERE id=?"
                    );

                    ps.setInt(1, id);

                    int r = ps.executeUpdate();

                    if (r > 0)
                        System.out.println("Data Deleted Successfully!");
                    else
                        System.out.println("ID Not Found!");
                }

                else if (ch == 3) {
                    System.out.print("Enter ID to edit: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter New Phone: ");
                    String phone = sc.nextLine();

                    PreparedStatement ps = con.prepareStatement(
                        "UPDATE Student SET name=?, email=?, phone=? WHERE id=?"
                    );

                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    ps.setInt(4, id);

                    int r = ps.executeUpdate();

                    if (r > 0)
                        System.out.println("Data Updated Successfully!");
                    else
                        System.out.println("ID Not Found!");
                }

                else if (ch == 4) {
                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery(
                        "SELECT * FROM Student"
                    );

                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getString("email") + " " +
                            rs.getString("phone")
                        );
                    }
                }

                else if (ch == 5) {
                    con.close();
                    sc.close();
                    System.out.println("Program Closed!");
                    break;
                }

                else {
                    System.out.println("Invalid Choice!");
                }
            }

       
    }
}


// Compile -- javac -cp ".;lib/*" StudentManagement.java
// Run -- java -cp ".;lib/*" StudentManagement



// -- Create Database
// CREATE DATABASE StudentDB;

// -- Select Database
// USE StudentDB;

// -- Create Table
// CREATE TABLE Student(
//    id INT PRIMARY KEY,
//    name VARCHAR(50),
//    email VARCHAR(80),
//    phone VARCHAR(20)
// );
