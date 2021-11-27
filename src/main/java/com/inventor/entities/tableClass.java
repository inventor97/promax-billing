package com.inventor.entities;

import com.inventor.dao.impls.subjectDAOimpls;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class tableClass {

    private int no;
    private String name;
    private String sub;
    private String teacher;
    private String month;
    private long amount;
    private ChecksDataEntity obj;

    public tableClass(int order, ChecksDataEntity obj) {
        this.obj = obj;
        this.no = order;
        this.name = obj.getName();
        this.month = obj.getPayedMonth();
        this.sub = obj.getSubjects();
        this.teacher = obj.getTeachers();
        this.amount = obj.getAmountBill();
    }

}
