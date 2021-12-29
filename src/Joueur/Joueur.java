package Joueur;

import java.util.LinkedList;

import Jeu.CarteRessources;
import Plateau.Infrastructures.Route;
import java.util.*;
public class Joueur {
    private boolean carteDev; //dit si le joueur à utilier une carte developpement ou non durant son tour;
    private final String nom;
    private final boolean ai;
    private int points;
    public  int Id=0;
    public String couleur;
    private LinkedList<Piece> piece;
    private LinkedList<CarteRessources> deckCarteRessources;
    private LInkedeList<CartesDeveloppemen> deckCarteDeveloppement;
    

    Joueur(String nom, boolean ai, String couleur) {
        this.nom = nom;
        this.ai = ai;
        this.couleur=couleur;
        points = 0;
        piece=new LinkedList<>();
        deckCarteRessources = new LinkedList<>();
        deckCarteDeveloppement= new LinkedList<>();
        Id++;
    }
    
    public void affiche(){
    System.out.println("Nom :" +this.nom+ ", couleur " + this.couleur);
    System.out.println("Nombre de point : "+ this.points);
    System.out.print("Cartes développement: ");
    	for(CarteDeveloppement c: deckCarteDeveloppement){
    		c.affiche(); 
    	}
    System.out.println("Cartes Ressources: ");
    	for(CarteRessources r: deckCarteRessources){
    		r.affiche(); 
    	}
    }
    
    public int LancerDe(){
    	Random rd= new Random();
    	int val = rd.nextInt(11) + 2;
		return val;
	}
	
	public int nbRessourceArgile(){
	int r=0;
		for(CartesRessource r : deckCarteRessource){
			if(r instanceof Argile){
				r+=1;
			}
		}
	return r;
	}
	
	public int nbRessourceBois(){
	int r=0;
		for(CartesRessource r : deckCarteRessource){
			if(r instanceof Bois){
				r+=1;
			}
		}
	return r;
	}
	
	public int nbRessourceLaine(){
	int r=0;
		for(CartesRessource r : deckCarteRessource){
			if(r instanceof Laine){
				r+=1;
			}
		}
	return r;
	}
	
	public int nbRessourceBle(){
	int r=0;
		for(CartesRessource r : deckCarteRessource){
			if(r instanceof Ble){
				r+=1;
			}
		}
	return r;
	}
	
	public int nbRessourceMinerai(){
	int r=0;
		for(CartesRessource r : deckCarteRessource){
			if(r instanceof Minerai){
				r+=1;
			}
		}
	return r;
	}
	
	
	public void FicheCoutConstruction(){
		int nbArgile=nbRessourceArgile();
		int nbBois= nbRessourceBois();
		int nbLaine=nbRessourceLaine();
		int nbBle=nbRessourceBle();
		int nbMinerai=nbRessourceMinerai();
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		while(s.hasNextLine()){
		System.out.println("Voulez-vous connaître le coût de construction d'une Route(r), d'une Colonie(c), ou d'une Ville(v) ?");
		s=s.nextLine();
		if(s.equals("Route")){ 
			System.out.println("Matériaux nécéssaires: Argile(1) , Bois(1)");
			System.out.println();
			System.out.println("Matérielle en possession: Argile("+nbArgile+"), Bois("+ nbBois+")");
		}
		if(s.equals("Colonie")){ 
			System.out.println("Matériaux nécéssaires: Argile(1) , Bois(1), Laine(1), Blé(1)");
			System.out.println();
			System.out.println("Matérielle en possession: Argile("+nbArgile+"), Bois("+ nbBois+"), Laine("+nbLaine+"), Blé("+nbBle+")");
		}
		if(s.equals("Ville")){ 
			System.out.println("Matériaux nécéssaires: Minerai(3) , Blé(2)");
						System.out.println();
			System.out.println("Matérielle en possession: Minerai("+nbMinerai+"), Blé("+nbBle+")");
						System.out.println();
			System.out.println("Attention, vous devez posséder une colonie pour pouvoir construire une ville !!!");
		}
		}
	}
	
	public void actionAeffectuer(){
		// ChoixAction(): methode qui  à l'aide d'un scanner demande à l'utilisateur si il veut construire une route (cr), une colonie(cc), ou une ville(cv), ou bien utiliser une carte developpement(cd) ou bien faire du commerce
		//il serait peut être mieux de ne pas coder choix victoire
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		while(s.hasNextLine()){
		System.out.println("Voulez-vous construire une Route(cr), une colonie(cc), une ville'cv), faire du commerce, ou utiliser une carte développement(cd) ?");
		s=s.nextLine();
		if(s.equals("cr")){
		ConstruireRoute();
		}
		if(s.equals("cc")){
		ConstruireColonie();
		}
		if(s.equals("cv")){
		ConstruireVille();
		}
		if(s.equals("commerce")){
		Commercer();
		}
		if(s.equals("cd")){
			System.out.println("Voulez-vous utiliser une Carte Victoire(victory), une carte chevalier(chevalier), ou une carte Progrès(cp) ?");
			String tmp=s.nextLine();
			if(tmp.equals("victory")){
			//à decider
			}
			if(tmp.equals("chevalier")){
				DeplaceVoleur();
			}
			if(tmp.equals("cp")){
				Random rd= new Random();
				int r=rd.nextInt(3);
				switch (r){
				case 0:  System.out.println("Vous avez tiré au sort une carte de construction de route, vous pouvez en construire 2 !!");
				ConstruireRoute();
				case 1: System.out.println("Vous avez tiré au sort une carte invention !!");
					//a finir, il faut choisir deux ressources et les ajouter aux decks, peut-être avec une fonction qui prend en parametre une String et un entier
				case 2: System.out.println("Vous avez tiré au sort une carte monopole !! Veuillez designer la ressource souhaitée :");
				//a finir, avec un switch de préférence (ou pas)
				}
		}
		}
	}
	
	public void recevoirRessource(String ressource, int n){
	for(int i=0; i< n; i++){
		deckCarteRessources.add(new CarteRessource(ressource));
	}
	}
	
	public void perdreRessource(String ressource, int n){
	for(int i=0; i<n; i++){
		for(CarteRessource c : deckCarteRessources){
			if(c instanceof ressource){
				deckCarteRessources.remove(c);
			}
		}
	}
	}
	
	public void Commercer(){
	
	}
	
	
	
	
}
