package controller;

import connection.LivreModel;

public class LivreController {

    public  void ajouter(String Titre, String Auth, String ISBN) {
        LivreModel nouveauLivre = new LivreModel();
        nouveauLivre.setNom(Titre);
        nouveauLivre.setNomauth(Auth);
        nouveauLivre.setISBN(ISBN);
        
        nouveauLivre.ajouterLivre(nouveauLivre);
    }

    public  void modifier(String nom, String nouveauTitre, String nouvelAuteur, String nouvelISBN) {
    	LivreModel nouveauLivre = new LivreModel();
    	 nouveauLivre.modifierLivre( nom,nouveauTitre, nouvelAuteur, nouvelISBN);
    }
   
    public  void archiver(String ISBN) {
    	LivreModel nouveauLivre = new LivreModel();
    	 nouveauLivre.archiver(ISBN);
    }

    public  void rechercher(String recherche) {
    	LivreModel nouveauLivre = new LivreModel();
    	 nouveauLivre.rechercher(recherche);
    }

  
    public void listerLivres() {
        LivreModel livreModel = new LivreModel();
        livreModel.listerLivres();
    }
    public void listerLivresEmpruntés() {
        LivreModel livreModel = new LivreModel();
        livreModel.listerLivresEmprunté();
    }
    public void listerArchivés() {
        LivreModel livreModel = new LivreModel();
        livreModel.listerLivresArchvés();
    }
    public void listerALivresDisponibles() {
        LivreModel livreModel = new LivreModel();
        livreModel.listerLivresDisponibles();
    }
    public void listerALivresPerdus() {
        LivreModel livreModel = new LivreModel();
        livreModel.listerLivresPerdus();
    }
}
