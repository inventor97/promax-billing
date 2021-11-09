package com.inventor.controllers;

import com.inventor.viewUtils.NavButtons;
import com.inventor.viewUtils.windowCtrl;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.ir.SwitchNode;

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
    private JFXButton casherBtn;

    @FXML
    private JFXButton historyBtn;

    @FXML
    private Label context_tile;

    @FXML
    private AnchorPane paymentPage;

    @FXML
    private VBox checkContent;

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
    private AnchorPane databasePage;

    @FXML
    private HBox teachersHb;

    @FXML
    private AnchorPane node;

    @FXML
    private AnchorPane accountImg;

    @FXML
    private Label name;

    @FXML
    private Label speciality;

    @FXML
    private AnchorPane subjectNode;

    @FXML
    private Label name1;

    @FXML
    private Label countTeachers;

    @FXML
    private AnchorPane casherPage;

    @FXML
    private JFXButton addCasher;

    @FXML
    private ScrollPane casherScrl;

    @FXML
    private GridPane casherGridPane;

    @FXML
    private AnchorPane userNode;

    @FXML
    private AnchorPane userImg1;

    @FXML
    private Label name2;

    @FXML
    private JFXButton edit;

    @FXML
    private JFXButton cancel;

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
    private AnchorPane userImg;

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
    private AnchorPane TeacherImg;

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
    private ImageView casherIcon;

    @FXML
    private ImageView hisIcon;

    private NavButtons btnCtrl;
    private windowCtrl  wCtrl;

    @FXML
    void clickHandler(ActionEvent event) {

    }

    @FXML
    void clickWindowHandler(ActionEvent event) {
        wCtrl.setCtrl(event);
    }

    @FXML
    void editTeacherActions(ActionEvent event) {

    }

    @FXML
    void editUserActions(ActionEvent event) {

    }

    @FXML
    void leftClickHandler(ActionEvent event) {
        btnCtrl.setFocus(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCtrl = new NavButtons(teacherBtn, paymentBtn, casherBtn, historyBtn, teacherIcon, paymentIcon, casherIcon, hisIcon);
        wCtrl = new windowCtrl(close, swipe);
    }
}
