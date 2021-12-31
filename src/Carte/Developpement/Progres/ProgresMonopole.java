package Carte.Developpement.Progres;

import Carte.Ressources.CarteRessources;
import Jeu.Communication;
import Joueur.Joueur;
import Plateau.Plateau;

public class ProgresMonopole extends Progres {

    private Plateau p;

    public ProgresMonopole(String nom, Joueur j, Plateau p) {
        super(nom, j);
        this.p = p;
        // TODO Auto-generated constructor stub
    }

    void monopole() {
        Communication c = new Communication();
        String ressource = c.choixRessource("Quel ressource voulez-vous obtenir ?");
        for (Joueur j : p.getListJoueurs()) {
            if (j != joueur) {
                int nbR = 0;
                for (CarteRessources cr : j.getDeckCarteRessources()) {
                    if (cr.getNom().equals(ressource))
                        nbR++;
                }
                j.perdreRessource(ressource, nbR);
                joueur.recevoirRessource(ressource, nbR);
            }
        }
    }

}
