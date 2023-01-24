package com.example.papyrus.GUI;

import com.example.papyrus.model.Commande;
import com.example.papyrus.model.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ComboboxController {
    public ComboBox<Fournisseur> comboFou;
    public TextArea textArea;

    public void initialize(){
        try
        {

            String url ="jdbc:mysql://localhost:3307/papyrus";
            Connection con= DriverManager.getConnection(url,"root","tiger");

            Statement stm = con.createStatement();
            ResultSet resFou = stm.executeQuery("SELECT numfou,nomfou FROM fournis");
            ObservableList<Fournisseur> model = FXCollections.observableArrayList();
            while (resFou.next())
            {
                System.out.println(resFou.getString("nomfou"));
                Fournisseur fou = new Fournisseur(resFou.getString("numfou"),resFou.getString("nomfou"));
                model.add(fou);
            }

            resFou.close();
            con.close();
            comboFou.setItems(model);
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mauvaise connection !");
            alert.setContentText("La connection s'est mal passée");
            alert.showAndWait();
        }
    }

    public void Select(ActionEvent actionEvent) {
        String ID = comboFou.getSelectionModel().getSelectedItem().getNumfou();
        StringBuilder commandesTexte = new StringBuilder();
        try {
            String url = "jdbc:mysql://localhost:3307/papyrus";
            Connection con = DriverManager.getConnection(url, "root", "tiger");
            PreparedStatement stm2 = con.prepareStatement("SELECT numcom,datcom,obscom FROM entcom WHERE numfou=?");
            stm2.setString(1,ID);
            ResultSet result = stm2.executeQuery();
            //ObservableList<Commande> model2 = FXCollections.observableArrayList();

            while (result.next()){
                Commande com = new Commande(result.getString("numcom"),result.getString("datcom"),result.getString("obscom") );
                // model2.add(com);
                commandesTexte.append(com).append("\n");

            }
            result.close(); //ferme le resultat
            con.close();//ferme la connection
            textArea.setText(commandesTexte.toString());


        }catch (Exception e){
            e.getMessage();     //Message d'erreur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mauvaise connection !");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }
}
