package connection;
import validation.LivreValidator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;
public class LivreModel {


	    private String nom;
	    private String nomauth;
	    private String ISBN;
	    private String status;

	  
	    public String getNom() {
	        return nom;
	    }

	    public String getNomauth() {
	        return nomauth;
	    }

	    public String getISBN() {
	        return ISBN;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setNom(String n) {
	        this.nom = n;
	    }

	    public void setNomauth(String t) {
	        this.nomauth = t;
	    }

	    public void setISBN(String i) {
	        this.ISBN = i;
	    }

	    public void setStatus(String s) {
	        this.status = s;
	    }
	    public void listerLivresArchvés() {
	        Connection con = null;
	        try {
	            connection c = new connection();
	            con = c.connect();

	            if (con != null) {
	                String selectionSQL = "SELECT * FROM livre WHERE status = ?";
	                PreparedStatement preparedStatement = con.prepareStatement(selectionSQL);
	                preparedStatement.setString(1, "Archivé");
	                ResultSet resultSet = preparedStatement.executeQuery();
            boolean isEmpty=true;
	                while (resultSet.next()) {
	                	isEmpty=false;
	                    String nom = resultSet.getString("nom");
	                    String nomauth = resultSet.getString("nomauth");
	                    String ISBN = resultSet.getString("ISBN");	                    
	                    System.out.println( " Nom: " + nom + ", Auteur: " + nomauth + ", ISBN: " + ISBN  );
	                }

	                resultSet.close();
	                preparedStatement.close();
	                c.disconnect(con);
	                if(isEmpty==true) {
	                	System.out.println("La liste des livres archivés est vide.");
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
	    public void listerLivresEmprunté() {
	        Connection con = null;
	        try {
	            connection c = new connection();
	            con = c.connect();

	            if (con != null) {
	                String selectionSQL = "SELECT * FROM livre INNER JOIN emprunte WHERE status = ?";
	                PreparedStatement preparedStatement = con.prepareStatement(selectionSQL);
	                preparedStatement.setString(1, "emprunté");
	                ResultSet resultSet = preparedStatement.executeQuery();
            boolean isEmpty=true;
	                while (resultSet.next()) {
	                	isEmpty=false;
	                   
	                    String nom = resultSet.getString("nom");
	                    String nomauth = resultSet.getString("nomauth");
	                    String ISBN = resultSet.getString("ISBN");
	                    Date date = resultSet.getDate("date");
	                    isEmpty=false;
	                    System.out.println(" Nom: " + nom + ", Auteur: " + nomauth + ", ISBN: " + ISBN +", Date de l'emprunte:" + date);
	                }

	                resultSet.close();
	                preparedStatement.close();
	                c.disconnect(con);
	                
	                if(isEmpty==true) {
	                	System.out.println("La liste des livres empruntés est vide.");
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
	    public void listerLivresPerdus() {
	        Connection con = null;
	        try {
	            connection c = new connection();
	            con = c.connect();

	            if (con != null) {
	                String selectionSQL = "SELECT * FROM livre WHERE status = ?";
	                PreparedStatement preparedStatement = con.prepareStatement(selectionSQL);
	                preparedStatement.setString(1, "perdu");
	                ResultSet resultSet = preparedStatement.executeQuery();
	                boolean isEmpty = true; 
	                while (resultSet.next()) {
	                	isEmpty = false;
	                    String nom = resultSet.getString("nom");
	                    String nomauth = resultSet.getString("nomauth");
	                    String ISBN = resultSet.getString("ISBN");	  
	                    
	                    System.out.println( " Nom: " + nom + ", Auteur: " + nomauth + ", ISBN: " + ISBN  );
	                }
	                if(isEmpty==true) {
	                	System.out.println("La liste des livres perdus est vide.");
	                }

	                resultSet.close();
	                preparedStatement.close();
	                c.disconnect(con);
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
	    public void listerLivres() {
	        Connection con = null;
	        try {
	            connection c = new connection();
	            con = c.connect();

	            if (con != null) {
	                String selectionSQL = "SELECT * FROM livre WHERE status = ? OR status = ?";
	                PreparedStatement preparedStatement = con.prepareStatement(selectionSQL);
	                preparedStatement.setString(1, "disponible");
	                preparedStatement.setString(2, "emprunté");
	                ResultSet resultSet = preparedStatement.executeQuery();
     boolean isEmpty=true;
	                while (resultSet.next()) {
	                   
	                    String nom = resultSet.getString("nom");
	                    String nomauth = resultSet.getString("nomauth");
	                    String ISBN = resultSet.getString("ISBN");
	                    String status = resultSet.getString("status");
	                    isEmpty=false;

	                    System.out.println( " Nom: " + nom + ", Auteur: " + nomauth + ", ISBN: " + ISBN + ", L'état: " + status);
	                }

	                resultSet.close();
	                preparedStatement.close();
	                c.disconnect(con);
	                if(isEmpty==true) {
	                	System.out.println("La liste des livres  est vide.");
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
	    public void listerLivresDisponibles() {
	        Connection con = null;
	        try {
	            connection c = new connection();
	            con = c.connect();

	            if (con != null) {
	                String selectionSQL = "SELECT * FROM livre WHERE status = ? ";
	                PreparedStatement preparedStatement = con.prepareStatement(selectionSQL);
	                preparedStatement.setString(1, "disponible");
                      
	                ResultSet resultSet = preparedStatement.executeQuery();
                   boolean isEmpty=true;
	                while (resultSet.next()) {
	                	isEmpty=false;
	                    
	                    String nom = resultSet.getString("nom");
	                    String nomauth = resultSet.getString("nomauth");
	                    String ISBN = resultSet.getString("ISBN");

	                    System.out.println(" Nom: " + nom + ", Auteur: " + nomauth + ", ISBN: " + ISBN );
	                }

	                resultSet.close();
	                preparedStatement.close();
	                c.disconnect(con);
	                if(isEmpty==true) {
	                	System.out.println("La liste des livres disponibles est vide.");
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
	    public void ajouterLivre(LivreModel livre) {
	        connection c = new connection();
	        Connection con = c.connect();
	        
	        LivreValidator validator = new LivreValidator(con);

	        if (con != null) {
	            try {
	                String nom = livre.getNom();
	                String ISBN = livre.getISBN();
	                if (!validator.isISBNUnique(ISBN)) {
	                    System.out.println("L'ISBN existe déjà.");
	                    return; 
	                }

	                String insertionSQL = "INSERT INTO livre (nom, nomauth, ISBN) VALUES (?, ?, ?)";
	                PreparedStatement preparedStatement = con.prepareStatement(insertionSQL);

	                preparedStatement.setString(1, nom);
	                preparedStatement.setString(2, livre.getNomauth());
	                preparedStatement.setString(3, ISBN);

	                int lignesAffectees = preparedStatement.executeUpdate();
	                System.out.println(lignesAffectees + " Livre ajouté avec succès.");

	                preparedStatement.close();
	                c.disconnect(con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void modifierLivre(String ISBN, String nouveauTitre, String nouvelAuteur, String nouvelISBN) {
	        connection c = new connection();
	        Connection con = c.connect();
	        LivreValidator validator = new LivreValidator(con);

	        if (con != null) {
	            try {
	             
	              

	                if (!validator.isISBNUnique(nouvelISBN)) {
	                    System.out.println("Le nouvel ISBN existe déjà.");
	                    return; 
	                }

	                
	                String modificationSQL = "UPDATE livre SET nom = ?, nomauth = ?, ISBN = ? WHERE ISBN = ?";
	                PreparedStatement preparedStatement = con.prepareStatement(modificationSQL);

	                preparedStatement.setString(1, nouveauTitre);
	                preparedStatement.setString(2, nouvelAuteur);
	                preparedStatement.setString(3, nouvelISBN);
	                preparedStatement.setString(4, ISBN);

	                int lignesAffectees = preparedStatement.executeUpdate();
	                System.out.println(lignesAffectees + " Livre modifié avec succès.");

	                preparedStatement.close();
	                c.disconnect(con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	  
	    public   void rechercher(String recherche) {
	    	connection c=new connection();
	        Connection con = c.connect();
	        if (con != null) {
	            try {
	                String rechercheSQL = "SELECT * FROM livre WHERE nom LIKE ? OR nomauth LIKE ? OR ISBN LIKE ?";
	                PreparedStatement preparedStatement = con.prepareStatement(rechercheSQL);
	                
	      
	                preparedStatement.setString(1, "%" + recherche + "%");
	                preparedStatement.setString(2, "%" + recherche + "%");
	                preparedStatement.setString(3, "%" + recherche + "%");
	                ResultSet resultSet = preparedStatement.executeQuery();
        boolean isEmpty=true;
	                while (resultSet.next()) {
	                    isEmpty=false;
	                    String nom = resultSet.getString("nom");
	                    String nomauth = resultSet.getString("nomauth");
	                    String ISBN = resultSet.getString("ISBN");
	                    String status = resultSet.getString("status");

	                    System.out.println( " Nom: " + nom + ", Auteur: " + nomauth + ", ISBN: " + ISBN + ", L'état: " + status);
	                }
                 if(isEmpty==true) {
                	 System.out.println("Ce livre n'existe pas ");
                 }
	                resultSet.close();
	                preparedStatement.close();
	                c.disconnect(con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    public  void archiver(String recherche) {
	    	connection c=new connection();
	        Connection con = c.connect();
	        if (con != null) {
	            try {
	                String archiverSQL = "UPDATE livre SET status = 'Archivé' WHERE ISBN LIKE ?";
	                PreparedStatement preparedStatement = con.prepareStatement(archiverSQL);
	                
	             
	                preparedStatement.setString(1, "%" + recherche + "%");
	               

	                int lignesAffectees = preparedStatement.executeUpdate();
	                System.out.println(lignesAffectees + "Livre archivée(s) avec succès.");

	                preparedStatement.close();
	                c.disconnect(con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }}}
	
