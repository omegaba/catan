package Plateau.Infrastructures;

import Joueur.Joueur;
import Plateau.Plateau;
import Plateau.Composants.Case;

import java.util.LinkedList;
import java.util.Scanner;

public class Colonie {
	private Joueur joueur;
	private Plateau plateau;
	private int nbrRessource;
	private LinkedList<Case> case_adja;
	private boolean isVille;

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
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println("Où voulez-vous placer votre colonie");
		String CaseLocation = sc.nextLine();
		String[] tab = decoupe(CaseLocation);
		int i = Integer.parseInt(tab[0]);
		int j = Integer.parseInt(tab[1]);
		Case c = plateau[i][j];
		if(!c.estEmplacementLibre()){
			System.out.println("Cet emplacement est déjà occupé");
			//faire en sorte que le joueur puisse choisir un autre emplacement;
		}
		else{
			System.out.println(
				"Dans quelle partie de la case voulez-vous placer votre colonie ?  haut gauche ?  bas gauche ? haut droit ? bas droit ?");
			String location = sc.nextLine();
			String[] decoupe = decoupe(location);
			String locationHorizontale = decoupe[0];
			String locationVerticale = decoupe[1];
			LinkedList<Case> list = new LinkedList<>();
				for (var v : c.getMap().entrySet()) {
					if (v.getKey().equals(location) || v.getKey().equals(locationHorizontale)
						|| v.getKey().equals(locationVerticale)) {
						list.add(v.getValue());
					}
				}
				plateau[i][j].setColonie(this);
				this.case_adja = list;
		}
	}
	
	public boolean hasColonie(String str){
		String[] decoupe =decoupe(str);
		int i=Integer.parseInt(decoupe[0]);
		int j=Integer.parseInt(decoupe[1]);
			if(!plateau[i][j].hasColonie()){
				return false;
			}
		return true;
	}
	
	public void remplaceColonie(String location, Colonie c){
		String[] decoupe =decoupe(str);
		int i=Integer.parseInt(decoupe[0]);
		int j=Integer.parseInt(decoupe[1]);
			plateau[i][j].setColonie(c);
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

}
