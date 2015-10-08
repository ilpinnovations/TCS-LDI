/*
 * File Summary: This file provides details of sessions. And option to add session to calendar.
 * Date: 25 Sep 2015
 * Author: Makarand Kate (974125)
 * Version: 1.2
 * -------------------------------------------------------------------------------
 * Change Log
 * -------------------------------------------------------------------------------
 * Changes:Removed rating 
 * Date: 7 Oct 2015
 * Editor: Makarand Kate (974125)
 * Version: 1.1
 * -------------------------------------------------------------------------------
 */

package com.tcs.tcsldi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSessionDetails extends Activity {

	DBHelper db=new DBHelper(this);

	int ddd=0;	
	int mmm=0;
	int yyy=0;
	int hhh=0;
	int mmn=0;
	int tid=0;
	int rating=0;
	int actionBarHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_session_deatails);
		
		Button tvbk=(Button)findViewById(R.id.tv_back_btn);
		tvbk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		Intent gint=getIntent();
		tid=gint.getExtras().getInt("tid");
		actionBarHeight=gint.getExtras().getInt("actionBarHeight");
		String name="";
		String date="";
		String time="";
		String location="";
		String desc="";
		String note="";
		String speaker="";
		Cursor cur=db.getAllTaskById(tid);
		if(cur.moveToFirst()){
			name=cur.getString(cur.getColumnIndex("tname"));
			int dy=cur.getInt(cur.getColumnIndex("dy"));
			int dm=cur.getInt(cur.getColumnIndex("dm"));
			int dd=cur.getInt(cur.getColumnIndex("dd"));
			ddd=dd;
			mmm=dm;
			yyy=dy;
			String monthOf[]=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			date=dd+" "+monthOf[dm-1]+" "+dy;
			int stime=cur.getInt(cur.getColumnIndex("stime"));
			int etime=cur.getInt(cur.getColumnIndex("etime"));
			int stimem=cur.getInt(cur.getColumnIndex("stimem"));
			int etimem=cur.getInt(cur.getColumnIndex("etimem"));
			hhh=stime;
			mmn=stimem;
			String stm="";
			String etm="";
			if(stime<12){
				stm=stime+" am";
			}else if(stime==12 && stimem==0){
				stm="12 pm";
			}else{
				stm=(stime-12)+":"+stimem+" pm";
			}
			
			if(etime<12){
				etm=etime+" am";
			}else if(etime==12 && etimem==0){
				etm="12 pm";
			}else{
				etm=(etime-12)+":"+etimem+" pm";
			}
			
			time=stm+" to "+etm;
			
			location=cur.getString(cur.getColumnIndex("location"));
			desc=cur.getString(cur.getColumnIndex("desc"));
			note=cur.getString(cur.getColumnIndex("note"));
			speaker=cur.getString(cur.getColumnIndex("speaker"));
		}

		final TextView tvspeaker=(TextView)findViewById(R.id.view_deatils_speaker);
		tvspeaker.setText(speaker);

		
		final TextView tvname=(TextView)findViewById(R.id.view_deatils_name);
		tvname.setText(name);

		final TextView tvdate=(TextView)findViewById(R.id.view_deatils_date);
		tvdate.setText(date);

		TextView tvtime=(TextView)findViewById(R.id.view_deatils_time);
		tvtime.setText(time);

		final TextView tvlocation=(TextView)findViewById(R.id.view_deatils_location);
		tvlocation.setText(location);

		TextView tvdescription=(TextView)findViewById(R.id.view_deatils_description);
		tvdescription.setText(desc);
		
		final EditText et=(EditText)findViewById(R.id.view_task_note);
		et.setText(note);
		Button btn=(Button)findViewById(R.id.view_task_btn);
		
		if(note.length()==0){
			btn.setText("Add to Calendar");
		}else{
			btn.setText("Update");
		}
		
		Calendar calendar=Calendar.getInstance();
		int dd = calendar.get(Calendar.DAY_OF_MONTH);
		int mm = calendar.get(Calendar.MONTH)+1;
		int yy = calendar.get(Calendar.YEAR);
		int hh = calendar.get(Calendar.HOUR_OF_DAY);
		int mn = calendar.get(Calendar.MINUTE);
		
		
		TextView personalNote=(TextView)findViewById(R.id.tv_personal_note);
		
		if(compareTime(yyy, mmm, ddd, hhh, mmn, yy, mm, dd, hh, mn)){
			btn.setVisibility(View.GONE);
			personalNote.setVisibility(View.VISIBLE);
			et.setFocusable(isRestricted());
			if(!(et.getText().toString().equals(""))){
				personalNote.setText(et.getText().toString());
			}
			et.setVisibility(View.GONE);
		}else{
			
		}
		
		final ScrollView sv=(ScrollView)findViewById(R.id.view_task_sv);
		sv.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sv.scrollTo(100, 0);
			}
		});
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Cursor mcur=db.getAllTaskById(tid);
				if(mcur.moveToFirst()){
					String title=mcur.getString(mcur.getColumnIndex("tname"));
					String desc=mcur.getString(mcur.getColumnIndex("desc"));
					int stime=mcur.getInt(mcur.getColumnIndex("stime"));
					int stimem=mcur.getInt(mcur.getColumnIndex("stimem"));
					int etime=mcur.getInt(mcur.getColumnIndex("etime"));
					int etimem=mcur.getInt(mcur.getColumnIndex("etimem"));
					int dd=mcur.getInt(mcur.getColumnIndex("dd"));
					Intent calIntent=new Intent(Intent.ACTION_INSERT);
					calIntent.setType("vnd.android.cursor.item/event");
					calIntent.putExtra(Events.TITLE, title);
					calIntent.putExtra(Events.DESCRIPTION,desc);
					
					
					calIntent.putExtra(Events.EVENT_LOCATION, tvlocation.getText().toString());

					GregorianCalendar calDate=new GregorianCalendar(yyy, mmm-1, dd,stime,stimem);
					GregorianCalendar calDateend=new GregorianCalendar(yyy, mmm-1, dd,etime,etimem);
					calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calDate.getTimeInMillis());
					calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,calDateend.getTimeInMillis());
					
					calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
					db.updateNote(tid, et.getText().toString());
					startActivity(calIntent);
					
				}
				
				
			}
		});


		
				

		final Button downbtn=(Button)findViewById(R.id.view_session_download);
		final TextView downsupport=(TextView)findViewById(R.id.donload_support);
		
		if(db.getDownLink(tid).equals("")){
			downbtn.setVisibility(View.GONE);
			downsupport.setVisibility(View.GONE);
		}else{
			downbtn.setVisibility(View.VISIBLE);
			downsupport.setVisibility(View.VISIBLE);
		}
		downbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(ViewSessionDetails.this,"http://16fc611f.ngrok.io/sch"+db.getDownLink(tid), Toast.LENGTH_LONG).show();
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.server)+db.getDownLink(tid)));
				startActivity(browserIntent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_session_deatails, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		onBackPressed();
	    return true;
	}
	
	/*
	 * Returns true if second time is past to first time
	 */
	public boolean compareTime(int yy1,int mm1,int dd1,int hh1,int mn1,int yy2,int mm2,int dd2,int hh2,int mn2){
		if(yy2>yy1){
			return true;
		}else if(yy2==yy1 && mm2>mm1){
			return true;
		}else if(yy2==yy1 && mm2==mm1 && dd2>dd1){
			return true;
		}else if(yy2==yy1 && mm2==mm1 && dd2==dd1 && hh2>hh1){
			return true;
		}else if(yy2==yy1 && mm2==mm1 && dd2==dd1 && hh2==hh1 && mn2>mn1){
			return true;
		}else{
			return false;	
		}
	}
		

}
