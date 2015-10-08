/*
 * File Summary: Activity for registration of user
 * Date: 25 Sep 2015
 * Author: Makarand Kate (974125)
 * Version: 1.2
 * -------------------------------------------------------------------------------
 * Change Log
 * -------------------------------------------------------------------------------
 * Changes: Removed location field
 * Date: 7 Oct 2015
 * Editor: Makarand Kate(974125)
 * Version: 1.1
 * -------------------------------------------------------------------------------
 */

package com.tcs.tcsldi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	DBHelper db=new DBHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		int uid=-1;
		Cursor cur=db.getLogin();
		if(cur.moveToFirst()){
			uid=cur.getInt(cur.getColumnIndex("uid"));
		}
		if(uid>0){
			Intent intn=new Intent(LoginActivity.this,MainActivity.class);
			intn.putExtra("uid", uid);
			startActivity(intn);
			finish();
		}
		
		final TextView error=(TextView)findViewById(R.id.login_error);
		error.setVisibility(View.GONE);
		
		final EditText registerName=(EditText)findViewById(R.id.register_name);
		final EditText registerEid=(EditText)findViewById(R.id.register_eid);
		final EditText registerEmail=(EditText)findViewById(R.id.register_email);
		
		
		Button btn=(Button)findViewById(R.id.login_btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=registerName.getText().toString();
				String eid=registerEid.getText().toString();
				String email=registerEmail.getText().toString();
				TextView tv=(TextView)findViewById(R.id.login_error);
				tv.setVisibility(View.VISIBLE);
				
				if(name.equals("") || eid.equals("") || email.equals("")){
					tv.setText("You missed some fields.");
				}else{
					if(email.indexOf("@tcs.com")==email.length()-8 && email.indexOf("@tcs.com")>2){
						tv.setVisibility(View.GONE);
						
						if(isNetworkAvailable()){
							new RequestTask().execute(getResources().getString(R.string.server)+"services/register.php?name="+name+"&eid="+eid+"&email="+email+"&location=tvm");
						}else{
							Toast.makeText(LoginActivity.this, "no network available", Toast.LENGTH_LONG).show();
						}
					}else{
						tv.setText("Incorrect email format.");	
					}
				}
				
			}
		});

		
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

		ProgressDialog dialog=new ProgressDialog(LoginActivity.this);
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			this.dialog.setMessage("Please Wait");
			this.dialog.show();
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			//do anything with response
			
			if(result.length()>0){
				if(dialog.isShowing()){
					dialog.dismiss();
				}
				db.updateLogin(Integer.parseInt(result));
				showNotification(LoginActivity.this,4,"Rgistration successfull.","You are successfully registered");
				Intent intn=new Intent(LoginActivity.this,LoginActivity.class);
				startActivity(intn);
				finish();
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
