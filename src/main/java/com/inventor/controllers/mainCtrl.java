package com.inventor.controllers;

import com.inventor.dao.impls.cashersDAOImpls;
import com.inventor.dao.impls.subjectDAOimpls;
import com.inventor.dao.impls.teacherDAOImpls;
import com.inventor.entities.CashersEntity;
import com.inventor.entities.SubjectsEntity;
import com.inventor.entities.TeachersEntity;
import com.inventor.utils.FileUtils;
import com.inventor.utils.windowCtrl;
import com.inventor.viewUtils.*;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private Circle topBarAccountImg;

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
    private GridPane monthBox;

    @FXML
    private GridPane teacherBox;

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
    private AnchorPane hisContent;

    @FXML
    private GridPane subjectGridPane;

    @FXML
    private ScrollPane teacherScrollPane;

    @FXML
    private JFXButton addTeacherBtn;

    @FXML
    private JFXButton addSubjectBtn;

    @FXML
    private JFXButton cancelSubjectChoice;

    @FXML
    private AnchorPane subjectChoicePane;

    @FXML
    private ScrollPane subjectChoiceBox;

    @FXML
    private VBox subChoiceHbox;

    @FXML
    private Label choiceLb;

    @FXML
    private AnchorPane authPane;

    @FXML
    private JFXPasswordField authPassfield;

    @FXML
    private JFXButton logout;

    private NavButtons btnCtrl;
    private windowCtrl wCtrl;

    public static com.inventor.viewUtils.subjectNode subNode;
    public static subjectEdit subEditOption;
    public static SubjectsEntity subObj = new SubjectsEntity();

    public static com.inventor.viewUtils.teacherNode teachNode;
    public static teacherEdit teachEditOption;
    public static TeachersEntity teachObj = new TeachersEntity();

    public static casherNode cashNode;
    public static casherEdit cashEdit;
    public static CashersEntity cashObj = new CashersEntity();

    private File imgUrl;
    public static subjectNode subChoiceNode;
    public static List<SubjectsEntity> selectedSubjectsTeachers = new ArrayList<>();
    public static List<TeachersEntity> selectedTeacherForPay = new ArrayList<>();
    public static List<String> selecedMonths = new ArrayList<>();
    public static paymentView payView;
    public static CashersEntity activeUser = new CashersEntity();
    public static authUserView auth;


    @FXML
    void clickHandler(ActionEvent event) {
       if (event.getSource() == selectMonthpayment) {
           payView.initMonthChoice();
       } else if (event.getSource() == confirmBtn) {
           if (payView.checkForCorrection()) {
               payView.recordCheck();
               windowCtrl.makeToast("Malumot saqlandi");
           }
       } else  if (event.getSource() == addTeacherBIlling) {
           payView.initTeacherChoice();
       }
    }

    @FXML
    void clickWindowHandler(ActionEvent event) {
        wCtrl.setCtrl(event);
        if (event.getSource() == logout) {
            activeUser = new CashersEntity();
            topBarAccountImg.setFill(Paint.valueOf("#e5e5e5"));
            topBarUserName.setText("");
            popupBkg.setVisible(true);
            authPane.setVisible(true);
        }
     }

    private void setVisibilityContent() {
        teacherBox.setVisible(false);
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
            TeacherImg.setFill(Paint.valueOf("#e5e5e5"));
            imgUrl = null;
            selectedSubjectsTeachers.clear();
            addTeacher.setText("Qo'shish");
            editedTeacherName.setText("");
            teachNode.initTeacherNode(teacherDAOImpls.getInstance().getAll());
        } else if (event.getSource() == addTeacher) {
            try {
                if (!selectedSubjectsTeachers.isEmpty()
                        && !editedTeacherName.getText().equals("")) {
                    teachObj.setName(editedTeacherName.getText());
                    try {
                        if (imgUrl != null) {
                            teachObj.setImg(ImageUtils.Img2ByteArray(imgUrl));
                        }
                    } catch (IOException | IllegalArgumentException e) {
                        e.printStackTrace();
                        teachObj.setImg(null);
                    }
                    teachObj.setSubjects(mainCtrl.selectedSubjectsTeachers);
                    teacherDAOImpls.getInstance().update(teachObj);
                    TeacherImg.setFill(Paint.valueOf("#e5e5e5"));
                    imgUrl = null;
                    selectedSubjectsTeachers.clear();
                    addTeacher.setText("Qo'shish");
                    windowCtrl.makeToast("Tasdiqlandi");
                    editedTeacherName.setText("");
                } else  {
                    windowCtrl.makeToast("Ma'lumotlar to'liq ko'rsatilmadi");
                }
            } catch (NullPointerException e) {
                windowCtrl.makeToast("Ma'lumotlar to'liq ko'rsatilmadi");
            }
        } else if (event.getSource() == addSubjectinTEacher) {
            subChoiceNode.initSubjectChoiceBox(selectedSubjectsTeachers, editTeacherNode);
        } else if (event.getSource() == editTeacherImg) {
            File img = FileUtils.openFile(mainPage);
            imgUrl = img;
            TeacherImg.setFill(new ImagePattern(new Image("file:///" + img.getPath())));
        } else if (event.getSource() == cancelSubjectChoice) {
            subjectChoicePane.setVisible(false);
            if (teacherContent.isVisible()) {
                editTeacherNode.setVisible(true);
            } else  {
                popupBkg.setVisible(false);
                payView.addTeacherBox(selectedTeacherForPay);
                payView.addMonthBox(selecedMonths);
            }

        }
    }

    @FXML
    void editUserActions(ActionEvent event) {
        if (event.getSource() == addUser) {
            try {
                if (!editedUserName.getText().equals("")
                        && !userPassword.getText().equals("")) {
                    cashObj.setName(editedUserName.getText());
                    cashObj.setPassword(userPassword.getText());
                    try {
                        cashObj.setImg(ImageUtils.Img2ByteArray(imgUrl));
                    } catch (IOException e) {
                        e.printStackTrace();
                        windowCtrl.makeToast("Rasm yulanishda xatolik");
                    }
                    cashersDAOImpls.getInstance().update(cashObj);
                    addUser.setText("Qo'shish");
                    windowCtrl.makeToast("Tasdiqlandi");
                    editedUserName.setText("");
                    userPassword.setText("");
                    imgUrl = null;
                    windowCtrl.makeToast("Tasdiqlandi");
                    userImg.setFill(Paint.valueOf("#e5e5e5"));
                } else {
                    windowCtrl.makeToast("Ma'lumotlar to'liq ko'rsatilmadi");
                }
            } catch (NullPointerException e) {
                windowCtrl.makeToast("Ma'lumotlar to'liq ko'rsatilmadi");
            }
        } else if (event.getSource() == cancelUser) {
            editUserNode.setVisible(false);
            popupBkg.setVisible(false);
            cashNode.initCashersNode(cashersDAOImpls.getInstance().getAll());
        } else if (event.getSource() == editUserImg) {
            File img = FileUtils.openFile(mainPage);
            imgUrl = img;
            userImg.setFill(new ImagePattern(new Image("file:///" + img.getPath())));
        }
    }

    @FXML
    void leftClickHandler(ActionEvent event) {
        btnCtrl.setFocus(event);
        if (event.getSource() == teacherBtn) {
            setVisibilityContent();
            teacherContent.setVisible(true);
            selecedMonths.clear();
            selectedTeacherForPay.clear();
        } else if (event.getSource() == paymentBtn) {
            setVisibilityContent();
            paymentContent.setVisible(true);
            teacherBox.setVisible(true);
            monthBox.setVisible(true);
        } else if (event.getSource() == historyBtn) {
            setVisibilityContent();
            hisContent.setVisible(true);
            selecedMonths.clear();
            selectedTeacherForPay.clear();
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
        subEditOption = new subjectEdit(popupBkg, editSubjectNode, editedSubject, addSubject);
        cashEdit = new casherEdit(popupBkg, editUserNode, editedUserName, userPassword, addUser, userImg);
        teachEditOption = new teacherEdit(editTeacherNode, popupBkg, TeacherImg, editedTeacherName, addTeacher);
        subChoiceNode = new subjectNode(subjectChoicePane, subChoiceHbox);
        payView = new paymentView(fio, monthlyBill, subjectCmbx, cashRBtn, cardRBtn, omment, monthBox, teacherBox, popupBkg, subjectChoicePane,choiceLb, subChoiceHbox);
        auth = new authUserView(popupBkg, authPane, topBarAccountImg, topBarUserName, authPassfield);
    }

    @FXML
    void clickTeachersHandler(ActionEvent event) {

    }

    @FXML
    void teacherClickHandler(ActionEvent event) {
        if (event.getSource() == addTeacherBtn) {
            teachObj = new TeachersEntity();
            teachEditOption.initEditTeacherNode(teachObj);
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
            imgUrl = null;
        }
    }

    @FXML
    void clickEditSubject(ActionEvent event) {
        if (event.getSource() == cancelSubject) {
            popupBkg.setVisible(false);
            editSubjectNode.setVisible(false);
            subNode.initSubjectNode(subjectDAOimpls.getInstance().getAll());
        } else if (event.getSource() == addSubject) {
            try {
                if (!editedSubject.getText().equals("")) {
                    subObj.setName(editedSubject.getText());
                    subjectDAOimpls.getInstance().update(subObj);
                    addSubject.setText("Qo'shish");
                    windowCtrl.makeToast("Tasdiqlandi");
                    editedSubject.setText("");
                } else {
                    windowCtrl.makeToast("Ma'lumot to'liq emas");
                }
            } catch (NullPointerException e) {
                windowCtrl.makeToast("Ma'lumot to'liq emas");
            }
        }
    }
}
