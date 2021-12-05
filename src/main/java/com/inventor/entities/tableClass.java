package com.inventor.entities;

import com.inventor.dao.impls.subjectDAOimpls;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
public class tableClass {

    private int no;
    private String name;
    private String sub;
    private String teacher;
    private String month;
    private long amount;
    private ChecksDataEntity obj;
    private ExpensesEntity expObj;

    public tableClass(int order, ChecksDataEntity obj) {
        this.obj = obj;
        this.no = order;
        this.name = obj.getName();
        this.month = obj.getPayedMonth();
        this.sub = obj.getSubjects();
        this.teacher = obj.getTeachers();
        this.amount = obj.getAmountBill();
    }

    public tableClass(int order, ExpensesEntity obj) {
        this.expObj = obj;
        this.no = order;
        this.amount = (long) obj.getAmount();
        this.month = obj.getUser();
        this.sub = String.valueOf(obj.getDateCreated());
        this.teacher = obj.getComment();
    }

}
