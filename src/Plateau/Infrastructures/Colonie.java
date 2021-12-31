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
	private HashMap<String, Case> case_adja;
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
		Case c = plateau[i][j];
			System.out.println(
				"Dans quelle partie de la case voulez-vous placer votre colonie ?  haut gauche ?  bas gauche ? haut droit ? bas droit ?");
				if(!c.estEmplacementLibre()){
					System.out.println("Cet emplacement est déjà occupé");
					//faire en sorte que le joueur puisse choisir un autre emplacement;
				}
				
				else{
					String location = sc.nextLine();
					String[] decoupe = decoupe(location);
					String locationHorizontale = decoupe[1];
					String locationVerticale = decoupe[0];
					HashMap<String, Case> map=new HashMap<>();
					String  inverseLh, inverseLv;
						if(locationHorizontale.equals("gauche"){
							inverseLh="droite";
						}
						else{
							inverseLh="gauche";
						}
						
						if(locationVerticale.equals("bas"){
							inverseLv="haut";
						}
						else{
							inverseLv="bas";
						}
						
						
						for (var v : c.getMap().entrySet()) {
							if(v.getKey().equals(location)){
								map.put(location,v.getValue());
							}
							if(v.getKey().equals(locationHorizontale)){
								map.put(inverseLv+" "+locationHorizontale,v.getValue());
							}
							if(v.getKey().equals(locationVerticale)){
								map.put(locationVerticale+" "+inverseLh,v.getValue());
							}
						}
				
						if(locationHorizontale.equals("haut") && locationVerticale.equals("gauche")){
							map.put("bas droit", c);
						}
						if(locationHorizontale.equals("bas") && locationVerticale.equals("gauche")){
							map.put("haut droit",c);
						}
						if(locationHorizontale.equals("haut") && locationVerticale.equals("droit")){
							map.put("bas gauche",c);
						}
						if(locationHorizontale.equals("bas",c) && locationVerticale.equals("droit")){
							map.put("haut gauche",c);
						}
				
					setMapColonie();
					this.case_adja=map;
				}

		}
	
	/*public boolean hasColonie(String str){
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
	}*/
	
	public void setMapColonie(int x, int y){
			for( var v : case_adja.entrySet()){
				switch(v.getKey()){
					case "haut gauche":
						v.getValue().getMapColonie().put("bas droit", this);
						break;
					case "bas droit":
						v.getValue().getMapColonie().put("haut gauche", this);
						break;
					case "bas gauche":
						v.getValue().getMapColonie().put("haut droit", this);
						break;
						
					case "haut droit":
						v.getValue().getMapColonie().put("bas gauche", this);
						break;
				}
			}
				
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
	
	String CaseDescription(){
	String Str="";
		for(var v : this.case_adja.entrySet()){
			Str+= v.getValue().getEnvironnement()+ " numéro " + v.getValue().getNumero()+"\n";
		}
		return Str;
	}
	
	public String toString(){
	String adjacents=CaseDescription();
	String ColonieOuVille;
		if(isVille) {
			ColonieOuVille="Ville";
		}else{
			ColonieOuVille="Colonie";
		}
	return "Colonie du joueur "+this.joueur.getNom()+
	" Est une "+ColonieOuVille+
	" Située entre "+ adjacents;
	}
	

	public int getNbrRessource() {
		return this.nbrRessource;
	}

}
