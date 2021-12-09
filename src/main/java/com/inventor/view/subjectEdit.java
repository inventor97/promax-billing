package com.inventor.view;

import com.inventor.entities.SubjectsEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;

public class subjectEdit {

    private AnchorPane mainBkg;
    private AnchorPane subjectEditNode;
    private JFXTextField editedSubject;
    private JFXButton addSubject;

    public subjectEdit(AnchorPane mainBkg, AnchorPane subjectEditNode, JFXTextField editedSubject, JFXButton addSubject) {
        this.mainBkg = mainBkg;
        this.subjectEditNode = subjectEditNode;
        this.editedSubject = editedSubject;
        this.addSubject = addSubject;
    }

    public SubjectsEntity initEditingSubject(SubjectsEntity obj) {
        mainBkg.setVisible(true);
        subjectEditNode.setVisible(true);
        if (obj != null) {
            editedSubject.setText(obj.getName());
            addSubject.setText("O'zgartirish");
        }
        return obj;
    }
}
