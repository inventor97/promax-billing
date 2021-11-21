package com.inventor.viewUtils;

import com.inventor.controllers.mainCtrl;
import com.inventor.dao.impls.cashersDAOImpls;
import com.inventor.entities.CashersEntity;
import com.inventor.utils.windowCtrl;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class authUserView {

    private AnchorPane popupBkg;
    private AnchorPane authPane;
    private Circle accountImg;
    private Label userName;
    private JFXPasswordField authPassField;


    public void initAuthUser() {
        popupBkg.setVisible(true);
        authPane.setVisible(true);
        List<CashersEntity> obj = new ArrayList<>(cashersDAOImpls.getInstance().getAll());
        authPassField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    for (CashersEntity o : obj) {
                        if (o.getPassword().equals(authPassField.getText())) {
                            try {
                                accountImg.setFill(new ImagePattern(ImageUtils.byteArray2Image(o.getImg())));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mainCtrl.activeUser = o;
                            userName.setText(o.getName());
                        }
                    }
                    popupBkg.setVisible(false);
                    authPane.setVisible(false);
                    authPassField.setText("");
                } else {
                    windowCtrl.makeToast("Xavfsizlik kodi tasdiqlanmadi");
                }
            }
        });
    }
}
