package Jeu;

public class Case {
    private final int numero;
    private final String environment, production;
    private Route nord, est, sud, ouest;
    private Object sommets;

    Case(int numero, String environment, String production) {
        this.numero = numero;
        this.environment = environment;
        this.production = production;
    }

    void setRoute(String location, Route route) {
        switch (location) {
            case "nord":
                nord = route;
                break;
            case "est":
                est = route;
                break;
            case "sud":
                sud = route;
                break;
            case "ouest":
                ouest = route;
        }
    }

    Route getRoute(String location) {
        switch (location) {
            case "nord":
                return nord;
            case "est":
                return est;
            case "sud":
                return sud;
            case "ouest":
                return ouest;
            default:
                return null;
        }
    }

    int getNumero() {
        return numero;
    }

    String getEnvironment() {
        return environment;
    }

    String getProduction() {
        return production;
    }
}
