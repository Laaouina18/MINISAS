package Main;
import java.util.Scanner;
import controller.LivreController;
import controller.ClientController;
import controller.EmprunteController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

public static void listeLivres(Scanner scanner, LivreController livreController) {
	
	   while (true) {
           System.out.println("Menu liste des livres:");
          System.out.println("1.Liste des livres disponibles ");
           System.out.println("2. Liste des livres empruntés");
           System.out.println("3. Liste des livres archivés");
           System.out.println("4. Liste des livres perdus");
           System.out.println("5. Liste des livres");
           System.out.println("6. Retour au menu principal");
          

           System.out.print("Choisissez une option (1-4): ");
           int choix = scanner.nextInt();
           scanner.nextLine();
   
    switch(choix) {
    
    case 1:
    	System.out.println("*************Liste des livres Disponibles*************");
    	 livreController.listerALivresDisponibles();
    	 break;
    case 2:
    	System.out.println("*************Liste des livres Empruntés*************");
    	livreController.listerLivresEmpruntés();
    	break;
    case 3:
    	System.out.println("*************Liste des livres archivés*************");
    	livreController.listerArchivés();
    	break;
    case 4:
    	System.out.println("*************Liste des livres Perdus*************");
   	 livreController.listerALivresPerdus();
   	break;
    case 5:
    	System.out.println("*********************Liste des livres *************");
    	 livreController.listerLivres();
    	break;
    case 6:
    	return;
    default:
    	System.out.print("Option non valide. Veuillez choisir une option valide.");
    }}
}
     
    public static void gestionLivres(Scanner scanner, LivreController livreController) {
        while (true) {
            System.out.println("Menu de gestion des livres:");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Modifier un livre");
            System.out.println("3. Supprimer un livre");
            System.out.println("4. Rechercher un livre");
            System.out.println("5. Lister les livres");
            System.out.println("6. Retour au menu principal");

            System.out.print("Choisissez une option (1-6): ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
            case 1:
                System.out.print("Entrez le titre du livre: ");
                String titre = scanner.nextLine();
                System.out.print("Entrez l'auteur du livre: ");
                String auteur = scanner.nextLine();
                System.out.print("Entrez l'ISBN du livre: ");
                String isbn = scanner.nextLine();
                livreController.ajouter(titre, auteur, isbn);
                break;
            case 2:
                System.out.print("Entrez ISBN du livre à modifier: ");
                String ISBN= scanner.nextLine();
                System.out.print("Entrez le nouveau titre du livre: ");
                String nouveauTitre = scanner.nextLine();
                System.out.print("Entrez le nouvel auteur du livre: ");
                String nouvelAuteur = scanner.nextLine();
                System.out.print("Entrez le nouvel ISBN du livre: ");
                String nouvelISBN = scanner.nextLine();
                livreController.modifier(ISBN, nouveauTitre, nouvelAuteur, nouvelISBN);
                break;
            case 3:
                System.out.print("Entrez l'ISBN du livre à supprimer: ");
                String nomLivreArchiver = scanner.nextLine();
                livreController.archiver(nomLivreArchiver);
                break;
            case 4:
                System.out.print("Entrez l'ISBN du livre à rechercher: ");
                String recherche = scanner.nextLine();
                livreController.rechercher(recherche);
                break;
        
            case 5:
            	
            listeLivres(scanner, livreController);
                break;
            case 6:
            	   return;
            default:
                System.out.println("Option non valide. Veuillez choisir une option valide.");
        }
        }
    }

    public static void gestionClients(Scanner scanner, ClientController clientController) {
        while (true) {
            System.out.println("Menu de gestion des clients:");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Rechercher un client");
            System.out.println("4. Archive des clients");
            System.out.println("5. Retour au menu principal");

            System.out.print("Choisissez une option (1-4): ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
            case 1: 
                System.out.print("Entrez l'adresse du client: ");
                String adresseClient = scanner.nextLine();
                System.out.print("Entrez le nom du client: ");
                String nomClient = scanner.nextLine();
                System.out.print("Entrez le prénom du client: ");
                String prenomClient = scanner.nextLine();
                System.out.print("Entrez le CIN du client: ");
                String cinClient = scanner.nextLine();
                System.out.print("Entrez le numéro du client: ");
                int numClient = scanner.nextInt();
                clientController.ajouter(adresseClient, nomClient, prenomClient, cinClient, numClient);
                break;

            case 2: 
                System.out.print("Entrez CIN du client à supprimé: ");
                String nomClientArchiver = scanner.nextLine();
                clientController.archiver(nomClientArchiver);
                break;

            case 3: 
                System.out.print("Entrez le CIN du client à rechercher: ");
                String rechercheClient = scanner.nextLine();
                clientController.Recherche(rechercheClient);
                break;
            case 4:
            	System.out.println("***************Liste des clients archivés**************");
            	 clientController.listerArchivés();
            	return;
            case 5: 
                return;
            default:
                System.out.println("Option non valide. Veuillez choisir une option valide.");

            }
        }
    }

    public static void gestionEmpruntes(Scanner scanner, EmprunteController emprunteController) {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Lister les emprunts");
            System.out.println("2. Ajouter un emprunt");
            System.out.println("3. Supprimer un emprunt");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    emprunteController.listerEmprunts();
                    break;
                case 2:
                    System.out.print("ISBN du livre : ");
                    String nomLivre = scanner.nextLine();

                    System.out.print("CIN du client : ");
                    String nomClient = scanner.nextLine();
                    emprunteController.ajouterEmprunt(nomLivre, nomClient);
                    break;
                case 3:
                    System.out.print("ISBN du livre : ");
                    String titre = scanner.nextLine();
                    emprunteController.supprimerEmprunt(titre);
                    break;
              
                case 4:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.err.println("Option invalide.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivreController livreController = new LivreController();
        ClientController clientController = new ClientController();
        EmprunteController emprunteController = new EmprunteController();

        while (true) {
            System.out.println("**********************Menu principal:*******************");
            System.out.println("1. Gestion des livres");
            System.out.println("2. Gestion des clients");
            System.out.println("3. Gestion des emprunts"); 
            System.out.println("4. Archive de bibliothèque"); 
            System.out.println("5. Statistiques"); 
            System.out.println("6. Quitter");

            System.out.print("Choisissez une option (1-5): ");
            int choixPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (choixPrincipal) {
                case 1:
                    gestionLivres(scanner, livreController);
                    break;
                case 2:
                    gestionClients(scanner, clientController);
                    break;
                case 3:
                    gestionEmpruntes(scanner, emprunteController); 
                    break;
                    
                case 4:
                	System.out.println("*************Liste des livres archivés*************");
                	livreController.listerArchivés();
                	break;
                case 5:
              
                    listeLivres(scanner, livreController);
                case 6:
               return;
                default:
                    System.out.println("Option non valide. Veuillez choisir une option valide.");
            }
        }
    }
}






