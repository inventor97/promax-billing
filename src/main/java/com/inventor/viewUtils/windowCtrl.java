package com.inventor.viewUtils;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class windowCtrl {

    private JFXButton close;
    private JFXButton swipe;

    public windowCtrl(JFXButton close, JFXButton swipe) {
        this.close = close;
        this.swipe = swipe;
    }

    public void setCtrl(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        if (event.getSource() == close) {
            stage.close();
            System.exit(0);
        } else if (event.getSource() == swipe) {
            stage.setIconified(true);
        }
    }
}
