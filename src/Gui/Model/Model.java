package Gui.Model;

import Plateau.Plateau;
import Plateau.Composants.Voleur;
import Plateau.Infrastructures.Route;
import java.util.LinkedList;
import Plateau.Infrastructures.Colonie;
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

    public LinkedList<Joueur> getListJoueurs() {
        return plateau.getListJoueurs();
    }
    public int getnbJoueur() {
        int i=0;
        for(Joueur j : plateau.getListJoueurs()) {
            i+=1;
        }
        return i;
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

   /* public void ChangerTour(){
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
    }*/

    public void deuxPremiersTour() {
        for (Joueur j : joueurs) {
            Colonie c = new Colonie(j, false, plateau);
            c.placerPremierTour();
            Route r = new Route(j, plateau);
            r.placerPremierTours(c);
        }
        for (int i = joueurs.size() - 1; i >= 0; i--) {
            Colonie c = new Colonie(joueurs.get(i), false, plateau);
            c.placerPremierTour();
            Route r = new Route(joueurs.get(i), plateau);
            r.placerPremierTours(c);
        }
    }

    public void jouerTour() {
       // controler.jouerTour();
    }

    public int lancerDe(){
        this.de=joueur_actuel.LancerDe();
        plateau.repartirRessource(de);
        //controler.repaint();
        if(de ==7){
            //controler.perdreRessource()
        }
        return de;
    }

    public int getDe(){
        return de;
    }
}
