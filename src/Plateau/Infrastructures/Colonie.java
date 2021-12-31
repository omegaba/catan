package Plateau.Infrastructures;

import Joueur.Joueur;
import Plateau.Plateau;
import Plateau.Composants.Case;

import java.util.LinkedList;
import Jeu.Communication;

public class Colonie {
	private Joueur joueur;
	private Plateau plateau;
	private int nbrRessource;
	private LinkedList<Case> case_adja;
	private boolean isVille;
	private Communication c;

	public Colonie(Joueur j, boolean isVille, Plateau p) {
		this.joueur = j;
		this.isVille = false;
		this.case_adja = null;
		this.plateau = p;
		this.nbrRessource = 1;
	}

	public void upgrade() {
		this.isVille = true;
		this.nbrRessource = 2;
	}

	public String[] decoupe(String str) {
		String[] tab = str.split(" ");
		return tab;
	}

	public void placer() {
		String CaseLocation = c.choixPlacementColonie();
		String[] tab = decoupe(CaseLocation);
		int i = Integer.parseInt(tab[0]);
		int j = Integer.parseInt(tab[1]);
		String location = c.choixLocationDeLaColonie();
		String[] decoupe = decoupe(location);
		String locationHorizontale = decoupe[0];
		String locationVerticale = decoupe[1];
		LinkedList<Case> list = new LinkedList<>();
		Case c = plateau.getPlateau()[i][j];
		for (var v : c.getMap().entrySet()) {
			if (v.getKey().equals(location) || v.getKey().equals(locationHorizontale)
					|| v.getKey().equals(locationVerticale)) {
				list.add(v.getValue());
			}
		}
		this.case_adja = list;
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public boolean getIsVille() {
		return this.isVille;
	}

	public LinkedList getCaseAdja() {
		return this.case_adja;
	}

	public int getNbrRessource() {
		return this.nbrRessource;
	}

}
