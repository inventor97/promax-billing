package com.inventor.entities;

import com.inventor.dto.impls.subjectDAOimpls;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teachers", schema = "promax_billing")
public class TeachersEntity {
    private int id;
    private String name;
    private String subjectId;
    private List<SubjectsEntity> subjects = new ArrayList<>();
    private byte[] img;

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String subjects2String() {
        int size = subjectId.split(",").length;
        String[] ids = subjectId.split(",");
        for (int i = 0; i < size; i++) {
            int id = Integer.parseInt(ids[i]);
            subjects.add(subjectDAOimpls.getInstance().get(id));
        }
        subjects.removeIf(Objects::isNull);
        final String[] txt = {""};
        subjects.forEach(e -> txt[0] +=e.getName() + ", ");
        return txt[0];
    }

    public void setSubjects(List<SubjectsEntity> subjects) {
        subjectId = "";
        for (SubjectsEntity o : subjects) {
            subjectId += o.getId() +",";
        }
        this.subjects = subjects;
    }

    public void getSubjects(List<SubjectsEntity> ls) {
        if (subjectId != null) {
            int size = subjectId.split(",").length;
            String[] ids = subjectId.split(",");
            for (int i = 0; i < size; i++) {
                int id = Integer.parseInt(ids[i]);
                ls.add(subjectDAOimpls.getInstance().get(id));
            }
            ls.removeIf(Objects::isNull);
        }
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
    @Column(name = "subject_id")
    public String getSubjectId() {
        return subjectId;
    }

    @Basic
    @Column(name="img")
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachersEntity that = (TeachersEntity) o;
        return id == that.id && subjectId.equals(that.subjectId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subjectId);
    }
}
