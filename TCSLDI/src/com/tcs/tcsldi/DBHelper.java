/*
 * File Summary: Local Database Helper
 * Date: 24 Sep 2015
 * Author: Makarand Kate (974125)
 * Version: 1.3
 * -------------------------------------------------------------------------------
 * Change Log
 * -------------------------------------------------------------------------------
 * Changes:
 * Date:
 * Editor:
 * -------------------------------------------------------------------------------
 */

package com.tcs.tcsldi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "schedulerdb", null, 4);
		// TODO Auto-generated constructor stub
		
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE users(uid INTEGER PRIMARY KEY,name TEXT,password TEXT)");
		db.execSQL("CREATE TABLE login(lid INTEGER,uid INTEGER)");
		db.execSQL("CREATE TABLE tschedule(tid INTEGER PRIMARY KEY,sch TEXT)");
		db.execSQL("CREATE TABLE ctasks(sid INTEGER PRIMARI KEY,typ INTEGER,tid INTEGER,tname TEXT,"
									+ " dy INTEGER,dm INTEGER,dd INTEGER,stime INTEGER,stimem INTEGER,"
									+ "etime INTEGER,etimem INTEGER,location TEXT,speaker TEXT,desc TEXT,note TEXT null,"
									+ "rating INTEGER,feedback TEXT null,sync INTEGER,download TEXT null,downstatus INTEGER)");
		db.execSQL("CREATE TABLE feedbacks (fid INTEGER PRIMARY KEY, sync INTEGER, speaker TEXT, session TEXT, q1 INTEGER,"
				+ "q2 INTEGER,q3 INTEGER,q4 INTEGER,q5 INTEGER,q6 INTEGER,q7 INTEGER,q8 INTEGER,q9 INTEGER,q10 INTEGER,"
				+ "q11 INTEGER,q12 INTEGER,q13 INTEGER,q14 INTEGER,q15 INTEGER,s1 TEXT,s2 TEXT,s3 TEXT,s4 TEXT)");
		db.execSQL("CREATE TABLE impcontacts(cid INTEGER PRIMARY KEY,name TEXT,number TEXT)");
		
		db.execSQL("INSERT INTO login values(1,0)");
		
		db.execSQL("INSERT INTO tschedule VALUES(1,'null')");

		db.execSQL("INSERT INTO impcontacts VALUES(1,'TCS TVM Reception','04716629400')");
		db.execSQL("INSERT INTO impcontacts VALUES(2,'Bijo Thomas (TCS Safety Leader)','09249440406')");
		db.execSQL("INSERT INTO impcontacts VALUES(3,'Saju S(TCS Medical Emergency HR)','09995470741')");
		db.execSQL("INSERT INTO impcontacts VALUES(4,'Alpha RS(TCS HR head)','08089151055')");
		
		/*
		db.execSQL("INSERT INTO ctasks values(1,1,1,'Macro Economics',2015,9,23,9,0,10,30,'Safron','Dr. Shyamal Roy','Why Macro economy is important? How does an economy work? Understanding the macroeconomic concepts','',0,'',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(2,2,2,'Tea / Coffee break',2015,9,23,10,30,11,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(3,1,3,'Completion of Fiscal Policy discussions.',2015,9,23,11,0,13,0,'Safron','Dr. Shyamal Roy','Monetary Policy transmission mechanism and the money supply process','',0,'',0,'',0)");

		db.execSQL("INSERT INTO ctasks values(4,2,4,'Lunch break',2015,9,23,13,00,14,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(5,1,5,'Balance of Payments',2015,9,23,14,0,15,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'',0)");

		db.execSQL("INSERT INTO ctasks values(6,2,6,'Tea / Coffee break',2015,9,23,15,30,16,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(7,1,7,'Global Economic Scenario',2015,9,23,16,0,17,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'bla bla',0)");
		
		//----------------------------------
		
		db.execSQL("INSERT INTO ctasks values(8,1,8,'Basics of Financial Statements and their Importance',2015,9,24,9,0,10,30,'Safron','Dr. Shyamal Roy','Why Macro economy is important? How does an economy work? Understanding the macroeconomic concepts','',0,'',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(9,2,9,'Tea / Coffee break',2015,9,24,10,30,10,45,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(10,1,10,'Interpreting terms in Financial Statements',2015,9,24,10,45,13,0,'Safron','Dr. Shyamal Roy','Monetary Policy transmission mechanism and the money supply process','',0,'',0,'',0)");

		db.execSQL("INSERT INTO ctasks values(11,2,11,'Lunch break',2015,9,24,13,00,14,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(12,1,12,'Interpreting terms in Financial Statements',2015,9,24,14,0,17,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'',0)");

		//-----------------------------------
						
		
		db.execSQL("INSERT INTO ctasks values(13,1,13,'Macro Economics',2015,9,29,9,0,10,30,'Safron','Dr. Shyamal Roy','Why Macro economy is important? How does an economy work? Understanding the macroeconomic concepts','',0,'',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(14,2,14,'Tea / Coffee break',2015,9,29,10,30,11,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(15,1,15,'Completion of Fiscal Policy discussions.',2015,9,29,11,0,13,0,'Safron','Dr. Shyamal Roy','Monetary Policy transmission mechanism and the money supply process','',0,'',0,'',0)");

		db.execSQL("INSERT INTO ctasks values(16,2,16,'Lunch break',2015,9,29,13,00,14,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(17,1,17,'Balance of Payments',2015,9,29,14,0,15,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'',0)");

		db.execSQL("INSERT INTO ctasks values(18,2,18,'Tea / Coffee break',2015,9,29,15,30,16,00,'Safron','abc','description','',0,'good',0,'',0)");
		
		db.execSQL("INSERT INTO ctasks values(19,1,19,'Global Economic Scenario',2015,9,29,16,0,17,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'bla bla',0)");
		
		//----------------------------------
				
		
		*/
