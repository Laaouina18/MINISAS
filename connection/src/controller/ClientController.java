package controller;

import connection.ClientModel;
import connection.LivreModel;

public class ClientController {
	  public  void ajouter(String Adresse, String nom,String prenom,String CIN, int num) {
	        ClientModel nouveauClient = new ClientModel();
	        nouveauClient.setAdresse(Adresse);
	        nouveauClient.setNom(nom);
	        nouveauClient.setPrenom(prenom);
	        nouveauClient.setCin(CIN);
	        nouveauClient.setNum(num);
	        nouveauClient.ajouterClient(nouveauClient);
	    }

	    public  void archiver(String nom) {
	    	 ClientModel nouveauLivre = new  ClientModel();
	    	 nouveauLivre.archiverClient(nom);
	    }

	 
	    public  void Recherche(String recherche) {
	    	 ClientModel nouveauLivre = new  ClientModel();
	    	 nouveauLivre.rechercherParCIN(recherche);
	    }
	    public void listerArchivés() {
	        ClientModel ClientModel = new ClientModel();
	        ClientModel.listerClientArchvés();
	    }
}
