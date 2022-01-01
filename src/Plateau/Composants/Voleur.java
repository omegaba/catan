 package Plateau.Composants;
 import Plateau.Composants.Case;
 import Plateau.Plateau;

import java.util.LinkedList;
import java.util.Scanner;
 
 public class Voleur{
 	private Case emplacement;
 	
 	public Voleur(Case c){
 		this.emplacement=c;
 	}
 	
 	public Case getEmplacement(){
 		return this.emplacement;
 	}
 	
 	public String[] decoupe(String str) {
		String[] tab = str.split(" ");
		return tab;
	}
 	
 	public void placer(){
 	Scanner sc = new Scanner(System.in);
	String s = sc.nextLine();
 	System.out.println("Donnez les coordonnées de la case  où vous voulez placer le voleur");
 	String CaseLocation = sc.nextLine();
		String[] tab = decoupe(CaseLocation);
		int i = Integer.parseInt(tab[0]);
		int j = Integer.parseInt(tab[1]);
		Case c = plateau[i][j];
 		this.emplacement=c;
 		c.setVoleur();
 	}
 	
 }
