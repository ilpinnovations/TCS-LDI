/*
 * File Summary: Collects all fragments
 * Date: 24 Sep 2015
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

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle="";
	DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBHelper(this);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#18bd9b")));
        bar.setTitle("TCS Leadership Development Institute");
        mTitle=(String)getTitle();
        ScheduleFragment scheduleFragment=new ScheduleFragment();
		FragmentTransaction ft=getFragmentManager().beginTransaction();
		ft.replace(R.id.content_frame,scheduleFragment);
		ft.commit();
        
        
        
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
		
		mDrawerList=(ListView)findViewById(R.id.drawer_list);
		
		mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.ic_launcher,R.string.menu_drawer_open){
			@Override
			public void onDrawerClosed(View drawerView){
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			@Override
			public void onDrawerOpened(View drawerView){
				getSupportActionBar().setTitle("Select an option");
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getBaseContext(),R.layout.drawer_list_item,getResources().getStringArray(R.array.drawer_menu_list));
		mDrawerList.setAdapter(adapter);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		mDrawerList.setOnItemClickListener(new OnItemClickListener(){
			
			

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				switch(position){
					case 0:{
						ScheduleFragment fragment=new ScheduleFragment();
						FragmentTransaction ft=getFragmentManager().beginTransaction();
						ft.replace(R.id.content_frame,fragment);
						ft.commit();
						mDrawerLayout.closeDrawer(mDrawerList);
						
					}break;
					case 1:{
						FeedbackFragment fragment=new FeedbackFragment();
						
						FragmentTransaction ft=getFragmentManager().beginTransaction();
						ft.replace(R.id.content_frame,fragment);
						ft.commit();
						mDrawerLayout.closeDrawer(mDrawerList);
						
					}break;
					case 2:{
						ContactFragment fragment=new ContactFragment();
						
						FragmentTransaction ft=getFragmentManager().beginTransaction();
						ft.replace(R.id.content_frame,fragment);
						ft.commit();
						mDrawerLayout.closeDrawer(mDrawerList);
						
					}break;
					case 3:{
						PlacesFragment fragment=new PlacesFragment();
						
						FragmentTransaction ft=getFragmentManager().beginTransaction();
						ft.replace(R.id.content_frame,fragment);
						ft.commit();
						mDrawerLayout.closeDrawer(mDrawerList);
						
					}break;
					case 4:{
						HelpFragment fragment=new HelpFragment();
						
						FragmentTransaction ft=getFragmentManager().beginTransaction();
						ft.replace(R.id.content_frame,fragment);
						ft.commit();
						mDrawerLayout.closeDrawer(mDrawerList);
						
					}break;
					case 5:{
						db.updateLogin(0);
						Intent intn=new Intent(MainActivity.this,LoginActivity.class);
						startActivity(intn);
						finish();
					}break;
					default:{
						
					}break;
				}
				
			}
		});
	
    }


	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	if(mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
    }
    
    @Override
	public boolean onPrepareOptionsMenu(Menu menu){
		menu.findItem(R.id.action_settings).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent e) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            
            if (!mDrawerLayout.isDrawerOpen(Gravity.START)) {
                mDrawerLayout.openDrawer(Gravity.START);
            }else{
            	mDrawerLayout.closeDrawers();
            }
            return true;
        }
        return super.onKeyDown(keyCode, e);
    }
    
}
