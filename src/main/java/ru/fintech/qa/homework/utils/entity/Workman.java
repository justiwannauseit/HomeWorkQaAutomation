package ru.fintech.qa.homework.utils.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "public.workman")
public class Workman extends BaseEntity {

    @Column (name = "\"name\"")
    private String name;

    @Column (name = "age")
    private int age;

    @Column (name = "\"position\"")
    private int position;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final int getAge() {
        return age;
    }

    public final void setAge(final int age) {
        this.age = age;
    }

    public final int getPosition() {
        return position;
    }

    public final void setPosition(final int position) {
        this.position = position;
    }
}
