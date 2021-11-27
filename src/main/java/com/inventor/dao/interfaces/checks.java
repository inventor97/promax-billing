package com.inventor.dao.interfaces;

import com.inventor.entities.ChecksDataEntity;

import java.sql.Date;
import java.util.List;

public interface checks extends commonDAO<ChecksDataEntity>{

    List<ChecksDataEntity> getListByDate(Date date);
    List<ChecksDataEntity> getListBySubject(String sub);
    List<ChecksDataEntity> getListByTeacher(String teacher);
    List<ChecksDataEntity> getListByMonth(String month);

}
