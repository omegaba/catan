package Gui.Model;

import Plateau.Plateau;
import Plateau.Composants.Voleur;

import java.util.LinkedList;

import Carte.Developpement.CarteDeveloppement;
import Joueur.Joueur;

public class Model {
    private final Plateau plateau;
    int tour;
    private LinkedList<Joueur> joueurs;
    private Joueur joueur_actuel;
    private int de;

    public Model() {
        this.plateau = new Plateau();
        this.joueurs.add(new Joueur("J1", false, null, null));
        this.joueurs.add(new Joueur("J2", false, null, null));
        joueur_actuel = plateau.getListJoueurs().get(0);
        de = 0;
        tour = 1;

    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public Joueur getJoueur() {
        return joueur_actuel;
    }

    public void getJoueurActuel(Joueur actuel) {
        this.joueur_actuel = joueur_actuel;
    }

    public void setJoueurActuel() {
        if (tour == 1 || tour>2) {
            for (int i = 0; i < joueurs.size(); i++) {
                if (joueur_actuel == joueurs.get(i)) {
                    joueur_actuel = joueurs.get(i + 1);
                    break;
                }
            }
        }
        if(tour == 2) {
            for (int i = joueurs.size(); i >= 0; i--) {
                if (joueur_actuel == joueurs.get(i)) {
                    joueur_actuel = joueurs.get(i - 1);
                    break;
                }
            }
        }
    }

    public void ChangerTour(){
        if(joueur_actuel.getPoints() >= 10){
            //
        }
        else{
            setJoueurActuel();
            tour++;
            if(tour <= plateau.getNombreJoueur()) jouerPremierTour();
            else if(3
            )
        }
    }

    public void jouerPremierTour(){

    }
}
