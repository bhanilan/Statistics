package com.example.berit.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OperationRepo extends Repo<Operation> {

    public OperationRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public ContentValues entityToContentValues(Operation operation) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.OPERATIONS_OPERANDID, operation.getOperandId());
        values.put(MySQLiteHelper.OPERATIONS_NUM_1, operation.getNum1());
        values.put(MySQLiteHelper.OPERATIONS_NUM_2, operation.getNum2());
        values.put(MySQLiteHelper.OPERATIONS_RES, operation.getRes());
        values.put(MySQLiteHelper.OPERATIONS_TIME_STAMP, operation.getTimeStamp());
        return values;
    }

    @Override
    public Operation cursorToEntity(Cursor cursor) {
        Operation operation = new Operation();
        operation.setId((cursor.getLong(0)));
        operation.setOperandId(cursor.getLong(1));
        operation.setNum1(cursor.getDouble(2));
        operation.setNum2(cursor.getDouble(3));
        operation.setRes(cursor.getDouble(4));
        operation.setTimeStamp(cursor.getInt(5));
        return operation;
    }
}