/*
 * File Summary: Important contacts
 * Date: 30 Sep 2015
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

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactFragment extends Fragment{
	DBHelper db;
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new DBHelper(activity);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancState){
		
		View view=inflater.inflate(R.layout.fragment_contacts,container,false);
		
		final LinearLayout lm = (LinearLayout)view.findViewById(R.id.contact_container);
		
		Cursor cur=db.getContacts();
		if(cur.moveToFirst()){
			do{
				String Name=cur.getString(cur.getColumnIndex("name"));
				final String Number=cur.getString(cur.getColumnIndex("number"));
				
				LinearLayout ll_contact = new LinearLayout(getActivity());
		        ll_contact.setOrientation(LinearLayout.VERTICAL);
		        ll_contact.setPadding(5, 5, 5, 5);
		        

		        LinearLayout ll_contact_div1 = new LinearLayout(getActivity());
		        ll_contact_div1.setOrientation(LinearLayout.HORIZONTAL);
		        ll_contact_div1.setPadding(5, 5, 5, 5);

		        
		        ImageView ll_contact_div1_img = new ImageView(getActivity());
		        //ll_contact_div1_img.setBackgroundResource(R.drawable.selectedstar);
		        ll_contact_div1_img.setMaxHeight(40);
		        ll_contact_div1_img.setMaxWidth(40);
		        
		        LinearLayout ll_contact_div1_sub = new LinearLayout(getActivity());
		        ll_contact_div1_sub.setOrientation(LinearLayout.VERTICAL);

		        TextView ll_contact_div1_sub_name=new TextView(getActivity());
		        ll_contact_div1_sub_name.setText(Name);
		        ll_contact_div1_sub_name.setTextSize(20f);
		        ll_contact_div1_sub_name.setPadding(10, 0, 0, 0);
		        
		        TextView ll_contact_div1_sub_phone=new TextView(getActivity());
		        ll_contact_div1_sub_phone.setText(Number);
		        ll_contact_div1_sub_phone.setTextSize(18f);
		        ll_contact_div1_sub_phone.setPadding(10, 0, 0, 0);
		        
		        ll_contact_div1_sub.addView(ll_contact_div1_sub_name);
		        ll_contact_div1_sub.addView(ll_contact_div1_sub_phone);

		        ll_contact_div1.addView(ll_contact_div1_img);
		        ll_contact_div1.addView(ll_contact_div1_sub);
		        
		        LinearLayout ll_contact_div2 = new LinearLayout(getActivity());
		        ll_contact_div2.setBackgroundColor(Color.parseColor("#18bd9b"));
		        ll_contact.setOrientation(LinearLayout.VERTICAL);
		        
		        Button ll_contact_div2_call=new Button(getActivity());
		        
		        ll_contact_div2_call.setBackgroundResource(R.drawable.call);
		        ll_contact_div2_call.setHeight(50);
		        ll_contact_div2_call.setPadding(10, 0, 10, 0);
		        ll_contact_div2_call.setOnClickListener(new OnClickListener() {
		        	
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent callIntent=new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:"+Number));
						startActivity(callIntent);
					}
				});

		        Button ll_contact_div2_msg=new Button(getActivity());
		        ll_contact_div2_msg.setBackgroundResource(R.drawable.message);
		        ll_contact_div2_msg.setPadding(10, 0, 10, 0);
		        ll_contact_div2_msg.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent callIntent=new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+Number));
						callIntent.putExtra("sms_body","");
						startActivity(callIntent);
					}
				});
		        
		        ll_contact_div2.addView(ll_contact_div2_call);
		        ll_contact_div2.addView(ll_contact_div2_msg);
		        
		        ll_contact.addView(ll_contact_div1);
		        ll_contact.addView(ll_contact_div2);
		        
		        ll_contact.setPadding(5, 10, 5, 5);
		        ll_contact.setBackgroundResource(R.drawable.border);
		        
		        lm.addView(ll_contact);

				
			}while(cur.moveToNext());
		}
		
		return view;
	}
}
