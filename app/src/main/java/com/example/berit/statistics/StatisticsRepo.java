package com.example.berit.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StatisticsRepo extends Repo<Statistics> {

    public StatisticsRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public ContentValues entityToContentValues(Statistics statistics) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.STATISTICS_DAY_STAMP, statistics.getDayStamp());
        values.put(MySQLiteHelper.STATISTICS_OPERANDID, statistics.getOperandId());
        values.put(MySQLiteHelper.STATISTICS_DAY_COUNTER, statistics.getDayCounter());
        return values;
    }

    @Override
    public Statistics cursorToEntity(Cursor cursor) {
        Statistics statistics = new Statistics();
        statistics.setId((cursor.getLong(0)));
        statistics.setDayStamp(cursor.getInt(1));
        statistics.setOperandId(cursor.getLong(2));
        statistics.setDayCounter(cursor.getInt(3));
        return statistics;
    }

    public Statistics getOperationInDay(int dayStamp, long operation) {
        Cursor cursor = getDatabase().query(getTablename(),
                getAllColumns(), "operandId =" + operation + " and dayStamp =" + dayStamp, null,
                null, null, null);
        Statistics statistics = null;
        if(cursor.moveToFirst()){
            statistics = cursorToEntity(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return statistics;
    }

    public List<Statistics> getByOperandId(long operandId){
        List<Statistics> listOfEntity = new ArrayList<Statistics>();

        Cursor cursor = getDatabase().query(getTablename(),
                getAllColumns(), "operandId = " + operandId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Statistics entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }
}