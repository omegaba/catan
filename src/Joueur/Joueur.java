package Joueur;

import java.util.LinkedList;

import Carte.Ressources.CarteRessources;
import Plateau.Infrastructures.Route;

public class Joueur {
    private final String nom;
    private final boolean ai;
    private int points;
    private LinkedList<Route> listeRoutes;
    private LinkedList<Object> listeVilles;
    private LinkedList<CarteRessources> deckCarteRessources;

    public Joueur(String nom, boolean ai, String couleur) {
        this.nom = nom;
        this.ai = ai;
        points = 0;
        listeRoutes = new LinkedList<>();
        listeVilles = new LinkedList<>();
        deckCarteRessources = new LinkedList<>();
    }
}
