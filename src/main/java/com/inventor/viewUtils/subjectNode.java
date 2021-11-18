package com.inventor.viewUtils;

import com.inventor.controllers.mainCtrl;
import com.inventor.dao.impls.subjectDAOimpls;
import com.inventor.dao.impls.teacherDAOImpls;
import com.inventor.entities.SubjectsEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;

import static com.inventor.controllers.mainCtrl.subEditOption;

public class subjectNode {

    private GridPane subjectsGridPane;
    private styles bkgStyle = new styles();
    private AnchorPane popupBkg;
    private AnchorPane subjectChoicePane;
    private HBox subChoiceHbox;

    public subjectNode(GridPane subjects) {
        this.subjectsGridPane = subjects;
    }

    public subjectNode(AnchorPane popupBkg, AnchorPane subjectChoicePane, HBox subChoiceHbox) {
        this.popupBkg = popupBkg;
        this.subjectChoicePane = subjectChoicePane;
        this.subChoiceHbox = subChoiceHbox;
    }

    public void initSubjectNode(List<SubjectsEntity> subjects) {
        subjectsGridPane.getChildren().clear();
        int size = subjects.size();
        AnchorPane[] panes = new AnchorPane[size];
        int iterator = 0;
        for (SubjectsEntity o : subjects) {
            try {
                panes[iterator]= FXMLLoader.load(getClass().getResource("/subjectNode.fxml"));
                String bkg = bkgStyle.getBackground();
                panes[iterator].setStyle("-fx-background-color: " + bkg);
                for (Node p : panes[iterator].getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        } else if ("countTeachers".equals(id)) {
                            long cntTeachers = teacherDAOImpls.getInstance().getTeachersCountOnSubject(o.getId());
                            ((Label) p).setText("o'qituvchilar soni:" + String.valueOf(cntTeachers));
                        }
                    } else if (p instanceof JFXButton) {
                        String id = p.getId();
                        if ("edit".equals(id)) {
                            ((JFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    long id  = Long.parseLong(((JFXButton) p).getText());
                                    SubjectsEntity obj = subjectDAOimpls.getInstance().get(id);
                                    mainCtrl.subObj = obj;
                                    subEditOption.initEditingSubject(obj);
                                }
                            });
                        } else if ("delete".equals(id)) {
                            ((JFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    subjectDAOimpls.getInstance().remove(Long.parseLong(((JFXButton) p).getText()));
                                    mainCtrl.subNode.initSubjectNode(subjectDAOimpls.getInstance().getAll());
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            iterator++;
        }
        fillScanGridPane(panes, size);
    }

    public void initSubjectChoiceBox(List<SubjectsEntity> subs, AnchorPane rootPane, List<SubjectsEntity> ls) {
        popupBkg.setVisible(true);
        subjectChoicePane.setVisible(true);
        rootPane.setVisible(false);
        subChoiceHbox.getChildren().clear();
        for (SubjectsEntity o : subjectDAOimpls.getInstance().getAll()) {
            for (SubjectsEntity p : subs) {
                if (o.getName().equals(p.getName())) {
                    subChoiceHbox.getChildren().add(createCheckBox(p, true, ls));
                } else {
                    subChoiceHbox.getChildren().add(createCheckBox(p, false, ls));
                }
            }
        }
    }

    private JFXCheckBox createCheckBox(SubjectsEntity obj, boolean selection, List<SubjectsEntity> ls) {
        JFXCheckBox chBox = new JFXCheckBox();
        chBox.setText(obj.getName());
        chBox.setPrefSize(190,35);
        chBox.setSelected(selection);
        if (selection) {
            ls.add(obj);
        }
        chBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (chBox.isSelected()) {
                    ls.remove(obj);
                } else {
                    ls.add(obj);
                }
            }
        });
        return chBox;
    }

    public void fillScanGridPane(AnchorPane panes[], int sizeNode) {
        subjectsGridPane.getChildren().clear();

        double rows = sizeNode/2 + 1;
        for (int i = 1; i <= rows;i++) {
            subjectsGridPane.addRow(i);
        }
        int k = 0;
         try {
            for (int i = 0; i < rows;i++) {
                for (int j = 0;j < 2;j++) {
                    subjectsGridPane.add(panes[k], j, i);
                    k++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

        }
    }
}
