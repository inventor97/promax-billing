package com.inventor.viewUtils;

import com.inventor.controllers.mainCtrl;
import com.inventor.entities.TeachersEntity;
import com.inventor.utils.windowCtrl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.IOException;

public class teacherEdit {

    private AnchorPane teacherEditPane;
    private AnchorPane popuoBkg;
    private Circle accountImg;
    private JFXTextField fioField;
    private JFXButton add;

    public teacherEdit(AnchorPane teacherEditPane, AnchorPane popuoBkg, Circle accountImg, JFXTextField fioField, JFXButton add) {
        this.teacherEditPane = teacherEditPane;
        this.popuoBkg = popuoBkg;
        this.accountImg = accountImg;
        this.fioField = fioField;
        this.add = add;
    }

    public void initEditTeacherNode(TeachersEntity obj) {
        popuoBkg.setVisible(true);
        teacherEditPane.setVisible(true);
        if (obj != null) {
            try {
                if (obj.getImg() != null) {
                    accountImg.setFill(new ImagePattern(ImageUtils.byteArray2Image(obj.getImg())));
                }
                obj.getSubjects(mainCtrl.selectedSubjectsTeachers);
            } catch(IOException | NullPointerException e){
                e.printStackTrace();
                accountImg.setFill(Paint.valueOf("#e5e5e5"));
            }
            fioField.setText(obj.getName());
            add.setText("O'zgartirish");
        }
    }
}
