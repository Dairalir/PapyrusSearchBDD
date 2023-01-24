package com.example.papyrus.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddFouController {
    public TextField addNom;
    public TextField addRue;
    public TextField addCP;
    public TextField addContact;
    public TextField addVille;
    public Button buttonAdd;
    public Button buttonCancel;


    public void ClickAdd(ActionEvent actionEvent) {

        String nom = addNom.getText();
        String patternNom = "^[A-Z]*$";
        String rue = addRue.getText();
        String patternRue = "^[0-9A-Za-z ]*$";
        String CP = addCP.getText();
        String patternCP = "^[0-9][0-9][0-9][0-9][0-9]?$";
        String ville = addVille.getText();
        String patternVille = "^[A-Za-z]*$";
        String contact = addContact.getText();
        String patternContact = "^[A-Za-z]$*";

        Pattern n = Pattern.compile(patternNom);
        Pattern r = Pattern.compile(patternRue);
        Pattern cp = Pattern.compile(patternCP);
        Pattern v = Pattern.compile(patternVille);
        Pattern c = Pattern.compile(patternContact);

        Matcher mn = n.matcher(nom);
        Matcher mr = r.matcher(rue);
        Matcher mcp = cp.matcher(CP);
        Matcher mv = v.matcher(ville);
        Matcher mc = c.matcher(contact);


        if (mn.find() && mr.find() && mcp.find() && mv.find() && mc.find()){
            try {
                String url = "jdbc:mysql://localhost:3307/papyrus";
                Connection con = DriverManager.getConnection(url, "root", "tiger");
                PreparedStatement stm = con.prepareStatement("INSERT INTO fournis (numfou, nomfou, ruefou, vilfou, posfou, confou) VALUES (?, ?, ?, ?, ?, ?)");

                Statement stm2 = con.createStatement();
                ResultSet idMax = stm2.executeQuery("SELECT MAX(numfou) AS max_id FROM fournis");

                if(idMax.next()){
                    int id2 = idMax.getInt("max_id") + 1;
                    stm.setInt(1, id2 );
                }

                stm.setString(2, addNom.getText());
                stm.setString(3, addRue.getText());
                stm.setString(4, addVille.getText());
                stm.setString(5, addCP.getText());
                stm.setString(6, addContact.getText());

                stm.execute();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("le fournisseur a bien été ajouter");
                alert.showAndWait();

                stm.close();
                con.close();

            } catch (Exception e) {
                e.getMessage();     //Message d'erreur
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mauvaise connection !");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setContentText("Les champs ne sont pas remplie correctement");
            alert.showAndWait();
        }
}

    public void ClickCancel(ActionEvent actionEvent) {
        addNom.clear();
        addRue.clear();
        addCP.clear();
        addVille.clear();
        addContact.clear();
    }
}
