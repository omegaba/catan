package Plateau;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import Joueur.Joueur;
import Plateau.Composants.Case;

public class Plateau {

    private Case[][] plateau;
    private LinkedList<Joueur> listJoueur;

    public Plateau() {
        LinkedList<Integer> jeton = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 12));
        LinkedList<String> environment = new LinkedList<>(Arrays.asList("Forêt", "Pré", "Champs", "Colline", "Montagne",
                "Forêt", "Pré", "Champs", "Colline", "Montagne", "Forêt", "Pré", "Champs", "Colline", "Montagne"));
        plateau = new Case[6][6];
        int desert = 0;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (i == 0 || j == 0 || i == 5 || j == 5)
                    plateau[i][j] = new Case(0, "Maritime");
                else {
                    int r = new Random().nextInt(jeton.size());
                    if (++desert != 21)
                        plateau[i][j] = new Case(jeton.remove(r), environment.remove(r));
                    else
                        plateau[i][j] = new Case(0, "Désert");
                }
            }
        }
    }

}
