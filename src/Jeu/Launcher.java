package Jeu;

import Plateau.Plateau;

public class Launcher {
    public static void main(String[] args) {

        Communication c = new Communication();
        if (c.choixPartie().equals("textuel"))
            jeuText();
        else
            jeuGraph();

    }

    static void jeuText() {
        Plateau p = new Plateau();
        AffichageText a = new AffichageText(p);
        a.affiche();
    }

    static void jeuGraph() {
    }
}
