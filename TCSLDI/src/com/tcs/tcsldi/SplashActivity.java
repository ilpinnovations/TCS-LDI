/*
 * File Summary: This is splash activity and also checks for change in schedule
 * Date: 24 Sep 2015
 * Author: makarand Kate (974125)
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private PendingIntent pendingIntent;
	
	DBHelper db=new DBHelper(this);
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=this;
		
		int uid=-1;
		Cursor cur=db.getLogin();
		if(cur.moveToFirst()){
			uid=cur.getInt(cur.getColumnIndex("uid"));
		}
		if(uid>0){
			if(isNetworkAvailable()){
				new RequestTask().execute(getResources().getString(R.string.server)+"services/getschedule.php");
			}else{
				Toast.makeText(context, "no network available", Toast.LENGTH_LONG).show();
			}//finish();
		}
		
		
		setContentView(R.layout.activity_splash);
		
		Intent alarmIntent=new Intent(SplashActivity.this,AlarmReceiver.class);
		pendingIntent=PendingIntent.getBroadcast(SplashActivity.this, 0, alarmIntent, 0);
		
		
		AlarmManager manager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		int interval=1000*60*1;
		Calendar c=Calendar.getInstance();
		manager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), interval, pendingIntent);
		
		Thread load=new Thread(){
			public void run(){
				try{
					
					sleep(1500);
					Intent intn=new Intent(SplashActivity.this,LoginActivity.class);
					
					startActivity(intn);
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
					finish();
				}
			}
		};
			load.start();

	}

	private class RequestTask extends AsyncTask<String,String,String> {

		@Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
			String responseString="";
			
			if(isNetworkAvailable()){
				try{
				HttpClient httpclient=new DefaultHttpClient();
				HttpResponse response;
				
				
					response=httpclient.execute(new HttpGet(uri[0]));
					StatusLine statusLine=response.getStatusLine();
					if(statusLine.getStatusCode()==HttpStatus.SC_OK){
						ByteArrayOutputStream out=new ByteArrayOutputStream();
						response.getEntity().writeTo(out);
						responseString=out.toString();
						out.close();
					}else{
						response.getEntity().getContent().close();
						throw new IOException(statusLine.getReasonPhrase());
					}
				}catch(ClientProtocolException e){
					
				}catch(Exception e){
					
				}

			}else{
				
			}
						
			return responseString;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			//do anything with response
			if(result.length()>0){
				if(db.getCurrentSchedule().equals(result)){
					
				}else{
					db.updateSchedule(result);
					try {
						JSONObject ojb=new JSONObject(result);
						db.dropSchedule();
						JSONArray schedule=ojb.getJSONArray("schedule");
						for(int i=0;i<schedule.length();i++){
							JSONObject c=schedule.getJSONObject(i);
							int tid=c.getInt("tid");
							int typ=c.getInt("typ");
							String name=c.getString("title");
							String date=c.getString("date");
							int yy=Integer.parseInt(date.substring(0,date.indexOf('-')));
							int mm=Integer.parseInt(date.substring(date.indexOf('-')+1,date.lastIndexOf('-')));
							int dd=Integer.parseInt(date.substring(date.lastIndexOf('-')+1,date.length()));
							String start=c.getString("stime");
							int stime=Integer.parseInt(start.substring(0,start.indexOf(':')));
							int stimem=Integer.parseInt(start.substring(start.indexOf(':')+1,start.length()));
							String end=c.getString("etime");
							int etime=Integer.parseInt(end.substring(0,end.indexOf(':')));
							int etimem=Integer.parseInt(end.substring(end.indexOf(':')+1,end.length()));
							String speaker=c.getString("speaker");
							String loc=c.getString("venue");
							String desc=c.getString("desc");
							String attachment=c.getString("attachment");
							db.insertIntoSchedule(typ, tid, name, yy, mm, dd, stime, stimem, etime, etimem, loc, speaker, desc,attachment);
						}
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Toast.makeText(SplashActivity.this, "Schedule Has been Updated", Toast.LENGTH_LONG).show();
					showNotification(SplashActivity.this,4,"Schedule Change","Schedule has been updated");
					Intent intn=new Intent(SplashActivity.this,LoginActivity.class);
					startActivity(intn);
					finish();
				}
				
			}
		
		}
		
	}

	private boolean isNetworkAvailable(){
		ConnectivityManager connectivity=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo active=connectivity.getActiveNetworkInfo();
		return active != null && active.isConnectedOrConnecting();
	}
	
	private void showNotification(Context context,int id,String title,String content){
		PendingIntent contentIntent=PendingIntent.getActivity(context, 0, new Intent(context,MainActivity.class), 0);
		NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.selectedstar)
				.setContentTitle(title)
				.setContentText(content);
		mBuilder.setContentIntent(contentIntent);
		mBuilder.setDefaults(Notification.DEFAULT_SOUND);
		mBuilder.setAutoCancel(true);
		NotificationManager mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(id, mBuilder.build());
	}

}
