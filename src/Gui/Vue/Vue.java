package Gui.Vue;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

import Gui.Controleur.Controleur;
import Gui.Model.*;
import Joueur.Joueur;
import javax.swing.*;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Font;
import Gui.Model.*;

public class Vue extends JPanel {
    private final Model model;
    private final JFrame window;
    private JPanel titleNamePanel, starbuttonPanel;
    private JLabel titleNameLabel, starbuttonLabel;
    private JButton startButton;
    private Font titlefont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);

    public Vue(Model model) {
        this.model = model;
        // Window.setDefaultCloseOperation(
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

        /*
         * startButton.addActionListener();
         * startButton.setActionCommand("start");
         */

        window.add(titleNamePanel);
        starbuttonPanel.add(startButton);
        window.add(starbuttonPanel);
        // initCenter();
        initBottom();
        initLeft();

        window.setVisible(true);
    }

    public void initBottom() {
        add(new PanelCarteRessource(model), BorderLayout.PAGE_END);
    }

    public void initLeft() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JPanel de = new PanelDe(model);
        panel.add(de);
        add(panel, BorderLayout.LINE_START);

    }

    
      public void initCenter(){
      JPanel center= new JPanel();
      center.setLayout(new GridBagLayout());
      GridBagConstraints carre= new GridBagConstraints();
      carre.gridx=1;
      carre.gridy=0;
      if(model.getListJoueurs().size()>2){
        center.add(new InfrastructurePanel(model.getListJoueurs().get(2),true));
      }
      carre.gridx=0;
      carre.gridy+=1;

      if(model.getListJoueurs().size()>3){
          center.add(new InfrastructurePanel(model.getListJoueurs().get(3),false));
      }
      carre.gridx+=1;
      carre.gridy=1;

      center.add(new )
    }
     

    public class ChoiceHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String start = e.getActionCommand();

        }
    }

    public class PanelListJoueur extends JPanel {
        public PanelListJoueur(List<Joueur> list) {
            setLayout(new FlowLayout());

            list.forEach(player -> add(new PanelJoueur(player)));
        }

        public static class PanelJoueur extends JPanel {
            private final Joueur joueur;
            private final Color color;

            public PanelJoueur(Joueur joueur) {
                this.joueur = joueur;
                this.color = Color.LIGHT_GRAY;
                setLayout(new BorderLayout());
                add(new Joueur_Description(joueur), BorderLayout.PAGE_START);
                setBackground((color));
            }

            public void initCentralPanel() {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.setBackground(Color.green);
                panel.add(new PanelCarteRessource(joueur));
                panel.add(new PanelCarteDeveloppement(joueur));
                add(panel, BorderLayout.CENTER);
            }

            public static class Joueur_Description extends JPanel {
                private final Joueur joueur;
                private final JLabel score;

                public Joueur_Description(Joueur joueur) {
                    this.joueur = joueur;
                    setLayout(new FlowLayout());
                    /*
                     * for (Joueur j : model.getListJoueurs()){
                     * if(j.getNom().equals(joueur.getNom())){
                     * setBackground(j.getColor());
                     * }
                     * }
                     */
                    setBackground(Color.GREEN);
                    score = new JLabel("Score");
                    score.setForeground(Color.white);
                    add(score);
                    JLabel label = new JLabel(joueur.getNom());
                    label.setForeground(Color.white);
                    add(label);
                }

                public void paintComponent(Graphics g) {
                    score.setText(String.valueOf(joueur.getPoints()));
                    super.paintComponent(g);
                }
            }

            public static class PanelCarteRessource extends JPanel {
                private final Joueur joueur;
                private final JLabel nb;

                public PanelCarteRessource(Joueur joueur) {
                    this.joueur = joueur;
                    setBackground(Color.GREEN);
                    nb = new JLabel(String.valueOf(joueur.nbCarteRessources()));
                    nb.setForeground(Color.white);
                    add(nb);
                }

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                }
            }

            public static class PanelCarteDeveloppement extends JPanel {
                private final Joueur joueur;
                private final JLabel nb;

                public PanelCarteDeveloppement(Joueur joueur) {
                    this.joueur = joueur;
                    setBackground(Color.GREEN);
                    nb = new JLabel(String.valueOf(joueur.nbCarteRessources()));
                    nb.setForeground(Color.white);
                    add(nb);
                }

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                }
            }
        }
    }

    public class PanelCarteRessource extends JPanel {
        private final Model model;
        private final JLabel Bois;
        private final JLabel Argile;
        private final JLabel Minerai;
        private final JLabel Ble;
        private final JLabel Laine;

        public PanelCarteRessource(Model model) {
            this.model = model;
            Bois = new JLabel("Bois");
            Argile = new JLabel("Argile");
            Ble = new JLabel("Ble");
            Minerai = new JLabel("Minerai");
            Laine = new JLabel("Laine");
            Bois.setForeground(Color.white);
            Ble.setForeground(Color.white);
            Argile.setForeground(Color.white);
            Minerai.setForeground(Color.white);
            Laine.setForeground(Color.white);
            add(Laine);
            add(Ble);
            add(Bois);
            add(Minerai);
            add(Argile);
        }

        public void paintComponent(Graphics g) {
            setBackground(Color.GREEN);
            super.paintComponent(g);
        }
    }

    public class PanelDe extends JPanel {
        private final JLabel de;
        private final Model model;

        public PanelDe(Model model) {
            this.model = model;
            de = new JLabel();
            add(de);
        }

        public void paintComponent(Graphics g) {
            de.setText(String.valueOf(model.getDe()));
            super.paintComponent(g);
        }
    }

    public class ButtonPanel extends JPanel {
        private final PanelDe dePanel;
        private final ActionButton de;
        private final ActionButton commerce;
        private final ActionButton valider;
        private final ActionButton AcheterCarteDev;
        private final ActionButton utiliserCarteDev;
        private final ActionButton deplaceVoleur;
        private final ActionButton construire;

        public ButtonPanel(Model model, Controleur controleur) {
            setLayout(new GridLayout(4, 2, 7, 7));
            dePanel = new PanelDe(model);
            de = new ActionButton("Dé", 0, controleur);
            commerce = new ActionButton("Commerce", 1, controleur);
            construire = new ActionButton("Construire", 2, controleur);
            AcheterCarteDev = new ActionButton("AcheterCartDev", 3, controleur);
            utiliserCarteDev = new ActionButton("UtiliserCartDev", 4, controleur);
            deplaceVoleur = new ActionButton("DéplacerVoleur", 5, controleur);
            valider = new ActionButton("Valider", 6, controleur);
            iniTialiserPanel();
        }

        public void iniTialiserPanel() {
            GridLayout layout = new GridLayout(4, 2, 7, 7);
            add(de);
            add(commerce);
            add(construire);
            add(AcheterCarteDev);
            add(utiliserCarteDev);
            add(valider);
            add(deplaceVoleur);

        }

        public ActionButton getDe() {
            return de;
        }

        public ActionButton getCommerce() {
            return this.commerce;
        }

        public ActionButton getConstruire() {
            return construire;
        }

        public ActionButton getUcdev() {
            return utiliserCarteDev;
        }

        public ActionButton getAcDev() {
            return AcheterCarteDev;
        }

        public ActionButton getValider() {
            return valider;
        }

        public ActionButton getDeplaceVoleur() {
            return deplaceVoleur;
        }

        public static class ActionButton extends JButton {
            private String texte;
            private int numero;
            private final Controleur controleur;

            public ActionButton(String texte, int numero, Controleur controleur) {
                super(texte);
                this.controleur = controleur;
                this.numero = numero;
                setVisible(true);

            }

            public void action(int numero) {
                switch (numero) {
                    case 0:
                        addActionListener(e -> controleur.lancerDe());
                        break;
                    case 1:
                        addActionListener(e -> controleur.commerce());
                        break;
                    case 2:
                        addActionListener(e -> controleur.menuConstruction());
                        break;
                    case 3:
                        addActionListener(e -> controleur.AcheterCarteDev());
                        break;
                    case 4:
                        addActionListener(e -> controleur.utiliserCarteDev());
                        break;
                    case 5:
                        addActionListener(e -> controleur.DeplaceVoleur());
                        break;
                    case 6:
                        addActionListener(e -> controleur.valider());
                        break;
                    default:
                        setVisible(false);
                }
            }

            public void paintComponent(Graphics g) {
                if (!isEnabled()) {
                    setForeground(Color.LIGHT_GRAY);
                } else {
                    setForeground(Color.BLACK);
                    super.paintComponent(g);
                }
            }
        }
    }

    public class InfrastructurePanel extends JPanel {
        private final Joueur joueur;
        private final JLabel colonies;
        private final JLabel villes;
        private final JLabel routes;

        public InfrastructurePanel(Joueur joueur, boolean location){
            this.joueur= joueur;
            colonies= new JLabel();
            villes=new JLabel();
            routes= new JLabel();

            if(location){
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            }

            add(colonies);
            add(villes);
            add(routes);
            setBackground(Color.cyan);
            colonies.setForeground(Color.white);
            villes.setForeground(Color.white);
            routes.setForeground(Color.white);
            description();
        }


        public void description(){
            colonies.setText(joueur.getNbColonie() + " colonies");
            villes.setText(joueur.getNbVilles() + " villes");
            routes.setText(joueur.getNbRoutes() + " routes");
        }

        public void paintComponent(Graphics g) {
            description();
            super.paintComponent(g);
        }
    }

    public class FenetreDialog extends JDialog {
        private final Component fenetre;

        public FenetreDialog(Frame source, Component fenetre, boolean modal){
            super(source);
            add(fenetre);
            this.fenetre= fenetre;
            setLocationRelativeTo(source);
            setMinimumSize(fenetre.getPreferredSize());
            pack();
        }


        public boolean isEmpty(){
            return fenetre==null || !fenetre.isVisible();
        }
        

        public void paint(Graphics g){
            if (isEmpty()){
                setVisible(false);
            }
            super.paint(g);
        }

        public void repaint(boolean flag){
            setVisible(flag);
            super.repaint();
        }
    }

    public class PanelPlateau{
        
    }

    
}
