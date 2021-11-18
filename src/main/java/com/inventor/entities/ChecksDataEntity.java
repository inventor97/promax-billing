package com.inventor.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "checks_data", schema = "promax_billing", catalog = "")
public class ChecksDataEntity {
    private int id;
    private String name;
    private int casherId;
    private String teachers;
    private int subjectId;
    private String comment;
    private Date dateCrated;
    private String payedMonth;
    private List<TeachersEntity> teachersList;

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
    @Column(name = "subject_id")
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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
        if (o == null || getClass() != o.getClass()) return false;
        ChecksDataEntity that = (ChecksDataEntity) o;
        return id == that.id && casherId == that.casherId && subjectId == that.subjectId && Objects.equals(teachers, that.teachers) && Objects.equals(comment, that.comment) && Objects.equals(dateCrated, that.dateCrated) && Objects.equals(payedMonth, that.payedMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, casherId, teachers, subjectId, comment, dateCrated, payedMonth);
    }
}
