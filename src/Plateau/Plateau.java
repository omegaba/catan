package Plateau;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import Jeu.AffichageText;
import Jeu.Communication;
import Joueur.Joueur;
import Plateau.Composants.Case;
import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.PortSpecialise;

public class Plateau {

    private Case[][] plateau;
    private LinkedList<Joueur> listJoueurs;
    private int numeroJoueurActuel;
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

        Communication c = new Communication();

        int nbJoueurs = c.demanderNombreJoueurs();
        listJoueurs = new LinkedList<>();
        for (int i = 0; i < nbJoueurs; i++)
            listJoueurs.add(c.demanderJoueurs(i, this));

        numeroJoueurActuel = 0;
    }

    public void deuxPremiersTour(AffichageText a) {

    }

    public void tour() {
        System.out.println("Tour de :");
        listJoueurs.get(numeroJoueurActuel).affiche();
        listJoueurs.get(numeroJoueurActuel).actionEffectuer();
        if (++numeroJoueurActuel == listJoueurs.size())
            numeroJoueurActuel = 0;
    }

    public void repartionRessource(int resultatDe) {

    }

    public boolean partiFini() {
        for (Joueur j : listJoueurs)
            if (j.getPoints() >= 10)
                return true;
        return false;
    }

    public Case[][] getPlateau() {
        return plateau;
    }
    
    public Voleur getVoleur(){
    	return this.voleur();
    }
    
    
    void afficheJoueur(){
    	for (Joueur j: listJoueurs){
    		for(Colonie c : j.getColonie()){
    			for(var v : j.getColonie().getCaseAdja().entrySet()){
    				if(v.getValue().hasVoleur()){
    					j.affiche+"\n";
    				}
    			}	
    		}
    	}
    }

}
