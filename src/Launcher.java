import Carte.*;
import Gui.Model.Model;
import Gui.Vue.Parametrage;
//import Gui.Vue.Parametrage;
import Gui.Vue.Vue;
import Jeu.*;
import Plateau.*;
import Joueur.*;
import javax.swing.*;
import java.awt.*;

public class Launcher extends JFrame {
    
   /* private static  Parametrage scene;
    private static JFrame window;*/
    public static void main(String[] args) {

        Communication c = new Communication();
        if (c.choixPartie().equals("textuel"))
            jeuText();
        else
            jeuGraph();

    }

    static void jeuText() {
        Plateau p = new Plateau(true);
        AffichageText a = new AffichageText(p);
        a.affiche();
        p.deuxPremiersTour(a);
        while (!p.getPartiFini()) {
            a.affiche();
            p.tour();
        }
        System.out.println(
                "Bravo à " + p.getListJoueurs().get(p.getNumeroJoueur()).getNom() + " !! Qui à gagner la partie avec "
                        + p
                                .getListJoueurs().get(p.getNumeroJoueur()).getPoints()
                        + " points.");
    }

    static void jeuGraph() {
       /* EventQueue.invokeLater(() -> {
            window = new JFrame();
            scene = new Parametrage(window);
            //scene.playFirst();
        });*/

        
    }

}
