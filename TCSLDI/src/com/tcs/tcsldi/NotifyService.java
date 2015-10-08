/*
 * File Summary: Background Service for notification
 * Date: 28 Sep 2015
 * Author: Makarand Kate(974125)
 * Version: 1.0
 * -------------------------------------------------------------------------------
 * Change Log
 * -------------------------------------------------------------------------------
 * Changes:
 * Date:
 * Editor:
 * -------------------------------------------------------------------------------
 */

package com.tcs.tcsldi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotifyService extends Service {
	Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();       
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) 
    {
        alarm.SetAlarm(this);
        return START_STICKY;
    }

   @Override        
   public void onStart(Intent intent, int startId)
    {
        alarm.SetAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent) 
    {
        return null;
    }
}
