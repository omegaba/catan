package Jeu;

import java.util.Scanner;

import Joueur.Joueur;
import Plateau.Plateau;

public class Communication {

    Scanner sc;

    public Communication() {
        sc = new Scanner(System.in);
    }

    public String choixPartie() {
        System.out.println("Voulez-vous jouer en mode textuel ou en mode graphique ?");
        String rep;
        do {
            rep = sc.nextLine().toLowerCase().replaceAll("\s+", "");
        } while (!rep.equals("textuel") && !rep.equals("graphique"));
        return rep;
    }

    public int demanderNombreJoueurs() {
        System.out.println("Combien de joueurs vont participer ? 3 ou 4 ?");
        String rep;
        do {
            rep = sc.nextLine().replaceAll("\\s+", "");
        } while (!rep.equals("3") && !rep.equals("4"));
        return Integer.parseInt(rep);
    }

    public Joueur demanderJoueurs(int numeroJoueur, Plateau p) {
        System.out.println("Le joueur n° " + numeroJoueur + " est-il joueur par un humain ou par l'ia ?");
        String rep;
        do {
            rep = sc.nextLine().toLowerCase().replaceAll("\\s+", "");
        } while (!rep.equals("humain") && !rep.equals("ia"));
        boolean ia = rep.equals("ia");
        String nom;
        if (!ia) {
            System.out.println("Quel est le nom du joueur ?");
            do {
                rep = sc.nextLine();
            } while (rep.isEmpty());
            nom = rep;
        } else
            nom = "ia" + numeroJoueur;
        System.out.println("Quelle couleurs aura le joueurs ?");
        do {
            rep = sc.nextLine();
        } while (rep.isEmpty());
        return new Joueur(nom, ia, rep, p);
    }

    public String choixAction(String action) {
        System.out.println(action);
        String rep;
        do {
            rep = sc.nextLine().toLowerCase().replaceAll("\\s+", "");
        } while (!rep.equals("oui") && !rep.equals("non"));
        return rep;
    }

    public String choixConstruction() {
        System.out.println(
                "Voulez-vous construire une Route, une Colonie ou une Ville ?");
        String rep;
        do {
            rep = sc.nextLine().toLowerCase().replaceAll("\s+", "");
        } while (!rep.equals("route") && !rep.equals("colonie") && !rep.equals("ville"));
        return rep;
    }

    public String choixRessource(String action) {
        System.out.println(action + " Vous pouvez écrire stop pour arreter l'échange.");
        String rep;
        do {
            rep = sc.nextLine().toLowerCase().replaceAll("\\s+", "");
        } while (!rep.equals("argile") && !rep.equals("bois") && !rep.equals("laine") && !rep.equals("ble")
                && !rep.equals("blé") && !rep.equals("minerai") && !rep.equals("stop"));
        return rep;
    }

    public int choixPort(int nbPort) {
        System.out.println("Quel port voulez-vous faire du commerce avec ?");
        String rep;
        do {
            rep = sc.nextLine().toLowerCase().replaceAll("\\s+", "");
        } while (Integer.parseInt(rep) <= 0 && Integer.parseInt(rep) > nbPort);
        return Integer.parseInt(rep) - 1;
    }

}
