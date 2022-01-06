package Gui.Model;

import Plateau.Plateau;
import Plateau.Composants.Voleur;
import Plateau.Infrastructures.Route;
import java.util.LinkedList;
import Plateau.Infrastructures.Colonie;
import Carte.Developpement.CarteDeveloppement;
import Gui.Controleur.Controleur;
import Jeu.Configuration;

import Gui.Vue.*;
import Joueur.Ia;
import Joueur.Joueur;

public class Model {
    private final Plateau plateau;
    int tour;
    private LinkedList<Joueur> joueurs;
    private Joueur joueur_actuel;
    private int de;
    private Controleur controleur;
    private Vue vue;

    public Model() {
        this.plateau = new Plateau();
        this.joueurs.add(new Joueur("J1", null, null));
        this.joueurs.add(new Joueur("J2", null, null));
        joueur_actuel = plateau.getListJoueurs().get(0);
        de = 0;
        tour = 1;
    }

    public void ajouterJoueur(String nom) {
        this.plateau.getListJoueurs().add(new Joueur(nom, null, null));
    }

    public void ajouterIA(String nom) {
        plateau.getListJoueurs().add(new Ia(nom, null, null));
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public boolean listCardDevVide() {
        return this.plateau.getPileCarteDeveloppement().isEmpty();
    }

    public Vue getVue() {
        return this.vue;
    }

    public Joueur getJoueur() {
        return joueur_actuel;
    }

    public Joueur getJoueurActuel(Joueur actuel) {
        return this.joueur_actuel;
    }

    public LinkedList<Joueur> getListJoueurs() {
        return plateau.getListJoueurs();
    }

    public int getnbJoueur() {
        int i = 0;
        for (Joueur j : plateau.getListJoueurs()) {
            i += 1;
        }
        return i;
    }

    public void setJoueurActuel() {
        if (tour == 1 || tour > 2) {
            for (int i = 0; i < joueurs.size(); i++) {
                if (joueur_actuel == joueurs.get(i)) {
                    joueur_actuel = joueurs.get(i + 1);
                    break;
                }
            }
        }
        if (tour == 2) {
            for (int i = joueurs.size(); i >= 0; i--) {
                if (joueur_actuel == joueurs.get(i)) {
                    joueur_actuel = joueurs.get(i - 1);
                    break;
                }
            }
        }
    }

    /*
     * public void ChangerTour(){
     * if(joueur_actuel.getPoints() >= 10){
     * //
     * }
     * else{
     * setJoueurActuel();
     * tour++;
     * if(tour <= plateau.getNombreJoueur()) jouerPremierTour();
     * else if(3
     * )
     * }
     * }
     */

    /*
     * public void deuxPremiersTour() {
     * for (Joueur j : listJoueurs) {
     * System.out.println("Tour de:");
     * j.affiche();
     * Colonie c = new Colonie(j, false, this);
     * if (j instanceof Ia)
     * c.placerPremierTourIa();
     * else
     * c.placerPremierTour();
     * j.getColonie().add(c);
     * Route r = new Route(j, this);
     * r.placerPremierTours(c, j instanceof Ia);
     * a.affiche();
     * j.calculPoints();
     * }
     * for (int i = 0; i < plateau.length; i++) {
     * for (int j = 0; j < plateau[i].length; j++) {
     * System.out.print(plateau[i][j] + "  |||| CASE SUIVANTE ||||");
     * }
     * System.out.println();
     * }
     * for (int i = listJoueurs.size() - 1; i >= 0; i--) {
     * System.out.println("Tour de:");
     * listJoueurs.get(i).affiche();
     * Colonie c = new Colonie(listJoueurs.get(i), false, this);
     * if (listJoueurs.get(i) instanceof Ia)
     * c.placerPremierTourIa();
     * else
     * c.placerPremierTour();
     * listJoueurs.get(i).getColonie().add(c);
     * Route r = new Route(listJoueurs.get(i), this);
     * r.placerPremierTours(c, listJoueurs.get(i) instanceof Ia);
     * listJoueurs.get(numeroJoueurActuel).calculPoints();
     * a.affiche();
     * }
     * }
     */

    /*public void jouerTour() {
        controleur.jouerTour();
    }*/

    public int lancerDe() {
        this.de = joueur_actuel.LancerDe();
        plateau.repartirRessource(de);
        controleur.repaint();
        if (de == 7) {
            // controler.perdreRessource()
        }
        return de;
    }

    public int getDe() {
        return de;
    }

    public void setChevalierPlusPuissant() {
        for (Joueur jo : plateau.getListJoueurs()) {
            if (jo.getNbChevalier() >= 3 && jo.getNbChevalier() > plateau.getNbChevalierMax()) {
                if (plateau.getChevalierLePlusPuissant().getJoueur() != null)
                plateau.getChevalierLePlusPuissant().getJoueur().getCarteSpeciale().remove(plateau.getChevalierLePlusPuissant());
                plateau.setChevalierMax(jo.getNbChevalier());
                plateau.getChevalierLePlusPuissant().setJoueur(jo);
                jo.getCarteSpeciale().add(plateau.getChevalierLePlusPuissant());
            }
        }

    }

    public Voleur getVoleur(){
        return plateau.getVoleur();
    }

   /* public void setNbJoueur(int nbJoueur){

    }*/

   
   /* public void initJeu(){

    }*/


}
