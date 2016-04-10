package com.example.berit.statistics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//Unit of work class for repos and opening and closing database and tables
public class UOW {
    private MySQLiteHelper dbHelper;
    private SQLiteDatabase database;

    private final Context context;

    public OperationTypeRepo operationTypeRepo;
    public OperationRepo operationRepo;
    public StatisticsRepo statisticsRepo;

    public UOW(Context context) {
        this.context = context;
        dbHelper = new MySQLiteHelper(context);

        database = dbHelper.getWritableDatabase();

        operationTypeRepo = new OperationTypeRepo(database, dbHelper.TABLE_TYPES,
                dbHelper.TYPES_ALLCOLUMNS);
        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATIONS,
                dbHelper.OPERATIONS_ALLCOLUMNS);
        statisticsRepo = new StatisticsRepo(database, dbHelper.TABLE_STATISTICS,
                dbHelper.STATISTICS_ALLCOLUMNS);

    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData(){
        OperationType operationTypePlus = operationTypeRepo.add(new OperationType("+", 0));
        OperationType operationTypeMinus = operationTypeRepo.add(new OperationType("-", 0));
        OperationType operationTypeMulti = operationTypeRepo.add(new OperationType("×", 0));
        OperationType operationTypeDiv = operationTypeRepo.add(new OperationType("÷", 0));
    }
}