package ru.fintech.qa.homework.utils.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "public.places")
public class Place extends BaseEntity {

    @Column(name = "\"row\"")
    private int row;

    @Column (name = "place_num")
    private int placeNum;

    @Column (name = "\"name\"")
    private String name;

    public final int getRow() {
        return row;
    }

    public final void setRow(final int row) {
        this.row = row;
    }

    public final int getPlaceNum() {
        return placeNum;
    }

    public final void setPlaceNum(final int placeNum) {
        this.placeNum = placeNum;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

}
