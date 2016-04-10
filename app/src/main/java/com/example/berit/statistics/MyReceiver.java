package com.example.berit.statistics;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class MyReceiver extends BroadcastReceiver {
    public static String TAG = "MyReceiver";

    private CalcEngine calcEngine = new CalcEngine();
    private UOW uow;
    @Override
    //Using CalcEngine if broadcast is ordered and returning calculation result
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received system broadcast");
        UOW uow = new UOW(context);
        Double calc = 0D;
        if (isOrderedBroadcast()) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                double nr1 = extras.getDouble("n1");
                double nr2 = extras.getDouble("n2");
                String operation = extras.getString("op");
                calc = calcEngine.calculate(nr1, nr2, operation);

                OperationType optype = uow.operationTypeRepo.getOperationType(operation);
                optype.setLifetimeCounter(optype.getLifetimeCounter()+1);
                uow.operationTypeRepo.update(optype);

                int time = (int) (System.currentTimeMillis());
                uow.operationRepo.add(new Operation(optype.getId(),nr1,nr2,calc,time));

                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DATE);
                int year = cal.get(Calendar.YEAR);
                int dayStamp = day*1000000 + month*10000 + year;
                Statistics stat = uow.statisticsRepo.getOperationInDay(dayStamp, optype.getId());
                if(stat == null){
                    stat = new Statistics(dayStamp, optype.getId(), 1);
                    uow.statisticsRepo.add(stat);
                } else {
                    stat.setDayCounter(stat.getDayCounter()+1);
                    uow.statisticsRepo.update(stat);
                }
                Log.d(TAG, stat.getDayCounter() + "counter");
                Log.d(TAG, optype.getLifetimeCounter() + "lifetime");
            }
            setResultCode(Activity.RESULT_OK);
            setResultData(calc == null ? "Nulliga ei saa jagada" : String.valueOf(calc));
        }
    }
}