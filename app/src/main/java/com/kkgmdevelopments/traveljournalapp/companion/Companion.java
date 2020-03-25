package com.kkgmdevelopments.traveljournalapp.companion;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/**
 * Companion Model (Not yet properly implemented, [further prototype development])
 *  This represents a single companion, which can be in multiple holidays
 */
public class Companion {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    /**
     * Empty Constructor
     */
    public Companion(){

    }

    /**
     * Constructor
     * @param name Name of Companion
     */
    public Companion(String name){
        this.name = name;
    }

    // Encapsulation Methods //
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
