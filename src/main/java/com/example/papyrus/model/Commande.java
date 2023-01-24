package com.example.papyrus.model;

public class Commande {
    private String numcom;
    private String datcom;
    private String obscom;

    public Commande(String numcom, String datcom, String obscom) {
        this.numcom = numcom;
        this.datcom = datcom;
        this.obscom = obscom;
    }

    public String getNumcom() {
        return numcom;
    }

    public void setNumcom(String numcom) {
        this.numcom = numcom;
    }

    public String getDatcom() {
        return datcom;
    }

    public void setDatcom(String datcom) {
        this.datcom = datcom;
    }

    public String getObscom() {
        return obscom;
    }

    public void setObscom(String obscom) {
        this.obscom = obscom;
    }

    @Override
    public String toString() {
        return numcom + datcom + obscom;
    }
}
