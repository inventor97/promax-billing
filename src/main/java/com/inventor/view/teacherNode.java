package com.inventor.view;

import com.inventor.controllers.mainCtrl;
import com.inventor.dto.impls.teacherDAOImpls;
import com.inventor.entities.TeachersEntity;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.List;

public class teacherNode {

    private HBox teachersHb;
    private styles st = new styles();

    public teacherNode(HBox teachersHb) {
        this.teachersHb = teachersHb;
    }

    public void initTeacherNode(List<TeachersEntity> teachers) {
        teachersHb.getChildren().clear();
        int size = teachers.size();
        AnchorPane[] nodes = new AnchorPane[size];
        int iterator = 0;
        for (TeachersEntity o : teachers) {
            try {
                nodes[iterator] = FXMLLoader.load(getClass().getResource("/teacherNode.fxml"));
                HBox.setMargin(nodes[iterator], new Insets(22, 0, 0, 0));
                for (Node p : nodes[iterator].getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        } else if ("speciality".equals(id)) {
                            ((Label) p).setText(o.subjects2String());
                        }
                    } else if (p instanceof JFXButton) {
                        String id = p.getId();
                        if ("edit".equals(id)) {
                            ((JFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    mainCtrl.teachObj = teacherDAOImpls.getInstance().get(Long.parseLong(((JFXButton) p).getText()));
                                    mainCtrl.teachEditOption.initEditTeacherNode(mainCtrl.teachObj);
                                }
                            });
                        } else if ("delete".equals(id)) {
                            ((JFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    teacherDAOImpls.getInstance().remove(Long.parseLong(((JFXButton) p).getText()));
                                    mainCtrl.teachNode.initTeacherNode(teacherDAOImpls.getInstance().getAll());
                                }
                            });
                        }
                    } else if (p instanceof Circle) {
                        String id = p.getId();
                        if ("accountImg".equals(id)) {
                            try {
                                if (o.getImg() != null) {
                                    ((Circle) p).setFill(new ImagePattern(ImageUtils.byteArray2Image(o.getImg())));
                                }
                            } catch (NullPointerException e) {
                                ((Circle) p).setFill(Paint.valueOf("#e5e5e5"));
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            teachersHb.getChildren().add(nodes[iterator]);
            iterator++;
        }

    }

}
