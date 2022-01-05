package Plateau.Composants;

import java.util.HashMap;

import Plateau.Infrastructures.Colonie;
import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.Route;
import Plateau.Infrastructures.Colonie;

public class Case {
    private final int numero;
    private final String environement, production;
    private Route haut, droit, bas, gauche;
    private Port port;
    private HashMap<String, Case> case_Adja = new HashMap<>();
    private HashMap<String, Colonie> MapColonie = new HashMap<>();
    private boolean voleur;

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
        voleur=false;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public void setRoute(String location, Route route) {
        switch (location) {
            case "haut":
                haut = route;
                break;
            case "droit":
                droit = route;
                break;
            case "bas":
                bas = route;
                break;
            case "gauche":
                gauche = route;
        }
    }

    public Route getRoute(String location) {
        switch (location) {
            case "haut":
                return haut;
            case "droit":
                return droit;
            case "bas":
                return bas;
            case "gauche":
                return gauche;
            default:
                return null;
        }
    }

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

    public boolean hasPort() {
        return port != null;
    }

    public Port getPort() {
        return port;
    }
    
    public boolean hasVoleur(){
    	return voleur==true;
    }
    
    public boolean setVoleur(){
    	this.voleur=true;
    }
    public HashMap<String, Colonie> getColonie(){
        return this.MapColonie;
    }
}
