package controller;
import connection.emprunte;
import java.sql.SQLException;
import java.util.Date;

public class EmprunteController {

    private emprunte emprunte;

    public EmprunteController() {
        this.emprunte = new emprunte();
    }

    public void listerEmprunts() {
        emprunte.listerEmprunts();
    }

    public void ajouterEmprunt(String ISBN, String CIN){
    
    	    this.emprunte.ajouterEmprunt( ISBN,CIN);
    	
    }

    public void supprimerEmprunt(String ISBN) {
        emprunte.supprimer(ISBN);
    }




}
