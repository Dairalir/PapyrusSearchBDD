package com.example.papyrus.GUI;

import com.example.papyrus.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class MenuController {
    public void openExo1(ActionEvent actionEvent) throws IOException {
        App.changeFxml("papyrus");
    }

    public void openExo2(ActionEvent actionEvent) throws IOException {
        App.changeFxml("combobox");
    }

    public void openExo3(ActionEvent actionEvent) throws IOException {
        App.changeFxml("addFou");
    }
}
