package com.inventor.viewUtils;

import com.inventor.entities.TeachersEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class teacherEdit {

    private AnchorPane teacherEditPane;
    private AnchorPane popuoBkg;
    private Circle accountImg;
    private JFXTextField fioField;
    private JFXButton addSubjetBtn;
    private JFXButton addImageBtn;
    private JFXButton add;
    private JFXButton cancel;

    public teacherEdit(AnchorPane teacherEditPane, AnchorPane popuoBkg, Circle accountImg, JFXTextField fioField, JFXButton addSubjetBtn, JFXButton addImageBtn, JFXButton add, JFXButton cancel) {
        this.teacherEditPane = teacherEditPane;
        this.popuoBkg = popuoBkg;
        this.accountImg = accountImg;
        this.fioField = fioField;
        this.addSubjetBtn = addSubjetBtn;
        this.addImageBtn = addImageBtn;
        this.add = add;
        this.cancel = cancel;
    }

    public void initEDitTeacherNOde(TeachersEntity obj) {
        popuoBkg.setVisible(true);
        teacherEditPane.setVisible(true);
        if (obj != null) {
            if (!obj.getImg().isEmpty()) {
                accountImg.setFill(new ImagePattern(new Image(obj.getImg())));
            }
            fioField.setText(obj.getName());
        }
    }
}
