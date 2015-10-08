/*
 * File Summary: List of pending feedbacks
 * Date: 5 Oct 2015
 * Author: Makarand Kate(974125)
 * Version: 1.3
 * -------------------------------------------------------------------------------
 * Change Log
 * -------------------------------------------------------------------------------
 * Changes: Removed session wise feedback and added speaker wise feedback
 * Date: 7 Oct 2015
 * Editor: Makarand Kate (974125)
 * Version: 1.1
 * -------------------------------------------------------------------------------
 * -------------------------------------------------------------------------------
 * Changes: List of feedback sorted and filtered according to date
 * Date: 8 Oct 2015
 * Editor: Makarand Kate (974125)
 * Version: 1.2
 * -------------------------------------------------------------------------------
 */

package com.tcs.tcsldi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackFragment extends Fragment{
	
	DBHelper db;
	int yyy=0;
	int mmm=0;
	int ddd=0;
	int lend=0;
	int lendm=0;
	int childAt=0;
	
	List<TaskDate> maintasklist=new ArrayList<TaskDate>();

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new DBHelper(activity);
    }

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancState){
		
		View view=inflater.inflate(R.layout.fragment_feedback,container,false);
		
final LinearLayout lm = (LinearLayout)view.findViewById(R.id.feedback_container);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
		Cursor cur=db.getAllSpeakers();
		
		if(cur.moveToFirst()){
			do{
				final String speaker=cur.getString(cur.getColumnIndex("speaker"));
				if(!(speaker.equals("") || speaker.equals("none"))){
					
					
					Cursor mcur=db.getLastSession(speaker);
					if(mcur.moveToFirst()){
						int dy=mcur.getInt(mcur.getColumnIndex("dy"));
						int dm=mcur.getInt(mcur.getColumnIndex("dm"));
						int dd=mcur.getInt(mcur.getColumnIndex("dd"));
						int etime=mcur.getInt(mcur.getColumnIndex("etime"));
						int etimem=mcur.getInt(mcur.getColumnIndex("etimem"));
						
						if(compareTime(yyy,mmm,ddd,lend,lendm,dy,dm,dd,etime,etimem)){
							yyy=dy;
							mmm=dm;
							ddd=dd;
							lend=etime;
							lendm=etimem;
						}
						
					}while(mcur.moveToNext());
					
					
					Calendar calendar=Calendar.getInstance();
					 
					 if(compareTime(yyy,mmm,ddd,lend,lendm,calendar.get(Calendar.YEAR),(calendar.get(Calendar.MONTH)+1),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE))){
						 TextView speakertext = new TextView(getActivity());
							speakertext.setTextSize(22f);
							speakertext.setBackgroundResource(R.drawable.border);
							speakertext.setTextColor(Color.parseColor("#000000"));
							speakertext.setPadding(25, 10, 10, 10);
							speakertext.setText(speaker);
				            lm.addView(speakertext);
				            
				            speakertext.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub

									Intent intn=new Intent(getActivity(),FeedbackActivity.class);
									intn.putExtra("speaker", speaker);
									startActivity(intn);
								}
							});
					 }
					
				}
				yyy=0;
				mmm=0;
				ddd=0;
				lend=0;
				lendm=0;
								
			}while(cur.moveToNext());
			
		}
		cur.close();
		lm.setPadding(5, 0, 5, 0);

		
		return view;
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
