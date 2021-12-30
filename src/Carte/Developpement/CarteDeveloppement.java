package Carte.Developpement;

import Carte.Carte;

public class CarteDeveloppement extends Carte {
    private String nom;

    public CarteDeveloppement(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return ("Carte developpement :" + this.nom);
    }

}
