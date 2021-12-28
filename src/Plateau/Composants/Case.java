package Plateau.Composants;

import Plateau.Infrastructures.Route;

public class Case {
    private final int numero;
    private final String environment, production;
    private Route nord, est, sud, ouest;
    private Object sommets;

    public Case(int numero, String environment) {
        this.numero = numero;
        this.environment = environment;
        switch (environment) {
            case "Forêt":
                production = "Bois";
                break;
            case "Pré":
                production = "Laine";
                break;
            case "Champs":
                production = "Blé";
                break;
            case "Colline":
                production = "Argile";
                break;
            case "Montagne":
                production = "Minerai";
                break;
            default:
                production = "";
        }
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

    public String toString() {
        return "  " + numero + "  " + environment;
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
