/*
 * File Summary: async task template
 * Date: 5 Oct 2015
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.widget.Toast;

public class RequestTask extends AsyncTask<String,String,String> {

	@Override
	protected String doInBackground(String... uri) {
		// TODO Auto-generated method stub
		HttpClient httpclient=new DefaultHttpClient();
		HttpResponse response;
		String responseString=null;
		try{
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
			
		}catch(IOException e){
			
		}
		
		return responseString;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		//do anything with response
		
	}
	
}
