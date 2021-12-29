package Carte.Speciale;

import Carte.Carte;

public class CarteSpeciale extends Carte {
    private String nom;

    public CarteSpeciale(String nom) {
        this.nom = nom;
    }

    public void affiche() {
        System.out.println("Carte spéciale :" + this.nom);
    }
}
