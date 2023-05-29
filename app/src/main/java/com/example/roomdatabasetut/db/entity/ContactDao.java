package com.example.roomdatabasetut.db.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao  {

    @Insert
    public void addContact(Contact contact);

    @Update
    public void updateContact(Contact contact);

    @Delete
    public void deleteContact(Contact contact);


    @Query("SELECT * from contacts where contact_id= :id;")
    Contact getContact(long id);


    @Query("Select * from contacts order by contact_id")
    List<Contact> getAllContacts();
}
