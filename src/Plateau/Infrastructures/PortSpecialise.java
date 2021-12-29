package Plateau.Infrastructures;

import Plateau.Composants.Case;

public class PortSpecialise extends Port {

    private final String ressource;

    public PortSpecialise(Case emplacement, String ressource) {
        super(emplacement);
        this.ressource = ressource;
    }

    public String getRessource() {
        return ressource;
    }
}