package Jeu;

import Plateau.Plateau;

public class Launcher {
    public static void main(String[] args) {
        Plateau p = new Plateau();
        AffichageText a = new AffichageText(p);
        a.affiche();
    }
}
