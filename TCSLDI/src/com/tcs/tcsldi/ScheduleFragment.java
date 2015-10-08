/*
 * File Summary: Shows the current schedule
 * Date: 25 Sep 2015
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ScheduleFragment extends Fragment{
	DBHelper db;
	int yyy;
	int mmm;
	int ddd;
	int childAt=0;
	
	List<TaskDate> maintasklist=new ArrayList<TaskDate>();

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new DBHelper(activity);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancState){
		
		Calendar calendar=Calendar.getInstance();
		 ddd = calendar.get(Calendar.DAY_OF_MONTH);
		 mmm = calendar.get(Calendar.MONTH)+1;
		 yyy = calendar.get(Calendar.YEAR);
		int childAtCounter=0;
		View view=inflater.inflate(R.layout.fragment_schedule,container,false);
		
		final LinearLayout lm = (LinearLayout)view.findViewById(R.id.home_container);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
		String full="";
		Cursor cur=db.getAllDates();
		
		if(cur.moveToFirst()){
			do{
				int dd=cur.getInt(cur.getColumnIndex("dd"));
				int dm=cur.getInt(cur.getColumnIndex("dm"));
				int dy=cur.getInt(cur.getColumnIndex("dy"));
				String monthOf[]=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				String date=dd+" "+monthOf[dm-1]+" "+dy;
				full=full+date+"\n";
				Cursor rcur=db.getAllTaskByDate(dy, dm, dd);
				
				List<Task> tasklist=new ArrayList<Task>();
				
				/*
				 * 
				 * 
				 */
				// Create LinearLayout
				LinearLayout ll = new LinearLayout(getActivity());
		        ll.setOrientation(LinearLayout.VERTICAL);
		        //Toast.makeText(getActivity(),ddd+"d"+dd+"/"+mmm+"m"+dm+"/"+yyy+"y"+dy, Toast.LENGTH_LONG).show();
		        
	            // Create TextView
	            TextView datetitle = new TextView(getActivity());
	            datetitle.setText(date);
	            datetitle.setTextSize(22f);
	            datetitle.setBackgroundResource(R.drawable.black_bg);
	            datetitle.setTextColor(Color.parseColor("#ffffff"));
	            datetitle.setPadding(25, 10, 10, 10);
	            ll.addView(datetitle);
	             
	            // Create TextView
	             
	                  
				/*
				 * 
				 * 
				 */
				
				if(rcur.moveToFirst()){
					do{
						String title=rcur.getString(rcur.getColumnIndex("tname"));
						final int tid=rcur.getInt(rcur.getColumnIndex("sid"));
						int stime=rcur.getInt(rcur.getColumnIndex("stime"));
						int etime=rcur.getInt(rcur.getColumnIndex("etime"));
						int stimem=rcur.getInt(rcur.getColumnIndex("stimem"));
						int etimem=rcur.getInt(rcur.getColumnIndex("etimem"));
						int typ=rcur.getInt(rcur.getColumnIndex("typ"));
						String stm="";
						String etm="";
						if(stime<12){
							stm=stime+":"+String.format("%02d", stimem)+" am";
						}else if(stime==12 && stimem==0){
							stm="12 pm";
						}else{
							stm=(stime-12)+":"+String.format("%02d", stimem)+" pm";
						}
						
						if(etime<12){
							etm=etime+":"+String.format("%02d", etimem)+" am";
						}else if(etime==12 && etimem==0){
							etm="12 pm";
						}else{
							etm=(etime-12)+":"+String.format("%02d", etimem)+" pm";
						}
						
						String time=stm+" to "+etm;
						
						tasklist.add(new Task(tid,title));
						full=full+"> title:"+title+"\n";
						/*
						 * 
						 */
						// Create Button
						
						LinearLayout lls = new LinearLayout(getActivity());
				        if(typ==1){
				        	lls.setOrientation(LinearLayout.VERTICAL);
					        
				        	TextView taskitemtitle = new TextView(getActivity());
							taskitemtitle.setText(title);
							taskitemtitle.setTextSize(20f);
							
							TextView taskitemtime = new TextView(getActivity());
							taskitemtime.setText(time);
							taskitemtime.setPadding(5, 0, 0, 0);
							
							
							lls.addView(taskitemtitle);
							lls.addView(taskitemtime);
				            
				            
				            lls.setBackgroundResource(R.drawable.border);
				            lls.setPadding(20, 5, 0, 5);
				            
				            lls.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Intent nint=new Intent(getActivity(),ViewSessionDetails.class);
									nint.putExtra("tid", tid);
									startActivity(nint);
								}
							});
				            
				        }else{
				        	lls.setOrientation(LinearLayout.HORIZONTAL);
					        
				        	TextView taskitemtitle = new TextView(getActivity());
							taskitemtitle.setText(title);
							taskitemtitle.setTextSize(20f);
							TextView taskitemtime = new TextView(getActivity());
							taskitemtime.setText(time);
							taskitemtime.setPadding(5, 0, 0, 0);
							
							taskitemtime.setPadding(20, 10, 0, 0);
							lls.addView(taskitemtitle);
							lls.addView(taskitemtime);
							
							lls.setBackgroundResource(R.drawable.border_break);
				            lls.setPadding(20, 5, 0, 5);
				            
				        }
						ll.addView(lls);
						/*
						 * 
						 */
						
				        
					}while(rcur.moveToNext());
				}
				maintasklist.add(new TaskDate(date,tasklist));
				rcur.close();
				tasklist.clear();
				
				ll.setPadding(0, 0, 0, 10);

				lm.addView(ll);
				
				if(dd==ddd && dm==mmm && dy==yyy){
		        	//scrollTo=ll.getChildCount();
		        	childAt=childAtCounter;
		        	//Toast.makeText(getActivity(),"matched"+childAt, Toast.LENGTH_LONG).show();
			        
		        	
		        }else{
		        	//scrollTo=700;
		        }
				childAtCounter++;
			}while(cur.moveToNext());
			
		}
		cur.close();
		lm.setPadding(5, 0, 5, 0);
		
		
		final ScrollView sv=(ScrollView)view.findViewById(R.id.home_scroll);
		sv.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sv.smoothScrollTo(0, lm.getChildAt(childAt).getTop());
			}
		});
		
		return view;
	}
}
