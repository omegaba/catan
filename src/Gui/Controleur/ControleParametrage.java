package Gui.Controleur;

import java.awt.*;
import javax.swing.*;
import Joueur.Joueur;
import Gui.Vue.*;
import Gui.Model.*;
import Jeu.Configuration;
import Gui.Vue.Parametrage;

public class ControleParametrage {
    private final Model model;
    private final Parametrage parametrage;
    private final JFrame fenetre;

    public ControleParametrage(Parametrage parametrage, Model model, JFrame fenetre) {
        this.model = model;
        this.parametrage = parametrage;
        this.fenetre = fenetre;
    }

    public void Commencer() {
        parametrage.removeAll();
        this.parametrage.protagonist();
    }

    public void Adversaire3Ia(){
        for(int i=0; i <4; i++){
            model.ajouterIA("IA" + i);
        }
        readGame();
    }

    public void Adversaire2Ia(){
        for(int i=0; i <3; i++){
            model.ajouterIA("IA" + i);
        }
        readGame();
    }


    public void readGame(){
        model.initGame();
        Vue v=new Vue(fenetre,model);
        fenetre.add(v);
        fenetre.setContentPane(v);
        fenetre.remove(parametrage);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.pack();
        v.play();
    }

    public void AdversaireJoueur() {
        this.parametrage.removeAll();


    }

    public String readString(JTextField text) {
        return text.getText();
    }

    public int readIntFromMenu(JComboBox<String> menu) {
        String s = menu.getItemAt(menu.getSelectedIndex());
        return Integer.parseInt(s);
    }

}
