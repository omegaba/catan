package Carte.Ressources;

import Carte.Carte;

public class CarteRessources extends Carte {
    private String nom;

    public CarteRessources(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return ("CarteRessources " + this.nom);
    }

    public String getNom() {
        return this.nom;
    }

}
