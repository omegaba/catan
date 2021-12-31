package Carte.Developpement.Progres;

import Joueur.Joueur;
import Plateau.Infrastructures.Route;

public class ProgresRoute extends Progres {
    public ProgresRoute(String nom, Joueur j) {
        super(nom, j);
        // TODO Auto-generated constructor stub
    }

    public void construireRoute() {
        for (int i = 0; i < 2; i++) {
            Route r = new Route(super.joueur);
            r.placer();
        }
    }

}