db.execSQL("INSERT INTO ctasks values(20,1,20,'Macro Economics',2015,10,1,9,0,10,30,'Safron','Dr. Shyamal Roy','Why Macro economy is important? How does an economy work? Understanding the macroeconomic concepts','',0,'',0,'',0)");

db.execSQL("INSERT INTO ctasks values(21,2,21,'Tea / Coffee break',2015,10,1,10,30,11,00,'Safron','abc','description','',0,'good',0,'',0)");

db.execSQL("INSERT INTO ctasks values(22,1,22,'Completion of Fiscal Policy discussions.',2015,10,1,11,0,13,0,'Safron','Dr. Shyamal Roy','Monetary Policy transmission mechanism and the money supply process','',0,'',0,'',0)");

db.execSQL("INSERT INTO ctasks values(23,2,23,'Lunch break',2015,10,1,13,00,14,00,'Safron','abc','description','',0,'good',0,'',0)");

db.execSQL("INSERT INTO ctasks values(24,1,24,'Balance of Payments',2015,10,1,14,0,15,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'',0)");

db.execSQL("INSERT INTO ctasks values(25,2,25,'Tea / Coffee break',2015,10,1,15,30,16,00,'Safron','abc','description','',0,'good',0,'',0)");

db.execSQL("INSERT INTO ctasks values(26,1,26,'Global Economic Scenario',2015,10,1,16,0,17,30,'Safron','Dr. Shyamal Roy','Balance of Payments and exchange rate determination Global Economic Scenario','',0,'',0,'bla bla',0)");

