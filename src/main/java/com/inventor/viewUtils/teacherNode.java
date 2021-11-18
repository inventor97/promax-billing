package com.inventor.viewUtils;

import com.inventor.entities.TeachersEntity;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.awt.*;
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
                        // TODO: 11/14/2021 generating editing teacher node
                        String id = p.getId();
                        if ("edit".equals(id)) {
                            ((JFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    String id = ((JFXButton) p).getText();
                                }
                            });
                        }
                    } else if (p instanceof Circle) {
                        String id = p.getId();
                        if ("accountImg".equals(id)) {
                            if (o.getImg() != null && !o.getImg().isEmpty()) {
                                ((Circle) p).setFill(new ImagePattern(new Image(o.getImg())));
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
