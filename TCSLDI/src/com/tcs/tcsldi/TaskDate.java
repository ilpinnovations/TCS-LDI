/*
 * File Summary: class for TaskDate datatype
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

import java.util.List;

public class TaskDate {
	public String title;
	public int stime;
	public int etime;
	
	public List<Task> tasklist;
	public TaskDate(String title,List<Task> tasklist){
		this.title=title;
		this.tasklist=tasklist;
	}
	public List<Task> getTasklist(){
		return tasklist;
	}
	
	public int getStime(){
		return stime;
	}
	
	public int getEtime(){
		return etime;
	}
	
	public String getTitle(){
		return title;
	}
	
}
