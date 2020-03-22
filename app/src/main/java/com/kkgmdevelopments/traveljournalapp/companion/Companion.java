package com.kkgmdevelopments.traveljournalapp.companion;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Companion {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "")
    private String name;

    public Companion(){

    }

    public Companion(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
