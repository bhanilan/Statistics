package com.example.berit.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OperationTypeRepo extends Repo<OperationType> {
    public OperationTypeRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public ContentValues entityToContentValues(OperationType operationType) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.TYPES_OPERAND, operationType.getOperand());
        values.put(MySQLiteHelper.TYPES_COUNTER, operationType.getLifetimeCounter());
        return values;
    }

    @Override
    public OperationType cursorToEntity(Cursor cursor) {
        OperationType operationType = new OperationType();
        operationType.setId((cursor.getLong(0)));
        operationType.setOperand(cursor.getString(1));
        operationType.setLifetimeCounter(cursor.getInt(2));
        return operationType;
    }

    public OperationType getOperationType(String type){
        Cursor cursor = getDatabase().query(getTablename(),
                getAllColumns(), "operand = '" + type+ "'", null, null, null, null);
        OperationType optype = null;
        if(cursor.moveToFirst()){
            optype = cursorToEntity(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return optype;
    }
}