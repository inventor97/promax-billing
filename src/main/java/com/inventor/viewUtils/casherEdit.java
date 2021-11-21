package com.inventor.viewUtils;

import com.inventor.entities.CashersEntity;
import com.inventor.utils.FileUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class casherEdit {

    private AnchorPane popupBkg;
    private AnchorPane casherEditPane;
    private JFXTextField name;
    private JFXPasswordField pass;
    private JFXButton addBtn;
    private Circle img;


    public casherEdit(AnchorPane popupBkg, AnchorPane casherEditPane, JFXTextField name, JFXPasswordField pass, JFXButton addBtn, Circle img) {
        this.popupBkg = popupBkg;
        this.casherEditPane = casherEditPane;
        this.name = name;
        this.pass = pass;
        this.addBtn = addBtn;
        this.img = img;
    }

    public void initEditingCasher(CashersEntity obj) {
        popupBkg.setVisible(true);
        casherEditPane.setVisible(true);
        if (obj != null) {
            name.setText(obj.getName());
            pass.setText(obj.getPassword());
            if (obj.getImg() != null && obj.getImg().length > 0) {
                try {
                    img.setFill(new ImagePattern(ImageUtils.byteArray2Image(obj.getImg())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            addBtn.setText("O'zgartirish");
        }
    }
}
