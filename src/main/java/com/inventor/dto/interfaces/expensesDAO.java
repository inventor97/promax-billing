package com.inventor.dto.interfaces;

import com.inventor.entities.ExpensesEntity;

import java.sql.Date;
import java.util.List;

public interface expensesDAO extends commonDAO<ExpensesEntity> {

    List<ExpensesEntity> getByList(Date date,boolean byMonth);

}
