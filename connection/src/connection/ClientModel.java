

package connection;
import validation.ClientValidator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientModel {
    private String Nom;
    private String Prenom;
    private String Adresse;
    private int Num;
    private String CIN;

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public int getNum() {
        return Num;
    }

    public String getCin() {
        return CIN;
    }

    public void setNom(String N) {
        Nom = N;
    }

    public void setPrenom(String P) {
        Prenom = P;
    }

    public void setAdresse(String A) {
        Adresse = A;
    }

    public void setNum(int N) {
        Num = N;
    }

    public void setCin(String C) {
        CIN = C;
    }

    public void ajouterClient(ClientModel client) {
  
    	 connection c = new connection();
         Connection con = c.connect();
        try {
        
       
            if (con != null) {
                ClientValidator validator = new ClientValidator(con);
                String cin = client.getCin();

                if (!validator.isClientUnique(CIN)) {
                    System.out.println("Ce client existe déjà.");
                    return;
                }

                String insertionSQL = "INSERT INTO client (NOM, prenom, num, adresse, cin) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(insertionSQL);

                preparedStatement.setString(1, Nom);
                preparedStatement.setString(2, client.getPrenom());
                preparedStatement.setInt(3, Num);
                preparedStatement.setString(4, client.getAdresse());
                preparedStatement.setString(5, cin);

                int lignesAffectees = preparedStatement.executeUpdate();
                System.out.println(lignesAffectees + " Client ajouté avec succès.");

                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
         
            if (con != null) {
                c.disconnect(con);
            }
        }
    }


    public void archiverClient(String CIN) {
       
        connection c = new connection();
        Connection con = c.connect();
        if (con != null) {
        	rechercherParCIN(CIN);
            try {
                String archiverSQL = "UPDATE client SET status = 'Archivé' WHERE CIN = ?";
                PreparedStatement preparedStatement = con.prepareStatement(archiverSQL);

                preparedStatement.setString(1, CIN);

                int lignesAffectees = preparedStatement.executeUpdate();
                System.out.println(lignesAffectees + " Client supprimé avec succès.");

                preparedStatement.close();
                c.disconnect(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void rechercherParCIN(String recherche) {

        connection c = new connection();
        Connection con = c.connect();
        if (con != null) {
            try {
                String rechercheSQL = "SELECT * FROM client WHERE cin LIKE ?";
                PreparedStatement preparedStatement = con.prepareStatement(rechercheSQL);

                preparedStatement.setString(1, "%" + recherche + "%");

                ResultSet resultSet = preparedStatement.executeQuery();

                int count = 0; 

                while (resultSet.next()) {
                    String nom = resultSet.getString("NOM");
                    String prenom = resultSet.getString("prenom");
                    int num = resultSet.getInt("num");
                    String adresse = resultSet.getString("adresse");
                    String cin = resultSet.getString("cin");
                    System.out.println("Nom: " + nom + ", Prénom: " + prenom + ", CIN: " + cin + ", Numéro: " + num + ", Adresse: " + adresse);
                    count++; 
                }

                resultSet.close();
                preparedStatement.close();
                c.disconnect(con);

                if (count == 0) {
                    System.out.println("Aucun client trouvé pour la CIN : " + recherche);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void listerClientArchvés() {
        Connection con = null;
        try {
            connection c = new connection();
            con = c.connect();

            if (con != null) {
                String selectionSQL = "SELECT * FROM client WHERE status = ?";
                PreparedStatement preparedStatement = con.prepareStatement(selectionSQL);
                preparedStatement.setString(1, "Archivé");
                ResultSet resultSet = preparedStatement.executeQuery();
        boolean isEmpty=true;
                while (resultSet.next()) {
                	isEmpty=false;
                    String nom = resultSet.getString("NOM");
                    String prenom = resultSet.getString("prenom");
                    int num = resultSet.getInt("num");
                    String adresse = resultSet.getString("adresse");
                    String cin = resultSet.getString("cin");
                    System.out.println("Nom: " + nom + ", Prénom: " + prenom + ", CIN: " + cin + ", Numéro: " + num + ", Adresse: " + adresse);
                 
                }

                resultSet.close();
                preparedStatement.close();
                c.disconnect(con);
                if(isEmpty==true) {
                	System.out.println("***************La liste clients archivés est vide.********************");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}