package Joueur;

import Carte.Developpement.CarteDeveloppement;
import Carte.Ressources.CarteRessources;
import Jeu.Communication;
import Plateau.Plateau;
import Plateau.Infrastructures.Colonie;
import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.PortSpecialise;
import Plateau.Infrastructures.Route;
import java.util.*;

public class Joueur {
	private boolean carteDev; // dit si le joueur à utilier une carte developpement ou non durant son tour;
	private final String nom;
	private final boolean ai;
	private int points;
	private Communication c;
	public String couleur;
	private LinkedList<Colonie> colonie;
	private LinkedList<CarteRessources> deckCarteRessources;
	private LinkedList<CarteDeveloppement> deckCarteDeveloppement;
	private LinkedList<Port> port;
	private Plateau plateau;
	private int nbColonies, nbVilles, nbRoutes;

	public Joueur(String nom, boolean ai, String couleur, Plateau p) {
		this.nom = nom;
		this.ai = ai;
		this.couleur = couleur;
		points = 0;
		deckCarteRessources = new LinkedList<>();
		deckCarteDeveloppement = new LinkedList<>();
		this.plateau = p;
		nbColonies = 5;
		nbVilles = 4;
		nbRoutes = 15;
	}

	public void affiche() {
		System.out.println("Nom :" + this.nom + ", couleur " + this.couleur);
		System.out.println("Nombre de point : " + this.points);
		System.out.print("Cartes développement: ");
	}

	public int LancerDe() {
		Random rd = new Random();
		int val = rd.nextInt(11) + 2;
		return val;
	}

	public int nbRessource(String ressource) {
		int r = 0;
		for (CarteRessources c : deckCarteRessources) {
			if (c.getNom().equals(ressource))
				r += 1;
		}
		return r;
	}

	public void FicheCoutConstruction() {
		int nbArgile = nbRessource("Argile");
		int nbBois = nbRessource("Bois");
		int nbLaine = nbRessource("Laine");
		int nbBle = nbRessource("Ble");
		int nbMinerai = nbRessource("Minerai");
		System.out.println("Matériaux en possession: Argile(" + nbArgile + "), Bois(" + nbBois + "), Laine("
				+ nbLaine + "), Blé(" + nbBle + "), Minerai(" + nbMinerai + ")\n");
		System.out.println("Matériaux nécéssaires pour une route: Argile(1) , Bois(1)\n");
		System.out.println("Matériaux nécéssaires pour une colonie: Argile(1) , Bois(1), Laine(1), Blé(1)\n");
		System.out.println("Matériaux nécéssaires pour une ville: Minerai(3) , Blé(2)");
		System.out.println("Attention, vous devez posséder une colonie pour pouvoir construire une ville !!!\n");
		System.out
				.println("Matériaux nécéssaires pour acheter une carte développement: Minerai(1), Laine(1), Blé(1)\n");
	}

	private void jouerCarteDeveloppement() {
		if (carteDev) {
			StringBuilder sb = new StringBuilder();
			for (CarteDeveloppement c : deckCarteDeveloppement)
				sb.append(c + " ");
			System.out.println("Voici la liste de vos cartes développement" + sb.toString());
			String dev = c.choixAction("Voulez-vous jouer une carte développement ?");
			if (dev.equals("oui"))
				utiliserCarteDevelopment();
		}
	}

	private void voirFicheCout() {
		String fiche = c.choixAction("Voulez-vous voir la fiche des cout de construction ?");
		if (fiche.equals("oui"))
			FicheCoutConstruction();
	}

