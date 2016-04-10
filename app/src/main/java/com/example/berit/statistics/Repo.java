package com.example.berit.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class Repo<T extends IEntity> {
    private SQLiteDatabase database;
    private String tablename;
    private String[] allColumns;

    private static String TAG = Repo.class.getName();

    //constructor gives back the database and table name
    public Repo(SQLiteDatabase database, String tablename, String[] allColumns) {
        this.database = database;
        this.tablename = tablename;
        this.allColumns = allColumns;
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    public  String getTablename(){
        return tablename;
    }

    //for getting table columns
    public String[] getAllColumns(){
        return allColumns;
    }

    public List<T> getAll(){
        List<T> listOfEntity = new ArrayList<T>();

        Cursor cursor = database.query(tablename,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            T entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        cursor.close();
        return listOfEntity;
    }

    public Cursor getCursorAll(){
        Cursor cursor = database.query(tablename,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        return cursor;
    }

    //add new entities to database
    public T add(T entity){
        ContentValues values = entityToContentValues(entity);
        //save to database
        long insertId = database.insert(tablename, null, values);
        return getById(insertId);
    }

    public void update(T entity){
        ContentValues values = entityToContentValues(entity);
        database.update(tablename, values, allColumns[0] + "=" + entity.getId(), null);
    }

    public T getById(long id){
        //in query 1st table, 2nd array of columns, 3rd where statement
        Cursor cursor = database.query(tablename,
                allColumns, allColumns[0] + " = "
                        + id, null, null, null, null);
        if (cursor == null){
            return null;
        }
        cursor.moveToFirst();
        T newEntity = cursorToEntity(cursor);
        return newEntity;
    }

    public void clear(T entity){

    }

    // this has to be implemented in child class
    public abstract ContentValues entityToContentValues(T entity);

    // this has to be implemented in child class
    public abstract T cursorToEntity(Cursor cursor);
}