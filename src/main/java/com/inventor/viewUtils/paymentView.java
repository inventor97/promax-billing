package com.inventor.viewUtils;

import com.inventor.controllers.mainCtrl;
import com.inventor.dao.impls.checksDataDAOimpls;
import com.inventor.dao.impls.subjectDAOimpls;
import com.inventor.dao.impls.teacherDAOImpls;
import com.inventor.entities.ChecksDataEntity;
import com.inventor.entities.TeachersEntity;
import com.inventor.utils.generateXlSXprinter;
import com.inventor.utils.windowCtrl;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class paymentView {

    private JFXTextField fio;
    private JFXTextField amount;
    private JFXComboBox<String> subjectCmbx;
    private JFXRadioButton cash;
    private JFXRadioButton card;
    private JFXTextArea comment;
    private GridPane monthBox;
    private GridPane teacherBox;
    private AnchorPane popBkg;
    private AnchorPane subjectChoiceNode;
    private Label choiceLb;
    private VBox subChoiceHbox;

    public paymentView(JFXTextField fio, JFXTextField amount, JFXComboBox<String> subjectCmbx, JFXRadioButton cash, JFXRadioButton card, JFXTextArea comment, GridPane monthBox, GridPane teacherBox, AnchorPane popBkg, AnchorPane subjectChoiceNode, Label choiceLb, VBox subChoiceHbox) {
        this.fio = fio;
        this.amount = amount;
        this.subjectCmbx = subjectCmbx;
        this.cash = cash;
        this.card = card;
        this.comment = comment;
        this.monthBox = monthBox;
        this.teacherBox = teacherBox;
        this.popBkg = popBkg;
        this.subjectChoiceNode = subjectChoiceNode;
        this.choiceLb = choiceLb;
        this.subChoiceHbox = subChoiceHbox;
        initCombx();
        initTxtRestr();
    }

    private void initCombx() {
        subjectCmbx.getItems().clear();
        subjectCmbx.getItems().addAll(subjectDAOimpls.getInstance().getNames());
    }

    private void initTxtRestr() {
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    amount.setText(oldValue);
                }
            }
        });
    }

    public void addTeacherBox(List<TeachersEntity> teachers) {
        teacherBox.getChildren().clear();
        int size = teachers.size();
        AnchorPane[] nodes = new AnchorPane[size];
        int iterator = 0;
        for (TeachersEntity o : teachers) {
            try {
                nodes[iterator] = FXMLLoader.load(getClass().getResource("/userNode.fxml"));
                for (Node p : nodes[iterator].getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o.getName());
                        }
                    } else if (p instanceof  JFXButton) {
                        String id = p.getId();
                        if ("cancel".equals(id)) {
                            ((JFXButton) p).setText(String.valueOf(o.getId()));
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    TeachersEntity obj = teacherDAOImpls
                                            .getInstance()
                                            .get(Long
                                                    .parseLong(((JFXButton) p)
                                                            .getText()));
                                    mainCtrl.selectedTeacherForPay.removeIf(e -> e.equals(obj));
                                    mainCtrl.payView.addTeacherBox(mainCtrl.selectedTeacherForPay);
                                    System.out.println("falnasdfjknsd");
                                }
                            });
                        }
                    } else if (p instanceof Circle) {
                        try {
                            ((Circle) p).setFill(new ImagePattern(ImageUtils.byteArray2Image(o.getImg())));
                        } catch (Exception e) {
                            ((Circle) p).setFill(Paint.valueOf("#e5e5e5"));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            iterator++;
        }
        for (int i = 0; i < nodes.length; i++) {
            teacherBox.add(nodes[i], i, 0);
        }
    }

    public void addMonthBox(List<String> months) {
        monthBox.getChildren().clear();
        List<AnchorPane> nodes = new ArrayList<>();
        int itr = 0;
        for (String o : months) {
            try {
                nodes.add(FXMLLoader.load(getClass().getResource("/monthNode.fxml")));
                for (Node p : nodes.get(itr).getChildren()) {
                    if (p instanceof Label) {
                        String id = p.getId();
                        if ("name".equals(id)) {
                            ((Label) p).setText(o);
                        }
                    } else if (p instanceof JFXButton) {
                        if ("cancel".equals(p.getId())) {
                            ((JFXButton) p).setText(o);
                            p.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    mainCtrl.selecedMonths.removeIf(e -> e.equals(o));
                                    mainCtrl.payView.addMonthBox(mainCtrl.selecedMonths);
                                }
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            itr++;
        }
        for (int i = 0; i < nodes.size(); i++) {
            monthBox.add(nodes.get(i), i, 0);
        }
    }

    public boolean checkForCorrection() {
        try {
            if (!fio.getText().equals("")) {
                if (!amount.getText().equals("")) {
                    if (cash.isSelected() || card.isSelected()) {
                        if (mainCtrl.selecedMonths.size() > 0 && mainCtrl.selectedTeacherForPay.size() > 0) {
                            if (!comment.getText().equals("")) {
                                if (!subjectCmbx.getSelectionModel().getSelectedItem().equals("")) {
                                    return true;
                                } else {
                                    windowCtrl.makeToast("O'quv fanini tanlang");
                                    return false;
                                }
                            } else {
                                windowCtrl.makeToast("Izoh kiritilamadi");
                                return false;
                            }
                        } else {
                            windowCtrl.makeToast("To'lov oyi yoki O'qituvchi tanlanmadi");
                            return false;
                        }
                    } else {
                        windowCtrl.makeToast("To'lov turini tanlang");
                        return false;
                    }
                } else  {
                    windowCtrl.makeToast("To'lovni kiriting");
                    return false;
                }
            } else {
                windowCtrl.makeToast("O'quvchi nomini kiriting");
                return false;
            }
        } catch (NullPointerException e) {
            windowCtrl.makeToast("Ma'lumotlar yetarli emas");
            return false;
        }
    }

    public void recordCheck() {
        long id = subjectDAOimpls.getInstance().getId(subjectCmbx.getSelectionModel().getSelectedItem());
        final String[] teachers = {""};
        mainCtrl.selectedTeacherForPay.forEach(e -> {
            teachers[0] += e.getName() + ",";
        });
        final String[] months = {""};
        mainCtrl.selecedMonths.forEach(e -> {
            months[0] += e + ",";
        });
        Date dateCreated = new Date(new java.util.Date().getTime());
        try {
            ChecksDataEntity obj = new ChecksDataEntity(fio.getText(),
                    Long.parseLong(amount.getText()), cash.isSelected(),
                    mainCtrl.activeUser.getId(), teachers[0], (int) id,
                    comment.getText(), dateCreated, months[0]);
            checksDataDAOimpls.getInstance().add(obj);
            generateXlSXprinter.saveSoldCheck(obj);
            fio.setText("");
            cash.setSelected(false);
            card.setSelected(false);
            teacherBox.getChildren().clear();
            monthBox.getChildren().clear();
            subjectCmbx.getSelectionModel().clearSelection();
            amount.setText("");
            comment.setText("");
            mainCtrl.selecedMonths.clear();
            mainCtrl.selectedTeacherForPay.clear();
        } catch (Exception e) {
            e.printStackTrace();
            windowCtrl.makeToast("Ma'lumotlar yuklanishida xatolik");
        }
    }

    public void initTeacherChoice() {
        popBkg.setVisible(true);
        subjectChoiceNode.setVisible(true);
        choiceLb.setText("O'qituvchini tanlang");
        subChoiceHbox.getChildren().clear();
        for (TeachersEntity o : teacherDAOImpls.getInstance().getAll()) {
            if (mainCtrl.selectedTeacherForPay.contains(o)) {
                subChoiceHbox.getChildren().add(createTeacherCheckBox(o, true));
            } else {
                subChoiceHbox.getChildren().add(createTeacherCheckBox(o, false));
            }
        }
    }

    public void initMonthChoice() {
        popBkg.setVisible(true);
        subjectChoiceNode.setVisible(true);
        choiceLb.setText("Oyni tanlang");
        subChoiceHbox.getChildren().clear();
        for (String o : MonthData.getMonths()) {
            if (mainCtrl.selecedMonths.contains(o)) {
                subChoiceHbox.getChildren().add(createMonthCheckBox(o, true));
            } else {
                subChoiceHbox.getChildren().add(createMonthCheckBox(o, false));
            }
        }
    }

    private JFXCheckBox createTeacherCheckBox(TeachersEntity obj, boolean selection) {
        JFXCheckBox chBox = new JFXCheckBox();
        chBox.setText(obj.getName());
        chBox.setPrefSize(Region.USE_COMPUTED_SIZE,35);
        chBox.setSelected(selection);
        chBox.setStyle("-fx-font-family: Poppins_regular;-fx-font-size: 15px;-fx-font-weight: bold");
        chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainCtrl.selectedTeacherForPay.add(obj);
            } else {
                mainCtrl.selectedTeacherForPay.remove(obj);
            }
        });
        return chBox;
    }

    private JFXCheckBox createMonthCheckBox(String obj, boolean selection) {
        JFXCheckBox chBox = new JFXCheckBox();
        chBox.setText(obj);
        chBox.setPrefSize(190,35);
        chBox.setSelected(selection);
        chBox.setStyle("-fx-font-family: Poppins_regular;-fx-font-size: 15px;-fx-font-weight: bold");
        chBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainCtrl.selecedMonths.add(obj);
            } else {
                mainCtrl.selecedMonths.remove(obj);
            }
        });
        return chBox;
    }

}
