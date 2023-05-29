package com.example.roomdatabasetut.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {


    @ColumnInfo(name = "contact_id" ) @PrimaryKey(autoGenerate = true)
    private  int id;
    @ColumnInfo(name = "contact_email")
    private String email;

    @ColumnInfo(name = "contact_name")
    private String name;

    //constructor

    @Ignore
    public Contact() {

    }


    public Contact(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
