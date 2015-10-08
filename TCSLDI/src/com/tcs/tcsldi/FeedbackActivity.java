/*
 * File Summary: Feedback form 
 * Date: 7 Oct 2015
 * Author: Makarand Kate (974125)
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
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

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
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends Activity {

	int opt_q1=0;
	int opt_q2=0;
	int opt_q3=0;
	int opt_q4=0;
	int opt_q5=0;
	int opt_q6=0;
	int opt_q7=0;
	int opt_q8=0;
	int opt_q9=0;
	int opt_q10=0;
	int opt_q11=0;
	int opt_q12=0;
	int opt_q13=0;
	int opt_q14=0;
	int opt_q15=0;
	
	DBHelper db;

	String speaker;
	String session;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		db=new DBHelper(this);
		context=this;
		Intent gint=getIntent();
		speaker=gint.getExtras().getString("speaker");
		
		TextView feedback_speaker=(TextView)findViewById(R.id.feedback_speaker);
		feedback_speaker.setText(speaker);
		
		Button back=(Button)findViewById(R.id.fb_back_btn);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		LinearLayout panel1=(LinearLayout)findViewById(R.id.panel1);
		panel1.setVisibility(View.VISIBLE);
		LinearLayout panel2=(LinearLayout)findViewById(R.id.panel2);
		panel2.setVisibility(View.VISIBLE);

		// q1 options
		final Button btn_q1_1=(Button)findViewById(R.id.btn_q1_1);
		final Button btn_q1_2=(Button)findViewById(R.id.btn_q1_2);
		final Button btn_q1_3=(Button)findViewById(R.id.btn_q1_3);
		final Button btn_q1_4=(Button)findViewById(R.id.btn_q1_4);
		final Button btn_q1_5=(Button)findViewById(R.id.btn_q1_5);

		btn_q1_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q1=1;
				btn_q1_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q1_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q1_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q1_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q1_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q1=2;
				btn_q1_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q1_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q1_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q1_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q1=3;
				btn_q1_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q1_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q1_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q1=4;
				btn_q1_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q1_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q1=5;
				btn_q1_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q1_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q2 options
		final Button btn_q2_1=(Button)findViewById(R.id.btn_q2_1);
		final Button btn_q2_2=(Button)findViewById(R.id.btn_q2_2);
		final Button btn_q2_3=(Button)findViewById(R.id.btn_q2_3);
		final Button btn_q2_4=(Button)findViewById(R.id.btn_q2_4);
		final Button btn_q2_5=(Button)findViewById(R.id.btn_q2_5);

		btn_q2_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q2=1;
				btn_q2_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q2_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q2_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q2_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q2_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q2=2;
				btn_q2_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q2_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q2_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q2_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q2=3;
				btn_q2_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q2_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q2_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q2=4;
				btn_q2_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q2_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q2=5;
				btn_q2_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q2_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q3 options
		final Button btn_q3_1=(Button)findViewById(R.id.btn_q3_1);
		final Button btn_q3_2=(Button)findViewById(R.id.btn_q3_2);
		final Button btn_q3_3=(Button)findViewById(R.id.btn_q3_3);
		final Button btn_q3_4=(Button)findViewById(R.id.btn_q3_4);
		final Button btn_q3_5=(Button)findViewById(R.id.btn_q3_5);

		btn_q3_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q3=1;
				btn_q3_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q3_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q3_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q3_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q3_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q3=2;
				btn_q3_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q3_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q3_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q3_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q3=3;
				btn_q3_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q3_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q3_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q3=4;
				btn_q3_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q3_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q3=5;
				btn_q3_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q3_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q4 options
		final Button btn_q4_1=(Button)findViewById(R.id.btn_q4_1);
		final Button btn_q4_2=(Button)findViewById(R.id.btn_q4_2);
		final Button btn_q4_3=(Button)findViewById(R.id.btn_q4_3);
		final Button btn_q4_4=(Button)findViewById(R.id.btn_q4_4);
		final Button btn_q4_5=(Button)findViewById(R.id.btn_q4_5);

		btn_q4_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q4=1;
				btn_q4_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q4_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q4_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q4_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q4_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q4=2;
				btn_q4_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q4_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q4_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q4_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q4=3;
				btn_q4_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q4_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q4_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q4=4;
				btn_q4_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q4_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q4=5;
				btn_q4_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q4_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q5 options
		final Button btn_q5_1=(Button)findViewById(R.id.btn_q5_1);
		final Button btn_q5_2=(Button)findViewById(R.id.btn_q5_2);
		final Button btn_q5_3=(Button)findViewById(R.id.btn_q5_3);
		final Button btn_q5_4=(Button)findViewById(R.id.btn_q5_4);
		final Button btn_q5_5=(Button)findViewById(R.id.btn_q5_5);

		btn_q5_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q5=1;
				btn_q5_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q5_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q5_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q5_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q5_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q5=2;
				btn_q5_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q5_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q5_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q5_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q5=3;
				btn_q5_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q5_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q5_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q5=4;
				btn_q5_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q5_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q5=5;
				btn_q5_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q5_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q6 options
		final Button btn_q6_1=(Button)findViewById(R.id.btn_q6_1);
		final Button btn_q6_2=(Button)findViewById(R.id.btn_q6_2);
		final Button btn_q6_3=(Button)findViewById(R.id.btn_q6_3);
		final Button btn_q6_4=(Button)findViewById(R.id.btn_q6_4);
		final Button btn_q6_5=(Button)findViewById(R.id.btn_q6_5);

		btn_q6_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q6=1;
				btn_q6_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q6_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q6_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q6_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q6_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q6=2;
				btn_q6_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q6_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q6_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q6_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q6=3;
				btn_q6_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q6_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q6_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q6=4;
				btn_q6_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q6_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q6=5;
				btn_q6_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q6_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q7 options
		final Button btn_q7_1=(Button)findViewById(R.id.btn_q7_1);
		final Button btn_q7_2=(Button)findViewById(R.id.btn_q7_2);
		final Button btn_q7_3=(Button)findViewById(R.id.btn_q7_3);
		final Button btn_q7_4=(Button)findViewById(R.id.btn_q7_4);
		final Button btn_q7_5=(Button)findViewById(R.id.btn_q7_5);

		btn_q7_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q7=1;
				btn_q7_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q7_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q7_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q7_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q7_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q7=2;
				btn_q7_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q7_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q7_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q7_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q7=3;
				btn_q7_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q7_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q7_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q7=4;
				btn_q7_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q7_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q7=5;
				btn_q7_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q7_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q8 options
		final Button btn_q8_1=(Button)findViewById(R.id.btn_q8_1);
		final Button btn_q8_2=(Button)findViewById(R.id.btn_q8_2);
		final Button btn_q8_3=(Button)findViewById(R.id.btn_q8_3);
		final Button btn_q8_4=(Button)findViewById(R.id.btn_q8_4);
		final Button btn_q8_5=(Button)findViewById(R.id.btn_q8_5);

		btn_q8_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q8=1;
				btn_q8_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q8_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q8_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q8_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q8_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q8=2;
				btn_q8_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q8_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q8_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q8_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q8=3;
				btn_q8_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q8_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q8_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q8=4;
				btn_q8_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q8_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q8=5;
				btn_q8_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q8_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q9 options
		final Button btn_q9_1=(Button)findViewById(R.id.btn_q9_1);
		final Button btn_q9_2=(Button)findViewById(R.id.btn_q9_2);
		final Button btn_q9_3=(Button)findViewById(R.id.btn_q9_3);
		final Button btn_q9_4=(Button)findViewById(R.id.btn_q9_4);
		final Button btn_q9_5=(Button)findViewById(R.id.btn_q9_5);

		btn_q9_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q9=1;
				btn_q9_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q9_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q9_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q9_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q9_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q9=2;
				btn_q9_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q9_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q9_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q9_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q9=3;
				btn_q9_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q9_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q9_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q9=4;
				btn_q9_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q9_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q9=5;
				btn_q9_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q9_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q10 options
		final Button btn_q10_1=(Button)findViewById(R.id.btn_q10_1);
		final Button btn_q10_2=(Button)findViewById(R.id.btn_q10_2);
		final Button btn_q10_3=(Button)findViewById(R.id.btn_q10_3);
		final Button btn_q10_4=(Button)findViewById(R.id.btn_q10_4);
		final Button btn_q10_5=(Button)findViewById(R.id.btn_q10_5);

		btn_q10_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q10=1;
				btn_q10_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q10_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q10_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q10_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q10_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q10=2;
				btn_q10_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q10_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q10_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q10_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q10=3;
				btn_q10_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q10_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q10_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q10=4;
				btn_q10_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q10_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q10=5;
				btn_q10_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q10_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q11 options
		final Button btn_q11_1=(Button)findViewById(R.id.btn_q11_1);
		final Button btn_q11_2=(Button)findViewById(R.id.btn_q11_2);
		final Button btn_q11_3=(Button)findViewById(R.id.btn_q11_3);
		final Button btn_q11_4=(Button)findViewById(R.id.btn_q11_4);
		final Button btn_q11_5=(Button)findViewById(R.id.btn_q11_5);

		btn_q11_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q11=1;
				btn_q11_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q11_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q11_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q11_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q11_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q11=2;
				btn_q11_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q11_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q11_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q11_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q11=3;
				btn_q11_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q11_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q11_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q11=4;
				btn_q11_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q11_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q11=5;
				btn_q11_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q11_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q12 options
		final Button btn_q12_1=(Button)findViewById(R.id.btn_q12_1);
		final Button btn_q12_2=(Button)findViewById(R.id.btn_q12_2);
		final Button btn_q12_3=(Button)findViewById(R.id.btn_q12_3);
		final Button btn_q12_4=(Button)findViewById(R.id.btn_q12_4);
		final Button btn_q12_5=(Button)findViewById(R.id.btn_q12_5);

		btn_q12_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q12=1;
				btn_q12_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q12_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q12_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q12_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q12_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q12=2;
				btn_q12_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q12_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q12_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q12_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q12=3;
				btn_q12_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q12_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q12_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q12=4;
				btn_q12_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q12_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q12=5;
				btn_q12_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q12_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q13 options
		final Button btn_q13_1=(Button)findViewById(R.id.btn_q13_1);
		final Button btn_q13_2=(Button)findViewById(R.id.btn_q13_2);
		final Button btn_q13_3=(Button)findViewById(R.id.btn_q13_3);
		final Button btn_q13_4=(Button)findViewById(R.id.btn_q13_4);
		final Button btn_q13_5=(Button)findViewById(R.id.btn_q13_5);

		btn_q13_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q13=1;
				btn_q13_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q13_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q13_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q13_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q13_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q13=2;
				btn_q13_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q13_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q13_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q13_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q13=3;
				btn_q13_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q13_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q13_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q13=4;
				btn_q13_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q13_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q13=5;
				btn_q13_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q13_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q14 options
		final Button btn_q14_1=(Button)findViewById(R.id.btn_q14_1);
		final Button btn_q14_2=(Button)findViewById(R.id.btn_q14_2);
		final Button btn_q14_3=(Button)findViewById(R.id.btn_q14_3);
		final Button btn_q14_4=(Button)findViewById(R.id.btn_q14_4);
		final Button btn_q14_5=(Button)findViewById(R.id.btn_q14_5);

		btn_q14_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q14=1;
				btn_q14_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q14_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q14_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q14_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q14_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q14=2;
				btn_q14_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q14_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q14_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q14_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q14=3;
				btn_q14_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q14_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q14_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q14=4;
				btn_q14_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q14_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q14=5;
				btn_q14_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q14_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------

		// q15 options
		final Button btn_q15_1=(Button)findViewById(R.id.btn_q15_1);
		final Button btn_q15_2=(Button)findViewById(R.id.btn_q15_2);
		final Button btn_q15_3=(Button)findViewById(R.id.btn_q15_3);
		final Button btn_q15_4=(Button)findViewById(R.id.btn_q15_4);
		final Button btn_q15_5=(Button)findViewById(R.id.btn_q15_5);

		btn_q15_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q15=1;
				btn_q15_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_2.setBackgroundResource(R.drawable.unselectedstar);
				btn_q15_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q15_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q15_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q15_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q15=2;
				btn_q15_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_3.setBackgroundResource(R.drawable.unselectedstar);
				btn_q15_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q15_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q15_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q15=3;
				btn_q15_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_4.setBackgroundResource(R.drawable.unselectedstar);
				btn_q15_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});

		btn_q15_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q15=4;
				btn_q15_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_5.setBackgroundResource(R.drawable.unselectedstar);
			}
		});
		

		btn_q15_5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				opt_q15=5;
				btn_q15_1.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_2.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_3.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_4.setBackgroundResource(R.drawable.selectedstar);
				btn_q15_5.setBackgroundResource(R.drawable.selectedstar);
			}
		});
		//-----------------------------------------------------
				
		Button submit=(Button)findViewById(R.id.feedback_submit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditText et1=(EditText)findViewById(R.id.suggestion_1);
				EditText et2=(EditText)findViewById(R.id.suggestion_2);
				EditText et3=(EditText)findViewById(R.id.suggestion_3);
				EditText et4=(EditText)findViewById(R.id.suggestion_4);
				String suggestion_1=et1.getText().toString();
				String suggestion_2=et2.getText().toString();
				String suggestion_3=et3.getText().toString();
				String suggestion_4=et4.getText().toString();
				
				if(opt_q1==0 || opt_q2==0 || opt_q3==0 || opt_q4==0 || opt_q5==0 || 
				   opt_q6==0 || opt_q7==0 || opt_q8==0 || opt_q9==0 || opt_q10==0 || 
				   opt_q11==0 || opt_q12==0 || opt_q13==0 || opt_q14==0 || opt_q15==0 || 
				   suggestion_1.isEmpty() || suggestion_2.isEmpty() || suggestion_3.isEmpty() || suggestion_4.isEmpty()){
				   Toast.makeText(FeedbackActivity.this, "You missed some fields please fill the whole form.", Toast.LENGTH_LONG).show();
				}else{
					/*db.insertIntoFeedback(speaker, session, opt_q1, opt_q2, opt_q3, opt_q4, opt_q5, opt_q6,
							opt_q7, opt_q8,opt_q9,opt_q10, opt_q11, opt_q12, opt_q13, opt_q14, opt_q15, 
							suggestion_1, suggestion_2, suggestion_3, suggestion_4);
					*/
					if(isNetworkAvailable()){
						//Toast.makeText(FeedbackActivity.this, "connecting| "+getResources().getString(R.string.server)+"services/feedbackcollector.php?speaker="+speaker, Toast.LENGTH_LONG).show();
						speaker=speaker.replaceAll("\\s+", "%20").replaceAll("\\.","");
						suggestion_1=suggestion_1.replaceAll("\\s","%20").replaceAll("\\.", "");
						suggestion_2=suggestion_2.replaceAll("\\s","%20").replaceAll("\\.", "");
						suggestion_3=suggestion_3.replaceAll("\\s","%20").replaceAll("\\.", "");
						suggestion_4=suggestion_4.replaceAll("\\s","%20").replaceAll("\\.", "");
						int eid=0;
						Cursor cur=db.getLogin();
						if(cur.moveToFirst()){
							eid=cur.getInt(cur.getColumnIndex("uid"));
						}
						
						String urlStr=getResources().getString(R.string.server)+"services/feedbackcollector.php?eid="+eid+"&speaker="+speaker
								+"&session=s&q1="+opt_q1+"&q2="+opt_q2+"&q3="+opt_q3+"&q4="+opt_q4+"&q5="+opt_q5+"&q6="+opt_q6
								+"&q7="+opt_q7+"&q8="+opt_q8+"&q9="+opt_q9+"&q10="+opt_q10+"&q11="+opt_q11+"&q12="+opt_q12+"&q13="+opt_q13
								+"&q14="+opt_q14+"&q15="+opt_q15+"&s1="+suggestion_1+"&s2="+suggestion_2+"&s3="+suggestion_3+"&s4="+suggestion_4;
						new RequestTask().execute(urlStr);
						db.insertIntoFeedback(speaker, "session", opt_q1, opt_q2, opt_q3, opt_q4, opt_q5, opt_q6, opt_q7, opt_q8, opt_q9, opt_q10, opt_q11, opt_q12, opt_q13, opt_q14, opt_q15, suggestion_1, suggestion_2, suggestion_3, suggestion_4);
						Toast.makeText(FeedbackActivity.this, "Feedback recorded successfully.", Toast.LENGTH_LONG).show();
						finish();
					}else{
						Toast.makeText(FeedbackActivity.this, "no network available", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
	}

	private class RequestTaskNw extends AsyncTask<String,String,String> {

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
				showNotification(context, 32, "Feedback", "Feedback recorded successfully");
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
