package Jeu;

public class CarteRessources extends Carte {
    private String nom;

    public CarteRessources(String nom) {
        this.nom = nom;
    }

    public void affiche() {
        System.out.println("CarteRessources " + this.nom);
    }

}
