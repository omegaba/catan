package Jeu;

public class CarteDeveloppement extends Carte{
    private String nom;
    public CarteDeveloppement(String nom) {
        this.nom=nom;
    }

    public void affiche(){
        System.out.println("Carte developpement :" +this.nom);
    }

    public class Chevalier extends CarteDeveloppement{
        public Chevalier(){
            super("Chevalier");
        }
    }

    public class PointDeVictoire extends CarteDeveloppement{
        public PointDeVictoire(){
            super("PointDeVictoire");
        }
    }
    
    public class Progres extends CarteDeveloppement{
        public Progres(){
            super("Progres");
        }
    }
}
