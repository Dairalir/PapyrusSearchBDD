package com.example.papyrus.GUI;

import com.example.papyrus.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class PapyrusController {

    public TextField search_text;
    public Button search_button;
    public Label label_nom;
    public Label label_adresse;
    public Label label_ville;
    public Label label_cp;
    public Label label_contact;
    public Label label_erreur;

    public void search_click(ActionEvent actionEvent) {
        try {

            String url = "jdbc:mysql://localhost:3307/papyrus";
            Connection con = DriverManager.getConnection(url, "root", "tiger");

            PreparedStatement stm = con.prepareStatement("SELECT * FROM fournis WHERE numfou =?");

            String id = search_text.getText();
            stm.setString(1,id);

            ResultSet result = stm.executeQuery();


            if (result.next()){
                String nom = result.getString("nomfou");
                String adresse = result.getString(("ruefou"));
                String cp = result.getString("posfou");
                String ville = result.getString("vilfou");
                String contact = result.getString("confou");
                label_nom.setText(nom);
                label_adresse.setText(adresse);
                label_cp.setText(cp);
                label_ville.setText(ville);
                label_contact.setText(contact);
            }else {
                label_erreur.setText("Erreur, le code fournisseur est inexistant ou eronné.");
                label_erreur.setStyle("-fx-text-fill: red");
                label_nom.setText("");
                label_adresse.setText("");
                label_cp.setText("");
                label_ville.setText("");
                label_contact.setText("");

            }

            stm.close();
            result.close();
            con.close();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de connexion");
            alert.setContentText("La connection s'est mal passée");
            alert.showAndWait();
        }

    }

    public void retour(ActionEvent actionEvent) throws IOException {
        App.changeFxml("menu");
    }
}
