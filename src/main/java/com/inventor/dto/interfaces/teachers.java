package com.inventor.dto.interfaces;

import com.inventor.entities.TeachersEntity;

import java.util.List;

public interface teachers extends commonDAO<TeachersEntity>{


    List<TeachersEntity> getTeachersList();

    long getTeachersCountOnSubject(int subjectId);
}
