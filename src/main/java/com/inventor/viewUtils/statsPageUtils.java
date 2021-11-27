package com.inventor.viewUtils;

import com.inventor.dao.impls.checksDataDAOimpls;
import com.inventor.entities.ChecksDataEntity;
import com.inventor.entities.tableClass;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class statsPageUtils {

    private TableView<tableClass> table;
    private TableColumn<tableClass, Integer> no;
    private TableColumn<tableClass, String> name;
    private TableColumn<tableClass, String> subject;
    private TableColumn<tableClass, String> teacher;
    private TableColumn<tableClass, String> month;
    private TableColumn<tableClass, Long> paymentAmount;
    private ChecksDataEntity obj;
    private Label sumUpLb;
    private Spinner<Integer> yearSpinner;
    private Spinner<Integer> dateSpinner;
    private JFXComboBox<String> monthCmbx;
    private JFXComboBox<String> subFilter;
    private JFXComboBox<String> teachFilter;
    private JFXComboBox<String> monthFilter;
    private JFXRadioButton cashFilter;
    private JFXRadioButton cardFilter;
    private JFXTextField searchField;
    private JFXButton openRightSide;
    private AnchorPane rightSide;
    private JFXButton generateXls;

    public statsPageUtils(TableView<tableClass> table, TableColumn<tableClass, Integer> no, TableColumn<tableClass, String> name, TableColumn<tableClass, String> subject, TableColumn<tableClass, String> teacher, TableColumn<tableClass, String> month, TableColumn<tableClass, Long> paymentAmount, ChecksDataEntity obj, Label sumUpLb, Spinner<Integer> yearSpinner, Spinner<Integer> dateSpinner, JFXComboBox<String> monthCmbx, JFXComboBox<String> subFilter, JFXComboBox<String> teachFilter, JFXComboBox<String> monthFilter, JFXRadioButton cashFilter, JFXRadioButton cardFilter, JFXTextField searchField, JFXButton openRightSide, AnchorPane rightSide, JFXButton generateXls) {
        this.table = table;
        this.no = no;
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
        this.month = month;
        this.paymentAmount = paymentAmount;
        this.obj = obj;
        this.sumUpLb = sumUpLb;
        this.yearSpinner = yearSpinner;
        this.dateSpinner = dateSpinner;
        this.monthCmbx = monthCmbx;
        this.subFilter = subFilter;
        this.teachFilter = teachFilter;
        this.monthFilter = monthFilter;
        this.cashFilter = cashFilter;
        this.cardFilter = cardFilter;
        this.searchField = searchField;
        this.openRightSide = openRightSide;
        this.rightSide = rightSide;
        this.generateXls = generateXls;
        initTable();
        initDatePicker();
//        setInitialTableValues(getInitTableValues(Date.valueOf(datePicker.getValue())));
    }

    private List<tableClass> getInitTableValues(Date date) {
        int order = 0;
        List<tableClass> ls = new ArrayList<>();
        for (ChecksDataEntity o : checksDataDAOimpls.getInstance().getListByDate(date)) {
            ls.add(new tableClass(order++, o));
        }
        return ls;
    }

    private void setInitialTableValues(List<tableClass> ls) {
        try {
            table.getItems().clear();
            table.setItems(FXCollections.observableArrayList(ls));
        } catch (NullPointerException e) {
            table.setItems(FXCollections.observableArrayList(ls));
        }
    }

    private void initTable(){
        no.setCellValueFactory(new PropertyValueFactory<>("no"));
        name.setCellValueFactory(new PropertyValueFactory<>("name`"));
        subject.setCellValueFactory(new PropertyValueFactory<>("sub"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        paymentAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void initDateFilter() {
        int crYear = new java.util.Date().getYear();
        yearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2021, crYear, crYear));
    }

    private void initDatePicker() {

    }

}
