/*
 * File Summary:
 * Date:
 * Author:
 * Version:
 * -------------------------------------------------------------------------------
 * Change Log
 * -------------------------------------------------------------------------------
 * Changes:
 * Date:
 * Editor:
 * -------------------------------------------------------------------------------
 */

package com.tcs.tcsldi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlacesFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancState){
		
		View view=inflater.inflate(R.layout.fragment_places,container,false);
		
		final LinearLayout lm = (LinearLayout)view.findViewById(R.id.places_container);
		
		for(int i=0;i<10;i++){
			
		
		
		LinearLayout ll_place = new LinearLayout(getActivity());
        ll_place.setOrientation(LinearLayout.HORIZONTAL);
        ll_place.setBackgroundResource(R.drawable.border);
        
        ImageView img=new ImageView(getActivity());
        img.setBackgroundResource(R.drawable.ic_launcher);
        img.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        img.setPadding(10, 10, 10, 10);
        
        TextView tv=new TextView(getActivity());
        tv.setText("Place"+i);
        tv.setTextSize(22f);
        
        ll_place.addView(img);
        ll_place.addView(tv);
        
        ll_place.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "place chosed",	Toast.LENGTH_SHORT).show();
			}
		});
        
        lm.addView(ll_place);
		
		}

		
		return view;
	}
}
