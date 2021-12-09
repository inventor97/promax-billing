package com.inventor.view;

import com.inventor.controllers.mainCtrl;
import com.inventor.dto.impls.cashersDAOImpls;
import com.inventor.entities.CashersEntity;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class authUserView {

    private AnchorPane popupBkg;
    private AnchorPane authPane;
    private Circle accountImg;
    private Label userName;
    private JFXPasswordField authPassField;

    public authUserView(AnchorPane popupBkg, AnchorPane authPane, Circle accountImg, Label userName, JFXPasswordField authPassField) {
        this.popupBkg = popupBkg;
        this.authPane = authPane;
        this.accountImg = accountImg;
        this.userName = userName;
        this.authPassField = authPassField;
        List<CashersEntity> obj = new ArrayList<>(cashersDAOImpls.getInstance().getFullData());
        authPassField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    for (CashersEntity o : obj) {
                        if (o.getPassword().equals(authPassField.getText())) {
                            if (o.getImg() != null) {
                                try {
                                    accountImg.setFill(new ImagePattern(ImageUtils.byteArray2Image(o.getImg())));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            mainCtrl.activeUser = o;
                            userName.setText(o.getName());
                            popupBkg.setVisible(false);
                            authPane.setVisible(false);
                            authPassField.setText("");
                            mainCtrl.cashNode.initCashersNode(cashersDAOImpls.getInstance().getAll());
                        }
                    }
                }
            }
        });
    }

}
