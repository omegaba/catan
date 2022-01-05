package Gui.Controleur;
import javax.swing.*;
import Joueur.Joueur;
import Plateau.Composants.Case;
import Gui.Model.*;
import Gui.Vue.*;
import Gui.Vue.Vue.ButtonPanel;
import Gui.Vue.Vue.FenetreDialog;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
public class Controleur {
    private final Model model;
    private final Vue vue;
    private final JFrame fenetre;
    private int compteur;
    private ButtonPanel buttonpanel;
    private FenetreDialog fenetreDialog;
    private FenetreDialog fenetreCart;
    private boolean isCardActive;
    private boolean isCardUsed;
    private int counter;
    private boolean second;

    public Controleur(Model model, Vue vue, JFrame fenetre){
        this.vue= vue;
        this.model= model;
        this.fenetre= fenetre;
        isCardActive=false;
        isCardUsed=false;
        compteur=0;
    }

}
