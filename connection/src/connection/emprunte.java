package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import validation.EmpruntValidator;
public class emprunte {

    private int idLivre;
    private int idClient;
    private java.util.Date date;

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }



    public void ajouterEmprunt(String ISBN, String CIN) {
        connection c = new connection();
        Connection con = c.connect();
        
        if (con != null) {
            try {
                EmpruntValidator validator = new EmpruntValidator(con);

                
                if (!validator.canEmprunterLivre(ISBN, CIN)) {
                    return; 
                }

        
                String selectLivreSQL = "SELECT id FROM livre WHERE ISBN = ?";
                PreparedStatement selectLivreStatement = con.prepareStatement(selectLivreSQL);
                selectLivreStatement.setString(1, ISBN);
                ResultSet livreResultSet = selectLivreStatement.executeQuery();
                int idLivre = -1;
                if (livreResultSet.next()) {
                    idLivre = livreResultSet.getInt("id");
                } else {
                    System.out.println("Livre non trouvé. Veuillez entrer un nom de livre existant.");
                    return;
                }

                String selectClientSQL = "SELECT id FROM client WHERE CIN = ?";
                PreparedStatement selectClientStatement = con.prepareStatement(selectClientSQL);
                selectClientStatement.setString(1, CIN);
                ResultSet clientResultSet = selectClientStatement.executeQuery();
                int idClient = -1;
                if (clientResultSet.next()) {
                    idClient = clientResultSet.getInt("id");
                } else {
                    System.out.println("CIN non trouvé. Veuillez entrer un CIN de client existant.");
                    return;
                }

               
                String insertionSQL = "INSERT INTO emprunte (idLivre, idClient) VALUES (?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(insertionSQL);

                preparedStatement.setInt(1, idLivre);
                preparedStatement.setInt(2, idClient);

                int lignesAffectees = preparedStatement.executeUpdate();
                System.out.println(lignesAffectees + " Emprunt ajouté avec succès.");

                preparedStatement.close();
                c.disconnect(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void listerEmprunts() {
        connection c = new connection();
        Connection con = c.connect();
        if (con != null) {
            try {
                String selectionSQL = "SELECT e.idLivre, e.idClient, e.date, l.nom AS nomLivre, c.nom AS nomClient FROM emprunte e " +
                                      "INNER JOIN livre l ON e.idLivre = l.id " +
                                      "INNER JOIN client c ON e.idClient = c.id";
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(selectionSQL);

                while (resultSet.next()) {
                    String nomLivre = resultSet.getString("nomLivre");
                    String nomClient = resultSet.getString("nomClient");
                    java.util.Date date = resultSet.getDate("date");

                    System.out.println("Livre: " + nomLivre + ", Client: " + nomClient + ", Date: " + date);
                }

                resultSet.close();
                statement.close();
                c.disconnect(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


   
    public void supprimer(String ISBN) {
        Connection con = null;
        try {
            connection c = new connection();
            con = c.connect();

            if (con != null) {
                
                String selectLivreSQL = "SELECT id FROM livre WHERE ISBN = ?";
                PreparedStatement selectLivreStatement = con.prepareStatement(selectLivreSQL);
                selectLivreStatement.setString(1, ISBN);
                ResultSet resultSet = selectLivreStatement.executeQuery();
                
                int idLivre = -1;
                if (resultSet.next()) {
                    idLivre = resultSet.getInt("id");
                }

                if (idLivre != -1) {
                  
                    String suppressionSQL = "DELETE FROM emprunte WHERE idLivre = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(suppressionSQL);
                    preparedStatement.setInt(1, idLivre);

                    int lignesAffectees = preparedStatement.executeUpdate();
                    System.out.println(lignesAffectees + " L'emprunte supprimée avec succès.");

                
                    preparedStatement.close();
                } else {
                    System.out.println("Livre non trouvé avec l'ISBN : " + ISBN);
                }
                
                c.disconnect(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
