package Jeu;

public class CarteRessources extends Carte{
    private String nom;

    public CarteRessources(String nom) {
        this.nom = nom;
    }

    public void affiche(){
        System.out.println("CarteRessources " + this.nom);
    }

    public class Ble extends CarteRessources{
        public Ble() {
            super("Blé");
        }
    }

    public class Argile extends CarteRessources{
        public Argile() {
            super("Argile");
        }
    }

    public class Minerai extends CarteRessources{
        public Minerai() {
            super("Minerai");
        }
    }

    public class Bois extends CarteRessources{
        public Bois() {
            super("Bois");
        }
    }

    public class Laine extends CarteRessources{
        public Laine() {
            super("Laine");
        }
    }
}
