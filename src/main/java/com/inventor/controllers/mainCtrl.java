package com.inventor.controllers;

import com.inventor.Main;
import com.inventor.dao.impls.*;
import com.inventor.entities.*;
import com.inventor.utils.FileUtils;
import com.inventor.utils.dateUtils;
import com.inventor.utils.generateXLS;
import com.inventor.utils.windowCtrl;
import com.inventor.viewUtils.*;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class mainCtrl  implements Initializable {


    @FXML
    private AnchorPane mainPage;

    @FXML
    private AnchorPane topBar;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXButton swipe;

    @FXML
    private JFXButton close;

    @FXML
    private Circle topBarAccountImg;

    @FXML
    private Label topBarUserName;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private VBox paymentContent;

    @FXML
    private JFXTextField fio;

    @FXML
    private JFXButton addSubjectBilling;

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
    private HBox cardHolderPane;

    @FXML
    private JFXTextField cardHolderTxt;

    @FXML
    private JFXTextArea omment;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private AnchorPane hisContent;

    @FXML
    private JFXTextField searchHis;

    @FXML
    private TableView<tableClass> statTableView;

    @FXML
    private TableColumn<tableClass, Integer> no;

    @FXML
    private TableColumn<tableClass, String> name;

    @FXML
    private TableColumn<tableClass, String> subject;

    @FXML
    private TableColumn<tableClass, String> teacher;

    @FXML
    private TableColumn<tableClass, String> month;

    @FXML
    private TableColumn<tableClass, Long> amount;

    @FXML
    private TableColumn<tableClass, ChecksDataEntity> object;

    @FXML
    private Label sumUpLb;

    @FXML
    private JFXButton generateXLS;

    @FXML
    private JFXButton openRightSideBar;

    @FXML
    private AnchorPane expensesPane;

    @FXML
    private AnchorPane teacherContent;

    @FXML
    private ScrollPane teacherScrollPane;

    @FXML
    private HBox teachersHb;

    @FXML
    private JFXButton addTeacherBtn;

    @FXML
    private JFXButton addSubjectBtn;

    @FXML
    private GridPane subjectGridPane;

    @FXML
    private JFXButton addCasher;

    @FXML
    private ScrollPane casherScrl;

    @FXML
    private VBox casherVbox;

    @FXML
    private AnchorPane subjectBox;

    @FXML
    private AnchorPane monthBox;

    @FXML
    private AnchorPane teacherBox;

    @FXML
    private VBox leftToolBar;

    @FXML
    private JFXButton teacherBtn;

    @FXML
    private ImageView teacherIcon;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private ImageView paymentIcon;

    @FXML
    private JFXButton historyBtn;

    @FXML
    private ImageView hisIcon;

    @FXML
    private JFXButton expencesBtn;

    @FXML
    private ImageView expensesIcon;

    @FXML
    private Label context_tile;

    @FXML
    private AnchorPane rightSideBar;

    @FXML
    private VBox filterPane;

    @FXML
    private JFXComboBox<String> subjectFilter;

    @FXML
    private JFXComboBox<String> monthFilter;

    @FXML
    private JFXComboBox<String> teacherFilter;

    @FXML
    private JFXRadioButton cashFilter;

    @FXML
    private JFXRadioButton cardFilter;

    @FXML
    private Spinner<Integer> yearSpinner;

    @FXML
    private Spinner<Integer> dateSpinner;

    @FXML
    private JFXComboBox<String> monthCmbx;

    @FXML
    private JFXRadioButton wholeMonthRBtn;

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
    private AnchorPane subjectChoicePane;

    @FXML
    private Label choiceLb;

    @FXML
    private ScrollPane subjectChoiceBox;

    @FXML
    private VBox subChoiceHbox;

    @FXML
    private JFXButton cancelSubjectChoice;

    @FXML
    private AnchorPane authPane;

    @FXML
    private JFXPasswordField authPassfield;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private HBox winTools;

    @FXML
    private ImageView rightSideIcon;

    @FXML
    private JFXTextField expenseAmount;

    @FXML
    private JFXTextField expensesComment;

    @FXML
    private JFXButton saveExpenses;

    @FXML
    private Spinner<Integer> expYearSpinner;

    @FXML
    private JFXComboBox<String> expMonthCmbx;

    @FXML
    private Spinner<Integer> expDaySpinner;

    @FXML
    private TableView<expTableClass> expensesTableView;

    @FXML
    private TableColumn<expTableClass, Integer> expNo;

    @FXML
    private TableColumn<expTableClass, Long> expAmount;

    @FXML
    private TableColumn<expTableClass, String> expBy;

    @FXML
    private TableColumn<expTableClass, String> expDate;

    @FXML
    private TableColumn<expTableClass, String> expComment;

    @FXML
    private TableColumn<expTableClass, String> expObj;

    @FXML
    private JFXRadioButton expWholeMonth;

    @FXML
    private Label expSummUp;

    @FXML
    private JFXButton generateExpXls;

    @FXML
    private AnchorPane expensesStatsPane;


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
    public static List<SubjectsEntity> selecedSubjects = new ArrayList<>();
    public static paymentView payView;
    public static CashersEntity activeUser = new CashersEntity();
    public static authUserView auth;
    private static JFXAutoCompletePopup<String> searchName = new JFXAutoCompletePopup<>();


    private void initSearchAutoCompliation() {
        List<String> names = new ArrayList<>(checksDataDAOimpls.getInstance().getNames());
        searchName.getSuggestions().setAll(names);
        searchName.setPrefSize(345, 350);
        searchName.setStyle("-fx-text-fill: #2a4e8b;" +
                "    -fx-font-family: \"Poppins_regular\";" +
                "    -fx-font-weight: bold;" +
                "    -fx-font-size: 12;" +
                "    -fx-text-alignment: center;" +
                "    -fx-selection-bar: #faf3dd;" +
                "    -fx-background-color: transparent;");
        searchName.setSelectionHandler(e -> {
            searchHis.setText(e.getObject());
            setTableValues(convertToTable(checksDataDAOimpls.getInstance().getByName(e.getObject())));
        });
        searchHis.textProperty().addListener(observable -> {
            searchName.filter( string -> string.toLowerCase().contains(searchHis.getText().toLowerCase()));
            if (searchName.getFilteredSuggestions().isEmpty() || searchHis.getText().isEmpty()) {
                searchName.hide();
            } else {
                searchName.show(searchHis);
            }
        });

    }


    @FXML
    void clickHandler(ActionEvent event) {
       if (event.getSource() == selectMonthpayment) {
           payView.initMonthChoice();
       } else if (event.getSource() == confirmBtn) {
           if (payView.checkForCorrection()) {
               popupBkg.setVisible(true);
               spinner.setVisible(true);
               new Thread(payView.initRecordTask()).start();
           }
       } else  if (event.getSource() == addTeacherBIlling) {
           payView.initTeacherChoice();
       } else  if (event.getSource() == addSubjectBilling) {
           payView.initSubjectChoice();
       }
    }

    private void initSavingStats() {
        popupBkg.setVisible(true);
        spinner.setVisible(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<tableClass> ls = new ArrayList<>(statTableView.getItems());
                com.inventor.utils.generateXLS.saveStats(ls);
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                windowCtrl.makeToast("Hisobot saqlandi");
                popupBkg.setVisible(false);
                spinner.setVisible(false);
            }
        });
        task.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                windowCtrl.makeToast("Error missing argument");
                popupBkg.setVisible(false);
                spinner.setVisible(false);
            }
        });
        new Thread(task).start();
    }

    private void initExpSavingStats() {
        popupBkg.setVisible(true);
        spinner.setVisible(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<expTableClass> ls = new ArrayList<>(expensesTableView.getItems());
                com.inventor.utils.generateExpXLS.saveStats(ls);
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                windowCtrl.makeToast("Hisobot saqlandi");
                popupBkg.setVisible(false);
                spinner.setVisible(false);
            }
        });
        task.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                windowCtrl.makeToast("Error missing argument");
                popupBkg.setVisible(false);
                spinner.setVisible(false);
            }
        });
        new Thread(task).start();
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
            expensesStatsPane.setVisible(false);
        }
     }

    private void setVisibilityContent() {
        teacherBox.setVisible(false);
        monthBox.setVisible(false);
        subjectBox.setVisible(false);
        paymentContent.setVisible(false);
        teacherContent.setVisible(false);
        hisContent.setVisible(false);
        expensesPane.setVisible(false);
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
                payView.addSubjectBox(selecedSubjects);
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
            closeRightSide();
        } else if (event.getSource() == paymentBtn) {
            setVisibilityContent();
            payView = new paymentView(fio, monthlyBill, subjectBox, cashRBtn, cardRBtn, cardHolderTxt, cardHolderPane, omment, monthBox, teacherBox, popupBkg, subjectChoicePane, choiceLb, spinner, subChoiceHbox);
            paymentContent.setVisible(true);
            teacherBox.setVisible(true);
            monthBox.setVisible(true);
            subjectBox.setVisible(true);
            closeRightSide();
        } else if (event.getSource() == historyBtn) {
            setVisibilityContent();
            hisContent.setVisible(true);
            selecedMonths.clear();
            selectedTeacherForPay.clear();
            initSearchAutoCompliation();
        } else if (event.getSource() == expencesBtn) {
            setVisibilityContent();
            expensesPane.setVisible(true);
            if (activeUser.getId() == 9) {
                expensesStatsPane.setVisible(true);
            }
            filterExpBy();
            closeRightSide();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCtrl = new NavButtons(teacherBtn, paymentBtn, historyBtn, expencesBtn, teacherIcon, paymentIcon, hisIcon, expensesIcon);
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
        payView = new paymentView(fio, monthlyBill, subjectBox, cashRBtn, cardRBtn, cardHolderTxt, cardHolderPane, omment, monthBox, teacherBox, popupBkg, subjectChoicePane,choiceLb,spinner, subChoiceHbox);
        auth = new authUserView(popupBkg, authPane, topBarAccountImg, topBarUserName, authPassfield);
        expenseAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    expenseAmount.setText(oldValue);
                }
            }
        });
        initTable();
        initDateFilter();
        initCmbx();
    }

    @FXML
    void clickTeachersHandler(ActionEvent event) {
        if (event.getSource() == openRightSideBar) {
           if (!rightSideBar.isVisible()) {
               Main.primaryStage.setWidth(1200);
               mainPage.setPrefWidth(1200);
               topBar.setPrefWidth(955);
               winTools.setLayoutX(830);
               topBar.setStyle("-fx-background-image: url(top-bkg-extended.png)");
               rightSideBar.setVisible(true);
           } else {
               closeRightSide();
           }
        } else if (event.getSource() == generateXLS) {
            try {
                if (statTableView.getItems().size() > 0) {
                    initSavingStats();
                } else {
                    windowCtrl.makeToast("Ma'lumot mavjud emas");
                }
            } catch (NullPointerException e) {
                windowCtrl.makeToast("Ma'lumot mavjud emas");
            }
        }
    }

    private void closeRightSide() {
        Main.primaryStage.setWidth(900);
        mainPage.setPrefWidth(900);
        topBar.setPrefWidth(665);
        winTools.setLayoutX(540);
        topBar.setStyle("-fx-background-image: url(top-bkg.png)");
        rightSideBar.setVisible(false);
    }

    private List<tableClass> convertToTable(List<ChecksDataEntity> lsCheck) {
        int order = 1;
        List<tableClass> ls = new ArrayList<>();
        for (ChecksDataEntity o : lsCheck) {
            ls.add(new tableClass(order++, o));
        }
        return ls;
    }

    private void setTableValues(List<tableClass> ls) {
        try {
            statTableView.getItems().clear();
            statTableView.setItems(FXCollections.observableArrayList(ls));
        } catch (NullPointerException e) {
            statTableView.setItems(FXCollections.observableArrayList(ls));
        }
    }

    private List<expTableClass> convertToExpTable(List<ExpensesEntity> lsExpeneses) {
        int order = 1;
        List<expTableClass> ls = new ArrayList<>();
        for (ExpensesEntity o : lsExpeneses) {
            ls.add(new expTableClass(order++, o));
        }
        return ls;
    }

    private void setExpTableValues(List<expTableClass> ls) {
        try {
            expensesTableView.getItems().clear();
            expensesTableView.setItems(FXCollections.observableArrayList(ls));
        } catch (NullPointerException e) {
            expensesTableView.setItems(FXCollections.observableArrayList(ls));
        }
    }

    private void initCmbx() {
        List<String> subList = new ArrayList<>();
        subList.add("Barchasi");
        subList.addAll(subjectDAOimpls.getInstance().getNames());
        List<String> teachList = new ArrayList<>();
        teachList.add("Barchasi");
        teachList.addAll(teacherDAOImpls.getInstance().getNames());
        subjectFilter.setItems(FXCollections.observableArrayList(subList));
        subjectFilter.getSelectionModel().select(0);
        monthFilter.setItems(FXCollections.observableArrayList(dateUtils.getMonthForSpinner()));
        monthFilter.getSelectionModel().select(0);
        teacherFilter.setItems(FXCollections.observableArrayList(teachList));
        teacherFilter.getSelectionModel().select(0);
        subjectFilter.valueProperty().addListener(newValue ->  {
            filterBy();
        });
        monthFilter.valueProperty().addListener(newValue -> {
            filterBy();
        });
        teacherFilter.valueProperty().addListener(newValue -> {
            filterBy();
        });
        cashFilter.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filterBy();
        });
        cardFilter.selectedProperty().addListener((observable, oldValue, newValue) ->  {
            filterBy();
        });
        wholeMonthRBtn.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filterBy();
        });
    }

    private void filterBy() {
        Date date = getSelectedDate();
        String month = monthFilter.getSelectionModel().getSelectedItem();
        String teacher = teacherFilter.getSelectionModel().getSelectedItem();
        String subject = subjectFilter.getSelectionModel().getSelectedItem();
        boolean cash = cashFilter.selectedProperty().getValue();
        boolean card = cardFilter.selectedProperty().getValue();
        boolean byMonth = wholeMonthRBtn.selectedProperty().getValue();
        setTableValues(convertToTable(checksDataDAOimpls.getInstance().getListBy(month, subject, teacher, date, cash, card, byMonth)));
        calculateTable();
    }

    private void filterExpBy() {
        Date date = getExpSelectedDate();
        boolean bMonth = expWholeMonth.selectedProperty().getValue();
        setExpTableValues(convertToExpTable(expenesDAOImpls.getInstance().getByList(date, bMonth)));
        calculateExpTable();
    }

    private void calculateTable() {
        double summ = 0.0;
        for (tableClass o : statTableView.getItems()) {
            summ += o.getAmount();
        }
        DecimalFormat df = new DecimalFormat("#,###");
        df.setMaximumFractionDigits(0);
        sumUpLb.setText("Umumiy to'lov: " + df.format(summ));
    }

    private void calculateExpTable() {
        double summ = 0.0;
        for (expTableClass o : expensesTableView.getItems()) {
            summ += o.getAmount();
        }
        DecimalFormat df = new DecimalFormat("#,###");
        df.setMaximumFractionDigits(0);
        expSummUp.setText("Umumiy xarajat: " + df.format(summ));
    }

    private void initTable(){
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        subject.setCellValueFactory(new PropertyValueFactory<>("sub"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        initTableWrapText(teacher);
        initTableWrapText(name);
        initTableWrapText(subject);
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        expNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        expAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        expBy.setCellValueFactory(new PropertyValueFactory<>("by"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        expComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        initExpTableWrapText(expBy);
        initExpTableWrapText(expComment);

    }

    private void initTableWrapText(TableColumn<tableClass, String> clm) {
        clm.setCellFactory(tc -> {
            TableCell<tableClass, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(clm.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }

    private void initExpTableWrapText(TableColumn<expTableClass, String> clm) {
        clm.setCellFactory(tc -> {
            TableCell<expTableClass, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(clm.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }

    private void initDateFilter() {
        int crYear = new java.util.Date().getYear();
        int crDy = new java.util.Date().getDate();
        SpinnerValueFactory<Integer> ySp = new SpinnerValueFactory.IntegerSpinnerValueFactory(2021, 1900 + crYear,crYear);
        yearSpinner.setValueFactory(ySp);
        expYearSpinner.setValueFactory(ySp);
        monthCmbx.setItems(FXCollections.observableArrayList(dateUtils.getMonths()));
        monthCmbx.getSelectionModel().select(dateUtils.getCurrentMonth());
        expMonthCmbx.setItems(FXCollections.observableArrayList(dateUtils.getMonths()));
        expMonthCmbx.getSelectionModel().select(dateUtils.getCurrentMonth());
        int lengthMonth = dateUtils.getLastDayMonth();
        SpinnerValueFactory<Integer> dSp = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, lengthMonth,crDy);
        dateSpinner.setValueFactory(dSp);
        expDaySpinner.setValueFactory(dSp);
        yearSpinner.valueProperty().addListener(e ->  {
            filterBy();
        });
        monthCmbx.valueProperty().addListener(newValue ->  {
            filterBy();
        });
        dateSpinner.valueProperty().addListener(newValue ->  {
            filterBy();
        });
        expYearSpinner.valueProperty().addListener(e -> {
            filterExpBy();
        });
        expDaySpinner.valueProperty().addListener(e -> {
            filterExpBy();
        });
        expMonthCmbx.valueProperty().addListener(e -> {
            filterExpBy();
        });
        expWholeMonth.selectedProperty().addListener(e -> {
            filterExpBy();
        });
    }

    private Date getSelectedDate() {
        String year  = String.valueOf(yearSpinner.getValue());
        String month  = monthCmbx.getSelectionModel().getSelectedItem();
        String day = String.valueOf(dateSpinner.getValue());
        return  Date.valueOf(year + "-" + dateUtils.getMonthOrder(month) + "-" + day);
    }

    private Date getExpSelectedDate() {
        String year  = String.valueOf(expYearSpinner.getValue());
        String month  = expMonthCmbx.getSelectionModel().getSelectedItem();
        String day = String.valueOf(expDaySpinner.getValue());
        return  Date.valueOf(year + "-" + dateUtils.getMonthOrder(month) + "-" + day);
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
            editedSubject.setText("");
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

    @FXML
    void exitOnESC(KeyEvent event) {
        if (event.getSource() == subjectChoicePane) {
            if (event.getCode() == KeyCode.ESCAPE) {
                subjectChoicePane.setVisible(false);
                if (teacherContent.isVisible()) {
                    editTeacherNode.setVisible(true);
                } else  {
                    popupBkg.setVisible(false);
                    payView.addTeacherBox(selectedTeacherForPay);
                    payView.addMonthBox(selecedMonths);
                    payView.addSubjectBox(selecedSubjects);
                }
            }
        } else if (event.getSource() == editTeacherNode) {
            if (event.getCode() == KeyCode.ESCAPE) {
                popupBkg.setVisible(false);
                editTeacherNode.setVisible(false);
                TeacherImg.setFill(Paint.valueOf("#e5e5e5"));
                imgUrl = null;
                selectedSubjectsTeachers.clear();
                addTeacher.setText("Qo'shish");
                editedTeacherName.setText("");
                teachNode.initTeacherNode(teacherDAOImpls.getInstance().getAll());
            }
        } else if (event.getSource() == editUserNode) {
            if (event.getCode() == KeyCode.ESCAPE) {
                editUserNode.setVisible(false);
                popupBkg.setVisible(false);
                cashNode.initCashersNode(cashersDAOImpls.getInstance().getAll());
            }
        } else if (event.getSource() == editSubjectNode) {
            if (event.getCode() == KeyCode.ESCAPE) {
                editedSubject.setText("");
                popupBkg.setVisible(false);
                editSubjectNode.setVisible(false);
                subNode.initSubjectNode(subjectDAOimpls.getInstance().getAll());
            }
        }
    }

    @FXML
    void expensesClickHandler(ActionEvent event) {
        if (event.getSource() == saveExpenses) {
            try {
                if (!expensesComment.getText().equals("") && !expenseAmount.getText().equals("")) {
                    ExpensesEntity obj = new ExpensesEntity();
                    obj.setAmount(Long.parseLong(expenseAmount.getText()));
                    obj.setDateCreated(new Date(new java.util.Date().getTime()));
                    obj.setUser(activeUser.getName());
                    obj.setComment(expensesComment.getText());
                    expenesDAOImpls.getInstance().add(obj);
                    expenseAmount.setText("");
                    expensesComment.setText("");
                    windowCtrl.makeToast("Ma'lumot saqlandi");
                    filterExpBy();
                } else {
                    windowCtrl.makeToast("Ma'lumot to'liq emas");
                }
            } catch (NullPointerException e) {
                windowCtrl.makeToast("Ma'lumot to'liq emas");
            }
        } else if (event.getSource() == generateExpXls) {
            try {
                if (expensesTableView.getItems().size() > 0) {
                    initExpSavingStats();
                } else {
                    windowCtrl.makeToast("Ma'lumot mavjud emas");
                }
            } catch (NullPointerException e) {
                windowCtrl.makeToast("Ma'lumot mavjud emas");
            }
        }
    }
}