	public void actionEffectuer() {
		jouerCarteDeveloppement();

		int resultatDe = LancerDe();
		plateau.repartionRessource(resultatDe);

		jouerCarteDeveloppement();

		voirFicheCout();

		String commerce;
		if (!hasPort()) {
			commerce = c.choixAction(
					"Vous n'avez pas de port donc le taux pour le commerce est de 4:1. Voulez-vous faire du commerce ?");
			if (commerce.equals("oui"))
				commerce("4:1");
		} else if (!hasSpecialPort()) {
			commerce = c.choixAction(
					"Vous avez juste un/des ports normaux donc le taux pour le commerce est de 3:1. Voulez-vous faire du commerce ?");
			if (commerce.equals("oui"))
				commerce("3:1");
		} else {
			StringBuilder sb = new StringBuilder();
			boolean portNormal = false;
			for (Port p : port)
				if (p instanceof PortSpecialise)
					sb.append(p + " ");
				else
					portNormal = true;
			if (portNormal)
				commerce = c.choixAction(
						"Vous avez un/des ports normaux, donc avec un taux de 3:1. Et vous avez aussi avec un taux de 2:1 un/des port(s) spécialisé(s) présent dans la liste suivante:\n"
								+ sb.toString() + "\nVoulez-vous faire du commerce ?");
			else
				commerce = c.choixAction(
						"Vous un/des port(s) spécialisé qui ont un taux de 2:1 et qui sont dans la liste suivante:\n"
								+ sb.toString() + "\nVoulez-vous faire du commerce ?");
			if (commerce.equals("oui"))
				commerce("2:1");
		}

		jouerCarteDeveloppement();

		voirFicheCout();

		String achatCarte = c.choixAction("Voulez-vous acheter une carte developpement ?");
		if (achatCarte.equals("oui"))
			achatCarteDeveloppement();

		String construction = c.choixAction("Voulez-vous construire quelque chose ?");
		if (construction.equals("oui")) {
			String choixConstruction = c.choixConstruction();
			switch (choixConstruction) {
				case "route":
					construireRoute();
					break;
				case "colonie":
					constuireColonie();
					break;
				case "ville":
					construireVille();
					break;
			}
		}

		jouerCarteDeveloppement();
	}
	
	
	public boolean aRessource(int t){
		int nbArgile = nbRessource("Argile");
		int nbBois = nbRessource("Bois");
		int nbLaine = nbRessource("Laine");
		int nbBle = nbRessource("Ble");
		if(nbArgile < t && nbBois < t && nbLaine < t && nbBle < t && nbMinerai < t){
			return false;
		}
		return true;
	}
	
	public boolean aRessource(String str, int n){
	int nbArgile = nbRessource("Argile");
	int nbBois = nbRessource("Bois");
	int nbLaine = nbRessource("Laine");
	int nbBle = nbRessource("Ble");
	int nbMinerai=nbRessource("Minerai");
		switch(str){
			case "Bois": 
				if(nbBois < n){
					return false;
				}
			case "Argile": 
				if(nbArgile < n){
					return false;
				}
			case "Ble": 
				if(nbBle < n){
					return false;
				}
			case "Minerai": 
				if(nbMinerai < n){
					return false;
				}
			case "Laine": 
				if(nbLaine < n){
					return false;
				}
		}
		return true;
	}
	
