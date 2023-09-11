package connection;

import java.sql.*;

public class connection {

    public Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void disconnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
    }


 



}
