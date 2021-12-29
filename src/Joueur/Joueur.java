package Joueur;

import Carte.Developpement.CarteDeveloppement;
import Carte.Ressources.CarteRessources;
import Jeu.Communication;
import Plateau.Infrastructures.Port;
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

	public Joueur(String nom, boolean ai, String couleur) {
		this.nom = nom;
		this.ai = ai;
		this.couleur = couleur;
		points = 0;
		deckCarteRessources = new LinkedList<>();
		deckCarteDeveloppement = new LinkedList<>();
	}

	public void affiche() {
		System.out.println("Nom :" + this.nom + ", couleur " + this.couleur);
		System.out.println("Nombre de point : " + this.points);
		System.out.print("Cartes développement: ");
		for (CarteDeveloppement c : deckCarteDeveloppement) {
			c.affiche();
		}
		System.out.println("Cartes Ressources: ");
		for (CarteRessources r : deckCarteRessources) {
			r.affiche();
		}
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
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		while (s.hasNextLine()) {
			System.out.println(
					"Voulez-vous connaître le coût de construction d'une Route(r), d'une Colonie(c), ou d'une Ville(v) ?");
			s = s.nextLine();
			if (s.equals("Route")) {
				System.out.println("Matériaux nécéssaires: Argile(1) , Bois(1)");
				System.out.println();
				System.out.println("Matérielle en possession: Argile(" + nbArgile + "), Bois(" + nbBois + ")");
			}
			if (s.equals("Colonie")) {
				System.out.println("Matériaux nécéssaires: Argile(1) , Bois(1), Laine(1), Blé(1)");
				System.out.println();
				System.out.println("Matérielle en possession: Argile(" + nbArgile + "), Bois(" + nbBois + "), Laine("
						+ nbLaine + "), Blé(" + nbBle + ")");
			}
			if (s.equals("Ville")) {
				System.out.println("Matériaux nécéssaires: Minerai(3) , Blé(2)");
				System.out.println();
				System.out.println("Matérielle en possession: Minerai(" + nbMinerai + "), Blé(" + nbBle + ")");
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
		s=sc.nextLine();
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
			String tmp=sc.nextLine();
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
				ConstruireRoute(); break;
				case 1: System.out.println("Vous avez tiré au sort une carte invention !!"); break;
					//a finir, il faut choisir deux ressources et les ajouter aux decks, peut-être avec une fonction qui prend en parametre une String et un entier
				case 2: System.out.println("Vous avez tiré au sort une carte monopole !! Veuillez designer la ressource souhaitée :");
				//a finir, avec un switch de préférence (ou pas)
				}
		}
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

	public void getSpecialPort(int n) {
		for (Port p : port) {
			if (p.getCase() != n) {
				System.out.println("Cette case n'est pas un port special");
				break;
			} else {
				int x = nbRessource(p.getRessouce());
				if (x < 2) {
					System.out.println(
							"Le nombre de " + p.getRessource() + " en possesion est insuffisant, il vous en faut 2 !!");
				} else {
					System.out.println("Vous avez perdu 2 ressource de " + p.getRessource());
					perdreRessource(p.getRessource(), 2);
					Scanner sc = new Scanner(System.in);
					String s = sc.nextLine();
					System.out.println("Quelle ressource souhaitez-vous en échange ?");
					String tmp = sc.nextLine();
					recevoirRessource(tmp, 1);
				}
			}
		}
	}

	public void Commercer(){
		int nbArgile=nbRessource("Argile");
		int nbBois= nbRessource("Bois");
		int nbLaine=nbRessource("Laine");
		int nbBle=nbRessource("Ble");
		int nbMinerai=nbRessource("Minerai");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
			while(s.hasNextLine()){
				System.out.println("Choisissez un taux de change : 4:1       3:1        2:1");
				s=sc.nextLine();
				if(s.equals("4:1"){
					System.out.println("Ce taux vous permet d'échanger quatre matière première identique contre une de votre choix");
					System.out.println("Matériel en possession : Argile("+nbArgile+"), Bois("+ nbBois+"), Laine("+nbLaine+"), Blé("+nbBle+"), Minerai("+nbMinerai+")");
					if(nbArgile<4 && nbBois <4 && nbLaine<4 && nbBle < 4 && nbMinerai<4){
						System.out.println("Vous n'avez pas les ressouces nécessaire");
					}
					else{
						System.out.println("Quel matière voulez-vous utiliser ?");
						String aux =sc.nextLine();
						if(nbRessource(aux) < 4){
							System.out.println("Vous n'avez pas assez de "+ aux);
						}
						else{
							perdreRessource(aux,4);
							System.out.println("Quel matière voulez vous recevoir");
							String tmp=sc.nextLine();
							recevoirRessource(tmp,1);
						}
					}	
				
				}
				if (s.equals("3:1")){
					System.out.println("Ce taux vous permet d'échanger trois matière première identiques contre une de votre choix");
					if (!hasPort()){
						System.out.println("Vous n'avez aucune colonie proche d'un port, vous ne pouvez pas profiter de ce taux");
					}
					else{
					System.out.println("Matériel en possession : Argile("+nbArgile+"), Bois("+ nbBois+"), Laine("+nbLaine+"), Blé("+nbBle+"), Minerai("+nbMinerai+")");
						if(nbArgile<3 && nbBois <3 && nbLaine<3 && nbBle < 4 && nbMinerai<3){
							System.out.println("Vous n'avez pas les ressouces nécessaire");
						}
						else{
							System.out.println("Quel matière voulez-vous utiliser ?");
							String aux =sc.nextLine();
							if(nbRessource(aux) < 3){
								System.out.println("Vous n'avez pas assez de "+ aux);
							}
							else{
								perdreRessource(aux,3);
								System.out.println("Quel matière voulez vous recevoir");
								String tmp=sc.nextLine();
								recevoirRessource(tmp,1);
							}
						}
							
					}
				}
				if(s.equals("2:1")){
				System.out.println("Ce taux vous permet d'échanger deux matière première identiques contre une de votre choix, a condition que vous soyez a proximité d'un port specialisé");
					if (!hasSpecialPort()){
						System.out.println("Vous n'avez aucune colonie proche d'un port spécialisé, vous ne pouvez pas profiter de ce taux");
					}
					else{
						
					System.out.println("Veuillez indiquer le numéro de case du port spécialisé que vous voulez utiliser");
					String tmp=sc.nextLine();
					getSpecialPort(Integer.parseInt(tmp));
					}
				}
			}
		}
