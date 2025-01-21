package com.alpha.postockfrontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TestController {
    @FXML
    private Label dataLabel;

    public void loadData() {
        try {
            TestService backendService = new TestService();
            String data = backendService.getEndpointData("/hello");
            dataLabel.setText(data);
        } catch (Exception e) {
            dataLabel.setText("Error al cargar datos.");
            e.printStackTrace();
        }
    }
}
