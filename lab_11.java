//StudentManagement.java
//StudentManagement.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class studentmanage {
    public static void main(String[] args) throws SQLException  {
            Connection con = DriverManager.getConnection(
                "jdbc:ucanaccess://Database1.accdb"
            );    
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Student Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Student Phone: ");
            String phone = sc.nextLine();

            String sql = "INSERT INTO Student ([Name], Email, Phone) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            ps.executeUpdate();

            System.out.println("Student Data Inserted Successfully!");

            ps.close();
            con.close();
            sc.close();
        }
}


// show.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class show {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection(
            "jdbc:ucanaccess://Database1.accdb"
        );

        Statement st = con.createStatement();

        String sql = "SELECT * FROM Student";

        ResultSet rs = st.executeQuery(sql);

        System.out.println("===== All Student Information =====");

        while (rs.next()) {
            System.out.println("Name: " + rs.getString("Name"));
            System.out.println("Email: " + rs.getString("Email"));
            System.out.println("Phone: " + rs.getString("Phone"));
            System.out.println("----------------------------------");
        }

        rs.close();
        st.close();
        con.close();
    }
}

