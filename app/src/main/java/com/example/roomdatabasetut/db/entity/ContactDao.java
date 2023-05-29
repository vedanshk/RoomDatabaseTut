package com.example.roomdatabasetut.db.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
public interface ContactDao  {

    @Insert
    public long addContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);

    @Query("SELECT  * from contacts")
    ArrayList<Contact> getAllContact();

    @Query("SELECT * from contacts where contact_id= :id")
    Contact getContact(long id);


}
