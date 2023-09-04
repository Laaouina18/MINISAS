package connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class connection {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjava", "root", "");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjava", "root", "");

            
            String insertQuery = "INSERT INTO user (column1, column2, column3) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            
    
            preparedStatement.setInt(1, 1); 
            preparedStatement.setString(2, "John"); 
            preparedStatement.setString(3, "Doe"); 
            
        
            preparedStatement.executeUpdate();
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        
    }
}
