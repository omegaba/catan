package Gui.Vue;

public class Game {
    Ui ui=new Ui();

    public Game() {

        ui.createUi();
    }

    public static void main(String[] args) {
        new Game();
    }
}
