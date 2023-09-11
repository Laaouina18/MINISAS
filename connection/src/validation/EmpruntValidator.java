package validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpruntValidator {
    private Connection connection;

    public EmpruntValidator(Connection connection) {
        this.connection = connection;
    }

    public boolean canEmprunterLivre(String ISBN, String CIN) {
        try {
           
            String checkClientEmpruntsSQL = "SELECT COUNT(*) FROM emprunte " +
                                            "INNER JOIN client ON emprunte.idClient = client.id " +
                                            "WHERE CIN = ?";
            PreparedStatement checkClientEmpruntsStatement = connection.prepareStatement(checkClientEmpruntsSQL);
            checkClientEmpruntsStatement.setString(1, CIN);
            ResultSet clientEmpruntsResultSet = checkClientEmpruntsStatement.executeQuery();

            if (clientEmpruntsResultSet.next() && clientEmpruntsResultSet.getInt(1) > 0) {
                System.out.println("Ce client a déjà emprunté un livre. Il ne peut pas emprunter un autre livre.");
                return false;
            }

           
            String checkLivreEmprunteSQL = "SELECT COUNT(*) FROM emprunte " +
                                           "INNER JOIN livre ON emprunte.idLivre = livre.id " +
                                           "WHERE livre.ISBN = ? AND livre.status = 'emprunté'";
            PreparedStatement checkLivreEmprunteStatement = connection.prepareStatement(checkLivreEmprunteSQL);
            checkLivreEmprunteStatement.setString(1, ISBN);
            ResultSet livreEmprunteResultSet = checkLivreEmprunteStatement.executeQuery();

            if (livreEmprunteResultSet.next() && livreEmprunteResultSet.getInt(1) > 0) {
                System.out.println("Ce livre est déjà emprunté et ne peut pas être emprunté à nouveau.");
                return false;
            }

            String checkLivrePerduSQL = "SELECT COUNT(*) FROM livre WHERE ISBN = ? AND status = 'perdu'";
            PreparedStatement checkLivrePerduStatement = connection.prepareStatement(checkLivrePerduSQL);
            checkLivrePerduStatement.setString(1, ISBN);
            ResultSet livrePerduResultSet = checkLivrePerduStatement.executeQuery();

            if (livrePerduResultSet.next() && livrePerduResultSet.getInt(1) > 0) {
                System.out.println("Ce livre est perdu et ne peut pas être emprunté.");
                return false;
            }

           
            String checkLivreNonArchiveSQL = "SELECT COUNT(*) FROM livre WHERE ISBN = ? AND status != 'Archivé'";
            PreparedStatement checkLivreNonArchiveStatement = connection.prepareStatement(checkLivreNonArchiveSQL);
            checkLivreNonArchiveStatement.setString(1, ISBN);
            ResultSet livreNonArchiveResultSet = checkLivreNonArchiveStatement.executeQuery();

            if (!livreNonArchiveResultSet.next() || livreNonArchiveResultSet.getInt(1) == 0) {
                System.out.println("Ce livre n'existe pas ou est archivé.");
                return false;
            }

          
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
