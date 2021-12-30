package Plateau.Infrastructures;
import Joueur.Joueur;
import Plateau.Composants.Case;
import java.util.Scanner;

public class Colonie {
	private Joueur joueur;
	private Plateau plateau;
	private int nbrRessource;
	private LinkedList<Case> case_adja;
	private boolean isVille;

	public Colonie(Joueur j, boolean isVille, PLateau p){
		this.joueur=j;
		this.isVille=false;
		this.case_adja=null;
		this.plateau =p;
		this. nbrRessource=1;
	}
	
	public void upgrade(){
		this.isVille=true;
		this.nbrRessource=2;
	}
	
	public void decoupe(String str){
		String [] tab= str.split(" ");
		return str;
	}
	
	
	public void placer(){
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		System.out.pritnln("Où voulez-vous placer votre colonie");
		String CaseLocation=sc.nextLine();
		String []tab=decoupe(CaseLocation);
		int i=Integer.parseInt(tab[0]);
		int j=Integer.parseInt(tab[1]);
		System.out.println("Dans quelle partie de la case voulez-vous placer votre colonie ?  haut gauche ?  bas gauche ? haut droit ? bas droit ?");
		String location =sc.nextLine();
		String [] decoupe=decoupe(location);
		String locationHorizontale=decoupe[0];
		String locationVerticale=decoupe[1];
		LinkedList <Case> list=new LinkedList<>();
		Case c= plateau[i][j];
			for(var v : c.getMap().entrySet()){
				if(v.getKey().equals(location) || v.getKey().equals(locationHorizontale) || v.getKey().equals(locationVerticale)){
					list.add(v.getValue());
				}
			}
		this.case_adja=list;
	}
	
	public Joueur getJoueur(){
		return this.joueur;
	}
	
	
	public boolean getIsVille(){
		return this.isVille;
	}
	
	public LinkedList getCaseAdja(){
		return this.case_adja;
	}
	
	
}
