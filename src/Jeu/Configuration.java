package Jeu;

import java.util.List;
import java.util.LinkedList;
import java.lang.Cloneable;

import Joueur.Ia;
import Joueur.Joueur;

import java.util.Map;

public class Configuration implements Cloneable {

    private final List<Joueur> listJoueurs;
    private int nbJoueur;
    private String ConfigName;
    private Joueur joueur;

    public Configuration(Joueur j, String nameConfig) {
        this.joueur = j;
        this.ConfigName = nameConfig;
        this.listJoueurs = new LinkedList<Joueur>();
    }

    public void ajouterJoueur(String nom) {
        listJoueurs.add(new Joueur(nom, null, null));
        nbJoueur++;
    }

    public void ajouteIA(String nom) {
        listJoueurs.add(new Ia(nom, null, null));
        nbJoueur++;
    }

    public void addIA() {
        listJoueurs.add(new Ia(null, null, null));
        nbJoueur++;
    }

    public Configuration clone() {
        Configuration newConfig = new Configuration(null, ConfigName);
        for (Joueur j : this.listJoueurs) {
            newConfig.ajouterJoueur(j.getNom());
        }
        return newConfig;
    }

    public void setConfigName(String newName) {
        this.ConfigName = newName;
    }

    public boolean ConfigFini() {
        if (this.listJoueurs.size() == 3) {
            this.nbJoueur = 3;
            return true;
        }
        if (this.listJoueurs.size() == 4) {
            this.nbJoueur = 4;
            return true;
        }
        return false;
    }

    public void affiche() {
        for (Joueur j : this.listJoueurs) {
            j.affiche();
        }
    }

    public List<Joueur> getListJoueurs() {
        return this.listJoueurs;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public String getNameConfig() {
        return this.ConfigName;
    }

    public Joueur getOwner() {
        return this.joueur;
    }

    public void setnbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

}
