/*
 * File Summary: Alarm service for every 5 min
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	DBHelper db;
	Context mContext;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		db=new DBHelper(context);
		mContext=context;
		Calendar c=Calendar.getInstance();
		int yy=c.get(Calendar.YEAR);
		int mm=c.get(Calendar.MONTH)+1;
		int dd=c.get(Calendar.DATE);
		int hh=c.get(Calendar.HOUR_OF_DAY);
		int mn=c.get(Calendar.MINUTE);
		int nw=(hh*60)+mn;
		
		Cursor cur=db.getAllTaskByDate(yy, mm, dd);
		if(cur.moveToFirst()){
			do{
				int tstime=cur.getInt(cur.getColumnIndex("stime"));
				int tstimem=cur.getInt(cur.getColumnIndex("stimem"));
				int tt=(tstime*60)+tstimem;
				if((tt-nw)<=5 && (tt-nw)>0){
					String title=cur.getString(cur.getColumnIndex("tname"));
					showNotification(context,1,title,"Starts at "+tstime+":"+tstimem);
				}
				
			}while(cur.moveToNext());
		}
		cur.close();
		if(mm==0){
			Cursor lcur=db.getLogin();
			int id=0;
			if(lcur.moveToFirst()){
				id=lcur.getInt(lcur.getColumnIndex("uid"));
			}
			lcur.close();
			Cursor mcur=db.getTaskByPendingSync();
			
			if(mcur.moveToFirst()){
				do{
					int tid=mcur.getInt(mcur.getColumnIndex("tid"));
					int rating=mcur.getInt(mcur.getColumnIndex("rating"));
					String feedback=mcur.getString(mcur.getColumnIndex("feedback"));
					if(isNetworkAvailable() && id!=0){
						new RequestTask().execute(context.getResources().getString(R.string.server)+"services/feedbackcollector.php?"
								+ "id="+id+"&tid="+tid+"&rating="+rating+"&feedback="+feedback);
					}
				}while(mcur.moveToNext());
			}
			mcur.close();
		}
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
				try {
					JSONObject obj=new JSONObject(result);
					int rid=obj.getInt("tid");
					db.updateSync(rid);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isNetworkAvailable(){
		ConnectivityManager connectivity=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo active=connectivity.getActiveNetworkInfo();
		return active != null && active.isConnectedOrConnecting();
	}


}
