package Jeu;

import Plateau.Plateau;

public class AffichageText {
    private Plateau p;

    public AffichageText(Plateau p) {
        this.p = p;
    }

    public void affiche() {
        for (int i = 0; i < p.getPlateau().length; i++) {
            for (int j = 0; j < p.getPlateau().length; j++) {
                System.out.print("|" + p.getPlateau()[i][j] + "|");
            }
            System.out.println("\n" + "-".repeat(100));
        }
    }
}
