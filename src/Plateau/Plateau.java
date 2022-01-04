package Plateau;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Map.Entry;

import Carte.Developpement.CarteDeveloppement;
import Carte.Developpement.Chevalier;
import Carte.Developpement.PointDeVictoire;
import Carte.Developpement.Progres.ProgresInvention;
import Carte.Developpement.Progres.ProgresMonopole;
import Carte.Developpement.Progres.ProgresRoute;
import Jeu.AffichageText;
import Jeu.Communication;
import Joueur.Joueur;
import Plateau.Composants.Case;
import Plateau.Infrastructures.Colonie;
import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.PortSpecialise;
import Plateau.Infrastructures.Route;
import Plateau.Composants.Voleur;

public class Plateau {

    private Case[][] plateau;
    private LinkedList<Joueur> listJoueurs;
    private int numeroJoueurActuel;
    private boolean partiFini;
    private LinkedList<CarteDeveloppement> pileCarteDeveloppement;
    private Voleur voleur;

    public Plateau() {
        int nbPortNormal = 3;
        int nbPortPlacer = 0;
        LinkedList<Integer> jeton = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 12));
        LinkedList<String> environment = new LinkedList<>(Arrays.asList("Forêt", "Pré", "Champs", "Colline", "Montagne",
                "Forêt", "Pré", "Champs", "Colline", "Montagne", "Forêt", "Pré", "Champs", "Colline", "Montagne"));
        LinkedList<String> listRessources = new LinkedList<>(
                Arrays.asList("Argile", "Ble", "Bois", "Laine", "Minerai"));
        plateau = new Case[6][6];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (i == 0 || j == 0 || i == 5 || j == 5) {
                    plateau[i][j] = new Case(0, "Maritime");
                    if (nbPortNormal-- > 0 && nbPortPlacer++ % 2 == 0) {
                        plateau[i][j].setPort(new Port(plateau[i][j]));
                    } else
                        plateau[i][j].setPort(new PortSpecialise(plateau[i][j], listRessources.removeFirst()));
                } else {
                    int r = new Random().nextInt(jeton.size());
                    if (i != 3 || j != 2)
                        plateau[i][j] = new Case(jeton.remove(r), environment.remove(r));
                    else
                        plateau[i][j] = new Case(0, "Désert");
                }
            }
        }

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (i == 0 && j == 0) {
                    plateau[i][j].getMap().put("haut gauche", null);
                    plateau[i][j].getMap().put("haut", null);
                    plateau[i][j].getMap().put("haut droit", null);
                    plateau[i][j].getMap().put("droit", plateau[i][j + 1]);
                    plateau[i][j].getMap().put("bas droit", plateau[i + 1][j + 1]);
                    plateau[i][j].getMap().put("bas", plateau[i + 1][j]);
                    plateau[i][j].getMap().put("bas gauche", null);
                    plateau[i][j].getMap().put("gauche", null);
                } else if (i == 0 && j == plateau[i].length - 1) {
                    plateau[i][j].getMap().put("haut gauche", null);
                    plateau[i][j].getMap().put("haut", null);
                    plateau[i][j].getMap().put("haut droit", null);
                    plateau[i][j].getMap().put("droit", null);
                    plateau[i][j].getMap().put("bas droit", null);
                    plateau[i][j].getMap().put("bas", plateau[i + 1][j]);
                    plateau[i][j].getMap().put("bas gauche", plateau[i + 1][j - 1]);
                    plateau[i][j].getMap().put("gauche", plateau[i - 1][j]);
                } else if (i == 0) {
                    plateau[i][j].getMap().put("haut gauche", null);
                    plateau[i][j].getMap().put("haut", null);
                    plateau[i][j].getMap().put("haut droit", null);
                    plateau[i][j].getMap().put("droit", plateau[i][j + 1]);
                    plateau[i][j].getMap().put("bas droit", plateau[i + 1][j + 1]);
                    plateau[i][j].getMap().put("bas", plateau[i + 1][j]);
                    plateau[i][j].getMap().put("bas gauche", plateau[i + 1][j - 1]);
                    plateau[i][j].getMap().put("gauche", plateau[i][j - 1]);
                } else if (i == plateau.length - 1 && j == 0) {
                    plateau[i][j].getMap().put("haut gauche", null);
                    plateau[i][j].getMap().put("haut", plateau[i - 1][j]);
                    plateau[i][j].getMap().put("haut droit", plateau[i - 1][j + 1]);
                    plateau[i][j].getMap().put("droit", plateau[i][j + 1]);
                    plateau[i][j].getMap().put("bas droit", null);
                    plateau[i][j].getMap().put("bas", null);
                    plateau[i][j].getMap().put("bas gauche", null);
                    plateau[i][j].getMap().put("gauche", null);
                } else if (i == plateau.length - 1 && j == plateau[i].length - 1) {
                    plateau[i][j].getMap().put("haut gauche", plateau[i - 1][j - 1]);
                    plateau[i][j].getMap().put("haut", plateau[i - 1][j]);
                    plateau[i][j].getMap().put("haut droit", null);
                    plateau[i][j].getMap().put("droit", null);
                    plateau[i][j].getMap().put("bas droit", null);
                    plateau[i][j].getMap().put("bas", null);
                    plateau[i][j].getMap().put("bas gauche", null);
                    plateau[i][j].getMap().put("gauche", plateau[i][j - 1]);
                } else if (i == plateau.length - 1) {
                    plateau[i][j].getMap().put("haut gauche", plateau[i - 1][j - 1]);
                    plateau[i][j].getMap().put("haut", plateau[i - 1][j]);
                    plateau[i][j].getMap().put("haut droit", plateau[i - 1][j + 1]);
                    plateau[i][j].getMap().put("droit", plateau[i][j + 1]);
                    plateau[i][j].getMap().put("bas droit", null);
                    plateau[i][j].getMap().put("bas", null);
                    plateau[i][j].getMap().put("bas gauche", null);
                    plateau[i][j].getMap().put("gauche", plateau[i][j - 1]);
                } else if (j == 0) {
                    plateau[i][j].getMap().put("haut gauche", null);
                    plateau[i][j].getMap().put("haut", plateau[i - 1][j]);
                    plateau[i][j].getMap().put("haut droit", plateau[i - 1][j + 1]);
                    plateau[i][j].getMap().put("droit", plateau[i][j + 1]);
                    plateau[i][j].getMap().put("bas droit", plateau[i + 1][j + 1]);
                    plateau[i][j].getMap().put("bas", plateau[i + 1][j]);
                    plateau[i][j].getMap().put("bas gauche", null);
                    plateau[i][j].getMap().put("gauche", null);
                } else if (j == plateau[i].length - 1) {
                    plateau[i][j].getMap().put("haut gauche", plateau[i - 1][j - 1]);
                    plateau[i][j].getMap().put("haut", plateau[i - 1][j]);
                    plateau[i][j].getMap().put("haut droit", null);
                    plateau[i][j].getMap().put("droit", null);
                    plateau[i][j].getMap().put("bas droit", null);
                    plateau[i][j].getMap().put("bas", plateau[i + 1][j]);
                    plateau[i][j].getMap().put("bas gauche", plateau[i + 1][j - 1]);
                    plateau[i][j].getMap().put("gauche", plateau[i][j - 1]);
                } else {
                    plateau[i][j].getMap().put("haut gauche", plateau[i - 1][j - 1]);
                    plateau[i][j].getMap().put("haut", plateau[i - 1][j]);
                    plateau[i][j].getMap().put("haut droit", plateau[i - 1][j + 1]);
                    plateau[i][j].getMap().put("droit", plateau[i][j + 1]);
                    plateau[i][j].getMap().put("bas droit", plateau[i + 1][j + 1]);
                    plateau[i][j].getMap().put("bas", plateau[i + 1][j]);
                    plateau[i][j].getMap().put("bas gauche", plateau[i + 1][j - 1]);
                    plateau[i][j].getMap().put("gauche", plateau[i][j - 1]);
                }

            }
        }

        Communication c = new Communication();

        int nbJoueurs = c.demanderNombreJoueurs();
        listJoueurs = new LinkedList<>();
        for (int i = 0; i < nbJoueurs; i++)
            listJoueurs.add(c.demanderJoueurs(i, this));

        numeroJoueurActuel = 0;
        partiFini = false;

        pileCarteDeveloppement = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            pileCarteDeveloppement.add(new PointDeVictoire(null));
        }
        for (int i = 0; i < 3; i++) {
            pileCarteDeveloppement.add(new ProgresRoute(null));
        }
        for (int i = 0; i < 3; i++) {
            pileCarteDeveloppement.add(new ProgresMonopole(null, this));
        }
        for (int i = 0; i < 3; i++) {
            pileCarteDeveloppement.add(new ProgresInvention(null));
        }
        for (int i = 0; i < 11; i++) {
            pileCarteDeveloppement.add(new Chevalier(null));
        }

        Collections.shuffle(pileCarteDeveloppement);
    }

    public void deuxPremiersTour(AffichageText a) {
        for (Joueur j : listJoueurs) {
            Colonie c = new Colonie(j, false, this);
            c.placerPremierTour();
            Route r = new Route(j);
            r.placer();
            a.affiche();
        }
        for (int i = listJoueurs.size() - 1; i >= 0; i--) {
            Colonie c = new Colonie(listJoueurs.get(i), false, this);
            c.placerPremierTour();
            Route r = new Route(listJoueurs.get(i));
            r.placer();
            a.affiche();
        }
    }

    public void tour() {
        System.out.println("Tour de :");
        listJoueurs.get(numeroJoueurActuel).affiche();
        listJoueurs.get(numeroJoueurActuel).actionEffectuer();
        listJoueurs.get(numeroJoueurActuel).calculPoints();
        if (listJoueurs.get(numeroJoueurActuel).getPoints() >= 10)
            partiFini = true;
        if (++numeroJoueurActuel == listJoueurs.size())
            numeroJoueurActuel = 0;
    }

    public void repartirRessource(int resultatDe) {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j].getNumero() == resultatDe) {
                    HashMap<String, Colonie> listColonieCase = plateau[i][j].getColonie();
                    for (var c : listColonieCase.entrySet()) {
                        c.getValue().getJoueur().recevoirRessource(plateau[i][j].getRessource(),
                                c.getValue().getNbrRessource());
                    }
                }
            }
        }
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public boolean getPartiFini() {
        return partiFini;
    }

    public LinkedList<Joueur> getListJoueurs() {
        return listJoueurs;
    }

    public LinkedList<CarteDeveloppement> getPileCarteDeveloppement() {
        return pileCarteDeveloppement;
    }
    
    
    public Voleur getVoleur(){
    	return this.voleur();
    }
    
    
    public void afficheJoueur(){
    	for (Joueur j: listJoueurs){
    		for(Colonie c : j.getColonie()){
    			for(var v: c.getCaseAdja().entrySet()){
    				if((v.getValue().hasVoleur())){
    					j.affiche();
    				}
    			}	
    		}
    	}
    }

    public LinkedList<Joueur> getlistJoueur() {
        return  listJoueurs;
    }

}
