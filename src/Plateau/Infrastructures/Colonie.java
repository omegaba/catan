package Plateau.Infrastructures;
import Joueur.Joueur;
import Plateau.Composants.Case;

public class Colonie {
	private Joueur joueur;
	private Case[] case_adjacente;
	private boolean isVille;

	public Colonie(Joueur j, boolean isVille){
		this.joueur=j;
		this.isVille=false;
		this.case_adjacente=null;
	}
	
	public void setCase(){
	}
	
	public Joueur getJoueur(){
		return this.joueur;
	}
	
	public void upgrade(){
		this.isVille=true;
		setCase();
	}
	
	public boolean colonieNear(){ //renvoie vrai si une des routes d'une des case n'est pas nul est correspond à une route du joueur;
		for(int i=0; i< case_adjacente.length; i++){
			if((case_adjacente[i].getRoute("nord") !=null ||	case_adjacente[i].getRoute("sud") !=null || case_adjacente[i].getRoute("est") !=null || case_adjacente[i].getRoute("ouest") !=null) && (case_adjacente[i].getRoute("nord").getJoueur().getNom.equals(this.Joueur.getNom() || case_adjacente[i].getRoute("sud").getJoueur().getNom.equals(this.Joueur.getNom()|| case_adjacente[i].getRoute("est").getJoueur().getNom.equals(this.Joueur.getNom() ||case_adjacente[i].getRoute("ouest").getJoueur().getNom.equals(this.Joueur.getNom())){
			return true;
			}
		}
		return false;
	}
	
	
	public boolean getIsVille(){
		return this.isVille;
	}
	
	public void placer(){
	}
	
	
}
