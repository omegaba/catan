package Plateau.Composants;

import java.util.HashMap;

import Plateau.Infrastructures.Port;
import Plateau.Infrastructures.Route;
import Plateau.Infrastructure.Colonie;

public class Case {
    private final int numero;
    private final String environment, production;
    private Route nord, est, sud, ouest;
    private Object sommets;
    private Port port;
    private HashMap<String, Case> case_Adja = new HashMap<>();
    private HashMap<String, Colonie> MapColonie= new HashMap<>();
    private boolean voleur;
    
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
        colonie=null;
        voleur=false;
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
    
    public boolean estEmplacementLibre(){
    	if(this.colonie==null && pretDeRouteAlie() && deuxColonieDedistance()){  //pretDeRouteAlie() : methode qui check si on est pret d'une route aliée
    		return true;
    	}
    	return false;
    }

    public String toString() {
        return "  " + numero + "  " + environment;
    }

    public HashMap<String, Case> getMap() {
        return caseAdja;
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
    
    public boolean hasColonie(){
    	if(this.colonie !=null){
    		return true;
    	}
    	return false;
    }
    
    public boolean hasVoleur(){
    	return voleur==true;
    }
    
    public boolean setVoleur(){
    	this.voleur=true;
    }
    
   HashMap getMapColonie(){
   	return this.MapColonie;
   }
   
   
   
    
    
}
