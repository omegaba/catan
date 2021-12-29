package Jeu;

import java.util.Scanner;

import Joueur.Joueur;

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

    public Joueur demanderJoueurs(int numeroJoueur) {
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
            } while (!rep.isEmpty());
            nom = rep;
        } else
            nom = "ia" + numeroJoueur;
        System.out.println("Quelle couleurs aura le joueurs ?");
        do {
            rep = sc.nextLine();
        } while (!rep.isEmpty());
        return new Joueur(nom, ia, rep);
    }

}
