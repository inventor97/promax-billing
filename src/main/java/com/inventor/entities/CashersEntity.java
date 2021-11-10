package com.inventor.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cashers", schema = "promax_billing", catalog = "")
public class CashersEntity {
    private int id;
    private String name;
    private int password;

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
    @Column(name = "password")
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashersEntity that = (CashersEntity) o;
        return id == that.id && password == that.password && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
