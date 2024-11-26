package org.example.rpsdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
//senle de işim yok daha
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}