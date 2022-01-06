package Gui.Vue;

import java.awt.*;
import javax.swing.*;
import Joueur.Joueur;
import Gui.Controleur.ControleParametrage;
import Gui.Model.*;
import Jeu.Configuration;
import java.awt.Font;

public class Parametrage extends JPanel {
    private JButton VsPlayers;
    private JButton VsTwoAI;
    private JButton VsThreeIA;
    private JPanel choiceButtonPanel;
    private final ControleParametrage controleur;
   // private final Model model;
    private final JFrame fenetre;
   /* private Font titlefont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);*/
    private final Model model;
    private final JFrame window;
    private JPanel titleNamePanel, starbuttonPanel;
    private JLabel titleNameLabel, starbuttonLabel;
    private JButton startButton;
    private Font titlefont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);


    public Parametrage(JFrame fenetre) {
        model = new Model();
        this.fenetre = fenetre;
        controleur = new ControleParametrage(this, model, fenetre);
        model.setControleur(controleur);
        this.setLayout(new BorderLayout());
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        // titlescreen

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 500);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Colon de Catan");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titlefont);
        titleNamePanel.add(titleNameLabel);

        starbuttonPanel = new JPanel();
        starbuttonPanel.setBounds(300, 400, 200, 100);
        starbuttonPanel.setBackground(Color.white);
        startButton = new JButton("Commencer");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);

        

        window.add(titleNamePanel);
        starbuttonPanel.add(startButton);
        window.add(starbuttonPanel);
        startButton.addActionListener(event -> controleur.Commencer());


        window.setVisible(true);
    }

    public void Start() {
        Configuration config = model.getConfiguration();
        startButton.addActionListener(event -> this.controleur.Commencer());
    }

    public void protagonist() {
        this.revalidate();
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(3, 1));

        VsPlayers = new JButton("Jouer contre des Joueurs");
        VsPlayers.setBackground(Color.black);
        VsPlayers.setForeground(Color.white);
        VsPlayers.setFont(normalFont);
        VsPlayers.setFocusPainted(false);
        choiceButtonPanel.add(VsPlayers);
        VsTwoAI = new JButton("Jouer deux Ia");
        VsTwoAI.setBackground(Color.black);
        VsTwoAI.setForeground(Color.white);
        VsTwoAI.setFont(normalFont);
        VsTwoAI.setFocusPainted(false);
        choiceButtonPanel.add(VsTwoAI);
        VsThreeIA = new JButton("Jouer trois Ia");
        VsThreeIA.setBackground(Color.black);
        VsThreeIA.setForeground(Color.white);
        VsThreeIA.setFont(normalFont);
        VsThreeIA.setFocusPainted(false);
        choiceButtonPanel.add(VsThreeIA);
        this.add(choiceButtonPanel);
        fenetre.pack();
        VsPlayers.addActionListener(event -> controleur.AdversaireJoueur());
        VsTwoAI.addActionListener(event -> controleur.Adversaire2Ia());
        VsThreeIA.addActionListener(event -> controleur.Adversaire3Ia());
    }

    public void Titre() {
        JPanel titleNamePanel;
        JLabel titleNameLabel;
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 500);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Colon de Catan");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titlefont);
        titleNamePanel.add(titleNameLabel);
        this.add(titleNamePanel);
        this.add(titleNameLabel);
        fenetre.pack();
    }

    private int numPlayer(JPanel Config, JComboBox<String> nb, JButton jb) {
        jb.addActionListener(event -> jb.setEnabled(false));
        return this.controleur.readIntFromMenu(nb);
    }

    /*
     * public void playFirst(){
     * model.JouerPremierTour();
     * }
     */

    

}
