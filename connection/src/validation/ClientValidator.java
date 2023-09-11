package validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ClientValidator {
    private Connection connection;

    public ClientValidator(Connection connection) {
        this.connection = connection;
    }

    public boolean isClientUnique(String cin) {
        try {
            String query = "SELECT COUNT(*) FROM client WHERE cin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
       
            preparedStatement.setString(1, cin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0; 
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
}
