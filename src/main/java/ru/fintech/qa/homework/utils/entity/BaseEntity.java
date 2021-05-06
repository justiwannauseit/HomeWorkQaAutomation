package ru.fintech.qa.homework.utils.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

    @Id
    private int id;

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }
}
