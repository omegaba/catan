package Plateau;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import Jeu.Communication;
import Joueur.Joueur;
import Plateau.Composants.Case;
public class Plateau {

    private Case[][] plateau;
    private LinkedList<Joueur> listJoueurs;

    public Plateau() {
        LinkedList<Integer> jeton = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 12));
        LinkedList<String> environment = new LinkedList<>(Arrays.asList("Forêt", "Pré", "Champs", "Colline", "Montagne",
                "Forêt", "Pré", "Champs", "Colline", "Montagne", "Forêt", "Pré", "Champs", "Colline", "Montagne"));
        plateau = new Case[6][6];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (i == 0 || j == 0 || i == 5 || j == 5)
                    plateau[i][j] = new Case(0, "Maritime");
                else {
                    int r = new Random().nextInt(jeton.size());
                    if (i != 3 || j != 2)
                        plateau[i][j] = new Case(jeton.remove(r), environment.remove(r));
                    else
                        plateau[i][j] = new Case(0, "Désert");
                }
            }
        }

        Communication c = new Communication();

        int nbJoueurs = c.demanderNombreJoueurs();
        listJoueurs = new LinkedList<>();
        for (int i = 0; i < nbJoueurs; i++)
            listJoueurs.add(c.demanderJoueurs(i,this));
    }

    public void deuxPremiersTour() {

    }

    public void tour() {

    }

    public void repartionRessource(int resultatDe) {
        
    }

    public Case[][] getPlateau() {
        return plateau;
    }

}
