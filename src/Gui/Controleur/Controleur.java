package Gui.Controleur;

import javax.swing.*;
import Joueur.Joueur;
import Plateau.Composants.Case;
import Gui.Model.*;
import Gui.Vue.*;
import Gui.Vue.Vue.ButtonPanel;
import Gui.Vue.Vue.FenetreDialog;
import Gui.Vue.Vue.PanelCase;
import Gui.Vue.Vue.PanelConstruction;

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
    private boolean first;

    public Controleur(Model model, Vue vue, JFrame fenetre) {
        this.vue = vue;
        this.model = model;
        this.fenetre = fenetre;
        isCardActive = false;
        isCardUsed = false;
        compteur = 0;
    }

    public void setActionButtonPanel(ButtonPanel buttonPanel) {
        this.buttonpanel = buttonPanel;
    }

    public void LancerDe() {
        int de = model.lancerDe();
        buttonpanel.getDe().setEnabled(false);
        buttonpanel.getAcDev().setEnabled(model.getJoueur().aRessource("carte ressource") && !model.listCardDevVide());
        buttonpanel.getConstruire().setEnabled(!model.getJoueur().aRessource("colonie")
                && !model.getJoueur().aRessource("ville") && !model.getJoueur().aRessource("route"));
        buttonpanel.getDeplaceVoleur().setEnabled(de == 7);
        buttonpanel.getValider().setEnabled(de != 7);
        if (fenetreDialog != null && fenetreDialog.isVisible()) {
            fenetreDialog.repaint(!model.getJoueur().aRessource("colonie") && !model.getJoueur().aRessource("ville")
                    && !model.getJoueur().aRessource("route"));
        }
    }

    public void AcheterCarteDev() {
        model.getJoueur().achatCarteDeveloppementGui();
        buttonpanel.getAcDev().setEnabled(model.getJoueur().aRessource("carte ressource") && !model.listCardDevVide());
        buttonpanel.getConstruire().setEnabled(!model.getJoueur().aRessource("colonie")
                && !model.getJoueur().aRessource("ville") && !model.getJoueur().aRessource("route"));
        vue.repaint();
        if (fenetreDialog != null && fenetreDialog.isVisible()) {
            fenetreDialog.repaint(!model.getJoueur().aRessource("colonie") && !model.getJoueur().aRessource("ville")
                    && !model.getJoueur().aRessource("route"));
        }
    }

    public void UtiliserCartDev(char c) {
        fenetreCart.setVisible(false);
        switch (c) {
            case 'C':
                Chevalier();
                break;
            case 'R':
                carteProgres();
                break;
            case 'D':
                monopole();
                break;
        }
    }

    public void CaseActive(boolean active) {
        PanelCase.ActivCase(active);
        vue.repaint();
    }

    public void Chevalier() {
        isCardActive = true;
        CaseActive(true);
        buttonpanel.getUcdev().setEnabled(false);
        model.getJoueur().utiliseCartChevalier();
        model.setChevalierPlusPuissant();
    }

    public void monopole() {
        isCardActive = true;
        buttonpanel.setVisible(false);
        if (compteur >= 2) {
            buttonpanel.setVisible(true);
            CaseActive(false);
            model.getJoueur().UtiliserCarteMonopole();
            isCardActive = false;
            compteur = 0;
        } else {
            CaseActive(true);
        }
    }

    public void carteProgres() {
        isCardActive = true;
        buttonpanel.setVisible(false);
        if (compteur >= 2) {
            buttonpanel.setVisible(true);
            CaseActive(false);
            model.getJoueur().utiliserCartProgres();
            isCardActive = false;
            counter = 0;
        } else {
            CaseActive(true);
        }
    }

    public void valider() {
        model.setJoueurActuel();
    }

    public void CommencerTour() {
        DialogTour();
        buttonpanel.getDe().setEnabled(true);
        buttonpanel.getCommerce().setEnabled(true);
        buttonpanel.getAcDev().setEnabled(false);
        buttonpanel.getConstruire().setEnabled(!model.getJoueur().aRessource("colonie")
                && !model.getJoueur().aRessource("ville") && !model.getJoueur().aRessource("route"));
        buttonpanel.getDeplaceVoleur().setEnabled(false);
        buttonpanel.getValider().setEnabled(false);
        buttonpanel.getUcdev().setEnabled(!model.getJoueur().getDeckCarteDeveloppement().isEmpty());
        ChoixConstruction('*');
        if (fenetreDialog != null)
            fenetreDialog.setEnabled(false);
        second = false;
        first = false;
        isCardActive = false;
        isCardUsed = false;
        fenetreDialog = null;
        vue.repaint();
    }

    public void DialogTour() {
        JOptionPane.showMessageDialog(vue, "c'est au tour de " + model.getJoueur().getNom());
    }

   /* public void overLimitResources(Joueur p) {
        if (p.getDeckCarteRessources().size() <= 7)
            return;
        JDialog dialog = new CustomJDialog(window, OverLimitPanel(this, window), true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
    }*/

    public void ChoixConstruction(Character c) {
        switch (c) {
            case 'r':
                CaseActive(true);
                break;
            case 'c':
                CaseActive(true);
                break;
            case 't':
                CaseActive(true);
                break;
            default:
                CaseActive(false);
        }
    }

    public void impossibleConstructionMessage() {
        JOptionPane.showMessageDialog(vue, "Vous ne pouvez pas construire ici", "Attenion",
                JOptionPane.WARNING_MESSAGE);
    }

    public Model getModel(){
        return this.model;
    }

    public void MenuConstruction(){
        if(fenetreDialog != null){
            fenetreDialog.setVisible(false);
        }

        fenetreDialog=vue.new FenetreDialog(fenetre, vue.new PanelConstruction(model.getJoueur(), this));
        fenetreDialog.setVisible(!model.getJoueur().aRessource("colonie")
        && !model.getJoueur().aRessource("ville") && !model.getJoueur().aRessource("route"));
    }

   /* public void build(char c, ) {

    }*/

    public void tileAction(Case c){
        if(!isCardActive) DeplaceVoleur(c);
        else{
            model.getJoueur().PerdreRessource(c);
            isCardUsed= true;
            buttonpanel.getUcdev().setEnabled(false);
            compteur++;
            monopole();
        }
    }

    public void DeplaceVoleur(Case c){
        model.getJoueur().DeplaceVoleur(c,model.getVoleur());
        buttonpanel.getDeplaceVoleur().setEnabled(false);
        vue.repaint();
        buttonpanel.getValider().setEnabled(true);
    }


    public void repaint(){
        vue.repaint();
    }



}
