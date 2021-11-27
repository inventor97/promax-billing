package com.inventor.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@NoArgsConstructor
@Entity
@Table(name = "checks_data", schema = "promax_billing", catalog = "")
public class ChecksDataEntity {
    private int id;
    private String name;
    private long amountBill;
    private boolean paymentType;
    private int casherId;
    private String teachers;
    private String subjectIds;
    private String comment;
    private Date dateCrated;
    private String payedMonth;

    public ChecksDataEntity(String name, long amountBill, boolean paymentType, int casherId, String teachers, String subjectIds, String comment, Date dateCrated, String payedMonth) {
        this.name = name;
        this.amountBill = amountBill;
        this.paymentType = paymentType;
        this.casherId = casherId;
        this.teachers = teachers;
        this.subjectIds = subjectIds;
        this.comment = comment;
        this.dateCrated = dateCrated;
        this.payedMonth = payedMonth;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "amount_bill")
    public long getAmountBill() {
        return amountBill;
    }

    public void setAmountBill(long amountBill) {
        this.amountBill = amountBill;
    }

    @Basic
    @Column(name = "payment_type")
    public boolean isPaymentType() {
        return paymentType;
    }

    public void setPaymentType(boolean paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "casher_id")
    public int getCasherId() {
        return casherId;
    }

    public void setCasherId(int casherId) {
        this.casherId = casherId;
    }

    @Basic
    @Column(name = "teachers")
    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    @Basic
    @Column(name = "subject_ids")
    public String getSubjectId() {
        return subjectIds;
    }

    public void setSubjectId(String subjectId) {
        this.subjectIds = subjectId;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "date_crated")
    public Date getDateCrated() {
        return dateCrated;
    }

    public void setDateCrated(Date dateCrated) {
        this.dateCrated = dateCrated;
    }

    @Basic
    @Column(name = "payed_month")
    public String getPayedMonth() {
        return payedMonth;
    }

    public void setPayedMonth(String payedMonth) {
        this.payedMonth = payedMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChecksDataEntity)) return false;
        ChecksDataEntity that = (ChecksDataEntity) o;
        return id == that.id && amountBill == that.amountBill && paymentType == that.paymentType && casherId == that.casherId && name.equals(that.name) && teachers.equals(that.teachers) && subjectIds.equals(that.subjectIds) && comment.equals(that.comment) && dateCrated.equals(that.dateCrated) && payedMonth.equals(that.payedMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amountBill, paymentType, casherId, teachers, subjectIds, comment, dateCrated, payedMonth);
    }
}
