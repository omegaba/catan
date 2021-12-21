package Jeu;

public class Case {
    private final int numero;
    private final String environment, production;
    private Route[] arêtes;
    private Object sommets;

    Case(int numero, String environment, String production) {
        this.numero = numero;
        this.environment = environment;
        this.production = production;
    }
}
