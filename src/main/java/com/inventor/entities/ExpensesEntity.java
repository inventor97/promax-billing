package com.inventor.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "expenses", schema = "promax_billing")
public class ExpensesEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user")
    private String user;
    @Basic
    @Column(name = "date_created")
    private Date dateCreated;
    @Basic
    @Column(name = "amount")
    private long amount;
    @Basic
    @Column(name = "comment")
    private String comment;
}
