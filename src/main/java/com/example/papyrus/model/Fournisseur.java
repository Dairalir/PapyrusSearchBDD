package com.example.papyrus.model;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.SelectionModel;

import javax.swing.*;
import java.awt.*;

public class Fournisseur {
    private String numfou;
    private String nomfou;


    public Fournisseur(String numfou, String nomfou) {
        this.numfou = numfou;
        this.nomfou = nomfou;

    }

    public String getNumfou() {
        return numfou;
    }

    public void setNumfou(String numfou) {
        this.numfou = numfou;
    }

    public String getNomfou() {
        return nomfou;
    }

    public void setNomfou(String nomfou) {
        this.nomfou = nomfou;
    }
    @Override
    public String toString() {
        return nomfou;
    }
}
