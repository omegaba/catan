package Joueur;

import Carte.Developpement.CarteDeveloppement;
import Carte.Ressources.CarteRessources;
import Jeu.Communication;
import Plateau.Plateau;
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

	public Joueur(String nom, boolean ai, String couleur, Plateau p) {
		this.nom = nom;
		this.ai = ai;
		this.couleur = couleur;
		points = 0;
		deckCarteRessources = new LinkedList<>();
		deckCarteDeveloppement = new LinkedList<>();
		this.plateau = p;
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
			if (nbArgile < t && nbBois < t && nbLaine < t && nbBle < t && nbMinerai < t) {
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
				if (nbArgile < 2 && nbBois < 2 && nbLaine < 2 && nbBle < 2 && nbMinerai < 2) {
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
				if (nbArgile < 3 && nbBois < 3 && nbLaine < 3 && nbBle < 3 && nbMinerai < 3) {
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

}