//----------------------------------

	}

	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("DROP TABLE IF EXISTS login");
		db.execSQL("DROP TABLE IF EXISTS ctasks");
		db.execSQL("DROP TABLE IF EXISTS tasks");
		db.execSQL("DROP TABLE IF EXISTS tschedule");
		db.execSQL("DROP TABLE IF EXISTS impcontacts");
		db.execSQL("DROP TABLE IF EXISTS feedbacks");
		 onCreate(db);
	}
	
	
	public String getCurrentSchedule(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT sch from tschedule where tid=1", new String[]{});
		if(cur.moveToFirst()){
			return cur.getString(cur.getColumnIndex("sch"));
		}else{
			return "";
		}
	}
	
	public long updateSchedule(String sch){
		SQLiteDatabase db=this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("sch", sch);
		long id=db.update("tschedule", values, "tid="+1, null);
		return id;
	}
	
	public void dropSchedule(){
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS ctasks");
		db.execSQL("CREATE TABLE ctasks(sid INTEGER PRIMARI KEY,typ INTEGER,tid INTEGER,tname TEXT,"
				+ " dy INTEGER,dm INTEGER,dd INTEGER,stime INTEGER,stimem INTEGER,"
				+ "etime INTEGER,etimem INTEGER,location TEXT,speaker TEXT,desc TEXT,note TEXT null,"
				+ "rating INTEGER,feedback TEXT null,sync INTEGER,download TEXT null,downstatus INTEGER)");

	}

	public long insertIntoSchedule(int typ,int tid,String name,int dy,int dm,int dd,
									int stime,int stimem,int etime,int etimem,String location,String speaker,String desc,String attachment){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("INSERT INTO ctasks values("+
					(getCount()+1)+","
					+typ+ ","
					+tid+ ",'"
					+name+ "',"
					+dy+ ","
					+dm+ ","
					+dd+ ","
					+stime+ ","
					+stimem+ ","
					+etime+ ","
					+etimem+ ",'"
					+location+ "','"
					+speaker+ "','"
					+desc+ "','',0,'',0,'"
					+attachment+ "',0)");
		
		return getCount();
	}
	
	public long updateLogin(int uid){
		SQLiteDatabase sdb=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("uid", uid);
		long id=sdb.update("login", values, "lid=1", null);
		//=sdb.insert("tasks", null, values);
		return id;
	}

		
	public Cursor getLogin(){
		 SQLiteDatabase db = this.getReadableDatabase();
			Cursor cur=db.rawQuery("select * from login", null);
			
			return cur;
	}

	public int getUser(String uname,String pass){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT uid from users where name=? and password=?", new String[]{uname,pass});
		if(cur.moveToFirst()){
			return cur.getInt(cur.getColumnIndex("uid"));
		}else{
			return -1;
		}
		
	}

	public Cursor getAllDates(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("select dy,dm,dd from ctasks group by dd order by dy,dm asc", null);
		return cur;
	}

	public int getCount(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("select count(sid) x from ctasks", null);
		if(cur.moveToFirst()){
			return cur.getInt(cur.getColumnIndex("x"));
		}else{
			return 0;
		}
	}

	public Cursor getAllTaskByDate(int dy,int dm,int dd){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("select * from ctasks where dy=? and dm=? and dd=?", new String[]{String.valueOf(dy),String.valueOf(dm),String.valueOf(dd)});
		
		return cur;
	}
	
	public Cursor getAllTaskById(int tid){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("select * from ctasks where sid=?", new String[]{String.valueOf(tid)});
		
		return cur;
	}
	
	public long updateNote(int tid,String note){
		SQLiteDatabase sdb=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("note", note);
		long id=sdb.update("ctasks", values, "sid="+tid, null);
		return id;
	}
	
	public long updateFeedBack(int tid,int rating,String feed){
		SQLiteDatabase sdb=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("rating", rating);
		values.put("feedback", feed);
		long id=sdb.update("ctasks", values, "sid="+tid, null);
		return id;
	}
	
	public int getRating(int tid){
			SQLiteDatabase db=this.getReadableDatabase();
			Cursor cur=db.rawQuery("SELECT rating from ctasks where sid="+tid, new String[]{});
			if(cur.moveToFirst()){
				return cur.getInt(cur.getColumnIndex("rating"));
			}else{
				return -1;
			}
	}

	public String getFeed(int tid){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT feedback from ctasks where sid="+tid, new String[]{});
		if(cur.moveToFirst()){
			return cur.getString(cur.getColumnIndex("feedback"));
		}else{
			return "";
		}
	}

	
	public String getDownLink(int tid){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT download from ctasks where sid="+tid, new String[]{});
		if(cur.moveToFirst()){
			return cur.getString(cur.getColumnIndex("download"));
		}else{
			return "";
		}
	}
	
	public String getTschedule(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("SELECT sch from tschedule where tid=1", new String[]{});
		if(cur.moveToFirst()){
			return cur.getString(cur.getColumnIndex("download"));
		}else{
			return "";
		}
	}
	

	public long updateTschedule(String tschedule){
		SQLiteDatabase sdb=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sch", tschedule);
		long id=sdb.update("tschedule", values, "tid="+1, null);
		return id;
	}
	
	public Cursor getTaskByPendingSync(){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur=db.rawQuery("select tid,rating,feedback from ctasks where rating!=0 and feedback!='' and sync=0",null);
		return cur;
	}
	
	public long updateSync(int tid){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("sync", 1);
		long id=db.update("ctasks", values, "tid="+tid, null);
		return id;
	}
	
	public Cursor getContacts(){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur=db.rawQuery("select * from impcontacts", null);
		return cur;
	}
	
	public int getFeedCount(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cur=db.rawQuery("select count(fid) x from feedbacks", null);
		if(cur.moveToFirst()){
			return cur.getInt(cur.getColumnIndex("x"));
		}else{
			return 0;
		}
	}
	
	public int insertIntoFeedback(String speaker,String session,int q1,int q2,int q3,int q4,int q5,int q6
			,int q7,int q8,int q9,int q10,int q11,int q12,int q13,int q14,int q15,String s1,String s2,String s3,String s4){
		SQLiteDatabase db=this.getWritableDatabase();
		
		String query="INSERT INTO feedbacks values("+(getFeedCount()+1)+",0,'"+speaker+"','"+session+"',"
				+q1+ ","+q2+","+q3+","+q4+","+q5+","+q6+","+q7+","+q8+","+q9+","+q10+","+q11+","+q12+","+q13+","+q14+","
						+q15+ ",'"+s1+"','"+s2+"','"+s3+"','"+s4+"')";
		db.execSQL(query);
		return getFeedCount();
	}
	
	public Cursor getAllSpeakers(){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor c=db.rawQuery("select speaker from ctasks group by speaker",null);
		return c;
	}
	
	public Cursor getLastSession(String speaker){
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor c=db.rawQuery("select dy,dm,dd,etime,etimem from ctasks where speaker='"+speaker+"'",null);
		return c;
	}
	
}
