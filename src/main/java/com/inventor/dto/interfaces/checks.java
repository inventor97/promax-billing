package com.inventor.dto.interfaces;

import com.inventor.entities.ChecksDataEntity;

import java.sql.Date;
import java.util.List;

public interface checks extends commonDAO<ChecksDataEntity>{

    List<ChecksDataEntity> getListByDate(Date date);

    List<ChecksDataEntity> getListBy(String month, String sub, String teach, Date date, boolean cash, boolean card, boolean byMonth);

    List<ChecksDataEntity> getByName(String name);
}
