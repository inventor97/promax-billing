package com.inventor.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class expTableClass {
    private int no;
    private long amount;
    private String by;
    private String comment;
    private String date;

    public expTableClass(int order, ExpensesEntity obj) {
        this.no = order;
        this.amount = obj.getAmount();
        this.by = obj.getUser();
        this.date = String.valueOf(obj.getDateCreated());
        this.comment = obj.getComment();
    }
}
