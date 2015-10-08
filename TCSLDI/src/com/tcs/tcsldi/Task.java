/*
 * File Summary: class for Task datatype
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

public class Task {
	public int tid;
	public String tname;
	public Task(int tid,String tname){
		this.tid=tid;
		this.tname=tname;
	}
	
	public int getTid(){
		return tid;
	}
	
	public String getTname(){
		return tname;
	}
}
