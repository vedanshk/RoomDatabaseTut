package com.example.roomdatabasetut.db.entity;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

}