	public boolean aRessource(String str){
		int nbArgile = nbRessource("Argile");
		int nbBois = nbRessource("Bois");
		int nbLaine = nbRessource("Laine");
		int nbBle = nbRessource("Ble");
		int nbMinerai=nbRessource("Minerai");
		switch(str){
			case "colonie":
				if(nbArgile < 1 && nbBois < 1 && nbLaine < 1 && nbBle < 1){
					return false;
				}
			case "ville": 
				if(nbMinerai <3 &&  nbBle <2){
					return false;
				}
			case "route":
				if(nbArgile <1 && nbBois<1){
					return false;
				}
		}
		return true;
	}
	
	
	public void construireColonie(){
		int nbArgile = nbRessource("Argile");
		int nbBois = nbRessource("Bois");
		int nbLaine = nbRessource("Laine");
		int nbBle = nbRessource("Ble");
		System.out.println("Matériaux utilisés pour la construction: Argile(1) , Bois(1), Laine(1), Blé(1)\n");
			if(!aRessource("colonie"){
				System.out.println("Vous n'avez pas les ressource necéssaires pour construire une colonie");
			}
			else{
				perdreRessource("Argile",1);
				perdreRessource("Bois",1);
				perdreRessource("Laine",1);
				perdreRessource("Ble",1);
				Colonie c=new Colonie(this.joueur, false, this.plateau);
				c.placer();
				this.colonie.add(c);
				
			}
		 
	}
	
	public void construireVille(){
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println("Voici la liste de vos Colonie");
		String listColonie="";
		int compteur=1;
		for(Colonie colon : colonie){
			listColonie+= colon.toString+ " (Colonie numéro " + compteur+") + \n";
			compteur+=1;
		}
		System.out.println(listColonie);
		System.out.pritnln("Choisissez une colonie à transformer en ville");
		//associer le numéro demandé et la colonie correspondant dans la list
		String location=sc.nextLine();  // il y aura des coordonnées de case dans la string
		System.out.println("Matériaux utilisés pour la construction: Minerai(3), Blé(2)\n");
			if(!aRessource("ville"){
				System.out.println("Vous n'avez pas les ressource necéssaires pour construire une ville");
			}
			else{
				perdreRessource("Minerai",3);
				perdreRessource("Ble",2);
				c.upgrade();
			}
		}
	
	
	

	public void recevoirRessource(String ressource, int n) {
		for (int i = 0; i < n; i++) {
			deckCarteRessources.add(new CarteRessources(ressource));
		}
	}

	public void perdreRessource(String ressource, int n) {
		for (int i = 0; i < n; i++) {
			for (CarteRessources c : deckCarteRessources) {
				if (c.getNom().equals(ressource)) {
					deckCarteRessources.remove(c);
				}
			}
		}
	}

	public boolean hasPort() {
		return !(port.isEmpty());
	}

	private boolean hasSpecialPort() {
		for (Port p : port)
			if (p instanceof PortSpecialise)
				return true;
		return false;
	}

	public void commerce(String taux) { // rajouter l'option d'arreter le commerce
		int nbArgile = nbRessource("Argile");
		int nbBois = nbRessource("Bois");
		int nbLaine = nbRessource("Laine");
		int nbBle = nbRessource("Ble");
		int nbMinerai = nbRessource("Minerai");
		int t = Integer.parseInt(taux.split(":")[0]);
		boolean echangeEnCour = true;
		System.out.println("Matériaux en possession : Argile(" + nbArgile + "), Bois(" + nbBois + "), Laine(" + nbLaine
				+ "), Blé(" + nbBle + "), Minerai(" + nbMinerai + ")");
		if (taux.equals("4:1") || taux.equals("3:1")) {
			System.out.println(
					"Ce taux vous permet d'échanger" + t + " matière première identique contre une de votre choix");
			if (!aRessource(t)) {
				System.out.println("Vous n'avez pas les ressouces nécessaire");
			} else {
				while (echangeEnCour) {
					String ressource = c.choixRessource("Quel matière voulez-vous utiliser ?");
					if (ressource.equals("stop"))
						echangeEnCour = false;
					else {
						if (nbRessource(ressource) < t) {
							System.out.println("Vous n'avez pas assez de " + ressource);
						} else {
							perdreRessource(ressource, t);
							ressource = c.choixRessource("Quel matière voulez vous recevoir");
							if (ressource.equals("stop"))
								echangeEnCour = false;
							else {
								recevoirRessource(ressource, 1);
								echangeEnCour = false;
							}
						}
					}
				}
			}
		} else {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Port p : port) {
				i++;
				sb.append(p + "(" + i + ") ");
			}
			int choixPort = c.choixPort(i);
			Port portChoisi = port.get(choixPort);
			if (portChoisi instanceof PortSpecialise) {
				if (!aRessource(2)) {
					System.out.println("Vous n'avez pas les ressouces nécessaire");
				} else {
					while (echangeEnCour) {
						String ressource = c.choixRessource("Quel matière voulez-vous utiliser ?");
						if (ressource.equals("stop"))
							echangeEnCour = false;
						else {
							if (nbRessource(ressource) < 2) {
								System.out.println("Vous n'avez pas assez de " + ressource);
							} else {
								perdreRessource(ressource, 2);
								ressource = c.choixRessource("Quel matière voulez vous recevoir");
								if (ressource.equals("stop"))
									echangeEnCour = false;
								else {
									recevoirRessource(portChoisi.getRessource(), 1);
									echangeEnCour = false;
								}
							}
						}
					}
				}
			} else {
				if (!aRessource(3)) {
					System.out.println("Vous n'avez pas les ressouces nécessaire");
				} else {
					while (echangeEnCour) {
						String ressource = c.choixRessource("Quel matière voulez-vous utiliser ?");
						if (ressource.equals("stop"))
							echangeEnCour = false;
						else {
							if (nbRessource(ressource) < 3) {
								System.out.println("Vous n'avez pas assez de " + ressource);
							} else {
								perdreRessource(ressource, 3);
								ressource = c.choixRessource("Quel matière voulez vous recevoir");
								if (ressource.equals("stop"))
									echangeEnCour = false;
								else {
									recevoirRessource(ressource, 1);
									echangeEnCour = false;
								}
							}
						}
					}
				}
			}
		}
	}

	public int getPoints() {
		return points;
	}
	
	public void JouerChevalier(){
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println("Vous avez la possibilité de déplacer le voleur");
		plateau.getVoleur().placer();
		System.out.println("Vous avez la possibilité de récupérer une matière première d'un joueur ayant une colonie qui jouxte l'emplacement  du voleur");
		System.out.println("Voici la liste des joueurs qui ont des colonies près de l'emplacement du voleur:");
		plateau.afficheJoueur();
		System.out.println("Veuillez saisir le nom d'un joueur ");
		String Jnom=sc.nextLine();
		Joueur player=new Joueur(null, null, null, null);
			for(Joueur j : plateau.getlistJoueur()){
				if(j.getNom.equals(Jnom)){
					player=j;
				}
			}
		System.out.println("une ressource vous sera donné au hasard");
		Random r= new Random();
		int x=r.nextInt(5);
			switch(x){
				case 0 :
					if(!player.aRessource("Bois",1){
						System.out.println("Ce joueur n'a pas assez de bois");
					}
					else{
						player.perdreRessource("Bois",1);
						recevoirRessource("Bois",1);
						System.out.println("une ressource de bois vous à été donnée");
					}
				case 1 :
					if(!player.aRessource("Argile",1){
						System.out.println("Ce joueur n'a pas assez d'Argile");
					}
					else{
						player.perdreRessource("Argile",1);
						recevoirRessource("Argile",1);
						System.out.println("une ressource d'argile vous à été donnée");
					}
				case 2 :
					if(!player.aRessource("Ble",1){
						System.out.println("Ce joueur n'a pas assez de ble");
					}
					else{
						player.perdreRessource("Ble",1);
						recevoirRessource("Ble",1);
						System.out.println("une ressource de ble vous à été donnée");
					}
				case 3 :
					if(!player.aRessource("Minerai",1){
						System.out.println("Ce joueur n'a pas assez de Minerai");
					}
					else{
						player.perdreRessource("Minerai",1);
						recevoirRessource("Minerai",1);
						System.out.println("une ressource de minerai vous à été donnée");
					}
				case 4 :
					if(!player.aRessource("Laine",1){
						System.out.println("Ce joueur n'a pas assez de laine");
					}
					else{
						player.perdreRessource("Laine",1);
						recevoirRessource("Laine",1);
						System.out.println("une ressource de laine vous à été donnée");
					}
			}
					
	}
	
	public LinkedList getColonie(){
		return this.colonie;
	}

}
