package Plateau.Composants;

import java.util.HashMap;

import Plateau.Infrastructures.Colonie;
import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.Route;
import Plateau.Infrastructures.Colonie;

public class Case {
    private final int numero;
    private final String environement, production;
    private Route nord, est, sud, ouest;
    private Port port;
    private HashMap<String, Case> case_Adja = new HashMap<>();
    private HashMap<String, Colonie> MapColonie = new HashMap<>();

    public Case(int numero, String environement) {
        this.numero = numero;
        this.environement = environement;
        switch (environement) {
            case "Forêt":
                production = "Bois";
                break;
            case "Pré":
                production = "Laine";
                break;
            case "Champs":
                production = "Blé";
                break;
            case "Coline":
                production = "Argile";
                break;
            case "Montagne":
                production = "Minerai";
                break;
            default:
                production = "";
        }
        port = null;
        MapColonie = null;
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

    // public boolean estEmplacementLibre() {
    //     if (this.MapColonie == null && pretDeRouteAlie() && deuxColonieDedistance()) { // pretDeRouteAlie() : methode qui
    //                                                                                 // check si on est pret d'une route
    //                                                                                 // aliée
    //         return true;
    //     }
    //     return false;
    // }

    public String toString() {
        return "  " + numero + "  " + environement.substring(0, 2);
    }

    public HashMap<String, Case> getMap() {
        return case_Adja;
    }

    public int getNumero() {
        return numero;
    }

    public String getEnvironement() {
        return environement;
    }

    public String getRessource() {
        return production;
    }

    public HashMap<String, Colonie> getMapColonie() {
        return this.MapColonie;
    }

    public boolean hasColonie() {
        if (!this.MapColonie.isEmpty()) {
            return true;
        }
        return false;
    }
}
