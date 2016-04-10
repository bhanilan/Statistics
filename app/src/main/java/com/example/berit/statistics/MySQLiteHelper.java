package com.example.berit.statistics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = MySQLiteHelper.class.getName();
    private final Context context;

    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;

    //Table operation types
    public static final String TABLE_TYPES = "types";
    public static final String TYPES_ID = "_id";
    public static final String TYPES_OPERAND = "operand";
    public static final String TYPES_COUNTER = "lifetimeCounter";
    public static final String[] TYPES_ALLCOLUMNS = {TYPES_ID, TYPES_OPERAND, TYPES_COUNTER};
    //Table statistics
    public static final String TABLE_STATISTICS = "statistics";
    public static final String STATISTICS_ID = "_id";
    public static final String STATISTICS_DAY_STAMP = "dayStamp";
    public static final String STATISTICS_OPERANDID = "operandId";
    public static final String STATISTICS_DAY_COUNTER = "dayCounter";
    public static final String[] STATISTICS_ALLCOLUMNS = {STATISTICS_ID, STATISTICS_DAY_STAMP,
            STATISTICS_OPERANDID, STATISTICS_DAY_COUNTER};

    //Table operations
    public static final String TABLE_OPERATIONS = "operations";
    public static final String OPERATIONS_ID = "_id";
    public static final String OPERATIONS_OPERANDID = "operandId";
    public static final String OPERATIONS_NUM_1 = "num1";
    public static final String OPERATIONS_NUM_2 = "num2";
    public static final String OPERATIONS_RES = "res";
    public static final String OPERATIONS_TIME_STAMP = "timeStamp";
    public static final String[] OPERATIONS_ALLCOLUMNS = {OPERATIONS_ID, OPERATIONS_OPERANDID,
            OPERATIONS_NUM_1, OPERATIONS_NUM_2, OPERATIONS_RES, OPERATIONS_TIME_STAMP};

    //Database creation sql statement
    private static final String DATABASE_CREATE_TYPES = "create table "
            + TABLE_TYPES + "(" + TYPES_ID
            + " integer primary key autoincrement, " + TYPES_OPERAND
            + " text not null, " + TYPES_COUNTER + " integer);";

    //Database creation sql statement
    private static final String DATABASE_CREATE_STATISTICS = "create table "
            + TABLE_STATISTICS + "(" + STATISTICS_ID
            + " integer primary key autoincrement, " + STATISTICS_DAY_STAMP
            + " integer, " + STATISTICS_OPERANDID + " integer, " + STATISTICS_DAY_COUNTER
            + " integer);";

    //Database creation sql statement
    private static final String DATABASE_CREATE_OPERATIONS = "create table "
            + TABLE_OPERATIONS + "(" + OPERATIONS_ID
            + " integer primary key autoincrement, " + OPERATIONS_OPERANDID
            + " integer, " + OPERATIONS_NUM_1 + " double, " + OPERATIONS_NUM_2
            + " double, " + OPERATIONS_RES + " double, " + OPERATIONS_TIME_STAMP
            + " integer);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_TYPES);
        db.execSQL(DATABASE_CREATE_STATISTICS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        //On upgrade drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);

        //Create new tables
        onCreate(db);
    }

    public void dropCreateDatabase(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);

        db.execSQL(DATABASE_CREATE_TYPES);
        db.execSQL(DATABASE_CREATE_STATISTICS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
    }
}