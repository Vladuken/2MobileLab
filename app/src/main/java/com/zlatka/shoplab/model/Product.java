package com.zlatka.shoplab.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public double price;

    @ColumnInfo
    public int amount;

    @ColumnInfo
    public String photoPath;

    @ColumnInfo
    public String description;
}
