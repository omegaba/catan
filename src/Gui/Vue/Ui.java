package Gui.Vue;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Font;

public class Ui {
    JFrame window;
    JPanel titleNamePanel, starbuttonPanel;
    JLabel titleNameLabel, starbuttonLabel;
    JButton startButton;
    Font titlefont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);

    public void createUi() {

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
        window.add(titleNamePanel);
        starbuttonPanel.add(startButton);
        window.add(starbuttonPanel);

     window.setVisible(true);
    }
}
