package validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreValidator {

    private Connection connection;

    public LivreValidator(Connection connection) {
        this.connection = connection;
    }

    public boolean isISBNUnique(String ISBN) {
        try {
            String query = "SELECT COUNT(*) FROM livre WHERE ISBN = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ISBN);
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
