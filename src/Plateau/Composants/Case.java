package Plateau.Composants;

import java.util.HashMap;

import Plateau.Infrastructures.Colonie;
import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.Route;

public class Case {
    private final int numero;
    private final String environment, production;
    private Route nord, est, sud, ouest;
    private Object sommets;
    private Port port;
    private HashMap<String, Case> caseAdja = new HashMap<>();

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
        port = null;
    }

    public void setPort(Port port) {
        this.port = port;
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
        return "  " + numero + "  " + environment.substring(0, 2);
    }

    public HashMap<String, Case> getMap() {
        return caseAdja;
    }

    public int getNumero() {
        return numero;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getRessource() {
        return production;
    }

    public HashMap<String, Colonie> getColonie() {
        return null;
    }
}
