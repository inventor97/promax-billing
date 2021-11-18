package com.inventor.controllers;

import com.inventor.dao.impls.cashersDAOImpls;
import com.inventor.dao.impls.subjectDAOimpls;
import com.inventor.dao.impls.teacherDAOImpls;
import com.inventor.entities.CashersEntity;
import com.inventor.entities.SubjectsEntity;
import com.inventor.utils.FileUtils;
import com.inventor.utils.windowCtrl;
import com.inventor.viewUtils.*;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class mainCtrl  implements Initializable {


    @FXML
    private AnchorPane mainPage;

    @FXML
    private VBox leftToolBar;

    @FXML
    private AnchorPane topBar;

    @FXML
    private JFXButton swipe;

    @FXML
    private JFXButton close;

    @FXML
    private AnchorPane topBarAccountImg;

    @FXML
    private Label topBarUserName;

    @FXML
    private JFXButton teacherBtn;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private JFXButton historyBtn;

    @FXML
    private Label context_tile;

    @FXML
    private VBox paymentContent;

    @FXML
    private JFXTextField fio;

    @FXML
    private JFXComboBox<String> subjectCmbx;

    @FXML
    private JFXTextField monthlyBill;

    @FXML
    private JFXButton addTeacherBIlling;

    @FXML
    private JFXRadioButton cashRBtn;

    @FXML
    private JFXRadioButton cardRBtn;

    @FXML
    private JFXButton selectMonthpayment;

    @FXML
    private JFXTextArea omment;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private JFXTextField searchHis;

    @FXML
    private JFXButton generateXLS;

    @FXML
    private AnchorPane teacherContent;

    @FXML
    private HBox teachersHb;


    @FXML
    private JFXButton addCasher;

    @FXML
    private ScrollPane casherScrl;

    @FXML
    private VBox casherVbox;

    @FXML
    private AnchorPane monthBox;

    @FXML
    private AnchorPane teacherNode;

    @FXML
    private AnchorPane rightSideBar;

    @FXML
    private VBox filterPane;

    @FXML
    private AnchorPane popupBkg;

    @FXML
    private AnchorPane editSubjectNode;

    @FXML
    private JFXButton cancelSubject;

    @FXML
    private JFXButton addSubject;

    @FXML
    private JFXTextField editedSubject;

    @FXML
    private AnchorPane editUserNode;

    @FXML
    private Circle userImg;

    @FXML
    private JFXTextField editedUserName;

    @FXML
    private JFXPasswordField userPassword;

    @FXML
    private JFXButton editUserImg;

    @FXML
    private JFXButton cancelUser;

    @FXML
    private JFXButton addUser;

    @FXML
    private AnchorPane editTeacherNode;

    @FXML
    private Circle TeacherImg;

    @FXML
    private JFXTextField editedTeacherName;

    @FXML
    private JFXButton editTeacherImg;

    @FXML
    private JFXButton cancelTeacher;

    @FXML
    private JFXButton addTeacher;

    @FXML
    private JFXButton addSubjectinTEacher;

    @FXML
    private ImageView teacherIcon;

    @FXML
    private ImageView paymentIcon;


    @FXML
    private ImageView hisIcon;

    @FXML
    AnchorPane hisContent;

    @FXML
    GridPane subjectGridPane;

    @FXML
    ScrollPane teacherScrollPane;

    @FXML
    JFXButton addTeacherBtn;

    @FXML
    JFXButton addSubjectBtn;

    @FXML
    AnchorPane subjectChoicePane;

    @FXML
    ScrollPane subjectChoiceBox;

    @FXML
    VBox subChoiceHbox;

    private NavButtons btnCtrl;
    private windowCtrl wCtrl;
    public static com.inventor.viewUtils.subjectNode subNode;
    public static subjectEdit subEditOption;
    public static com.inventor.viewUtils.teacherNode teachNode;
    public static teacherEdit teachEditOption;
    public static casherNode cashNode;
    public static casherEdit cashEdit;

    public static SubjectsEntity subObj = new SubjectsEntity();
    public static CashersEntity cashObj = new CashersEntity();

    @FXML
    void clickHandler(ActionEvent event) {

    }

    @FXML
    void clickWindowHandler(ActionEvent event) {
        wCtrl.setCtrl(event);
    }

    private void setVisibilityContent() {
        teacherNode.setVisible(false);
        monthBox.setVisible(false);
        paymentContent.setVisible(false);
        teacherContent.setVisible(false);
        hisContent.setVisible(false);
    }

    @FXML
    void editTeacherActions(ActionEvent event) {
        if (event.getSource() == cancelTeacher) {
            popupBkg.setVisible(false);
            editTeacherNode.setVisible(false);
        } else if (event.getSource() == addTeacher) {

        }
    }

    @FXML
    void editUserActions(ActionEvent event) {
        String imgUrl = "";
        if (event.getSource() == addUser) {
            cashObj.setName(editedUserName.getText());
            cashObj.setPassword(userPassword.getText());
            cashObj.setImg(imgUrl);
            cashersDAOImpls.getInstance().update(cashObj);
            addUser.setText("Qo'shish");
            windowCtrl.makeToast("Tasdiqlandi");
            editedUserName.setText("");
            userPassword.setText("");
            imgUrl = "";
        } else if (event.getSource() == cancelUser) {
            editUserNode.setVisible(false);
            popupBkg.setVisible(false);
            cashNode.initCashersNode(cashersDAOImpls.getInstance().getAll());
        } else if (event.getSource() == editUserImg) {
            File img = FileUtils.openFile(mainPage);
            imgUrl = img.getName();
            userImg.setFill(new ImagePattern(new Image(imgUrl)));
        }
    }

    @FXML
    void leftClickHandler(ActionEvent event) {
        btnCtrl.setFocus(event);
        if (event.getSource() == teacherBtn) {
            setVisibilityContent();
            teacherContent.setVisible(true);
        } else if (event.getSource() == paymentBtn) {
            setVisibilityContent();
            paymentContent.setVisible(true);
            teacherNode.setVisible(true);
            monthBox.setVisible(true);
        } else if (event.getSource() == historyBtn) {
            setVisibilityContent();
            hisContent.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCtrl = new NavButtons(teacherBtn, paymentBtn, historyBtn, teacherIcon, paymentIcon, hisIcon);
        wCtrl = new windowCtrl(close, swipe);
        subNode  = new subjectNode(subjectGridPane);
        teachNode = new teacherNode(teachersHb);
        subNode.initSubjectNode(subjectDAOimpls.getInstance().getAll());
        teachNode.initTeacherNode(teacherDAOImpls.getInstance().getAll());
        teacherScrollPane.setOnScroll(event -> {
            if(event.getDeltaX() == 0 && event.getDeltaY() != 0) {
                teacherScrollPane.setHvalue(teacherScrollPane.getHvalue() - event.getDeltaY() / 500);
            }
        });
        cashNode = new casherNode(casherVbox);
        cashNode.initCashersNode(cashersDAOImpls.getInstance().getAll());
        subEditOption = new subjectEdit(popupBkg, editSubjectNode, editedSubject, addSubject);
        cashEdit = new casherEdit(popupBkg, editUserNode, editedUserName, userPassword, addUser, userImg);
    }

    @FXML
    void clickTeachersHandler(ActionEvent event) {

    }

    @FXML
    void teacherClickHandler(ActionEvent event) {
        if (event.getSource() == addTeacherBtn) {
            popupBkg.setVisible(true);
            editTeacherNode.setVisible(true);
            addTeacher.setText("Qo'shish");
        } else if (event.getSource() == addSubjectBtn) {
            subObj = new SubjectsEntity();
            subEditOption.initEditingSubject(subObj);
            addSubject.setText("Qo'shish");
        } else  if (event.getSource() == addCasher) {
            cashObj = new CashersEntity();
            cashEdit.initEditingCasher(cashObj);
            addUser.setText("Qo'shish");
            userImg.setFill(Paint.valueOf("#e5e5e5"));
        }
    }

    @FXML
    void clickEditSubject(ActionEvent event) {
        if (event.getSource() == cancelSubject) {
            popupBkg.setVisible(false);
            editSubjectNode.setVisible(false);
            subNode.initSubjectNode(subjectDAOimpls.getInstance().getAll());
        } else if (event.getSource() == addSubject) {
            subObj.setName(editedSubject.getText());
            subjectDAOimpls.getInstance().update(subObj);
            addSubject.setText("Qo'shish");
            windowCtrl.makeToast("Tasdiqlandi");
            editedSubject.setText("");
        }
    }
}
