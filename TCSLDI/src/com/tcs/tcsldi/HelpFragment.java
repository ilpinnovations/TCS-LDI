/*
 * File Summary: Provides guidelines for user
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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class HelpFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancState){
		
		View view=inflater.inflate(R.layout.fragment_help,container,false);
		
		WebView wv=(WebView)view.findViewById(R.id.webView1);
		String customHtml="<html><body><h1>Guidelines</h1><hr/><h2>TCS Leadership Development Institute, Trivandrum</h2>"
				+ "<b> General Guidelines to be followed in the premises at the TCS LDI centre for LDP Programs.</b>"
				+ "<ol><li>Kindly display the TCS ID card at all times inside the premises. In case of access issue,"
				+ " please get in touch with the co-ordinator.</li><li>Photography <b>IS NOT</b> allowed in the premises."
				+ "</li><li>Avoid carrying eatables in the accommodation or session halls.</li><li>Avoid alcohol consumption"
				+ " in the accommodation/premises.</li><li>Kindly avoid smoking inside the buildings; a smoking zone is"
				+ " available near the pump house.</li><li>You are free to use the Recreation facilities- the gym and badminton"
				+ " court, where instructions have been displayed about the timings and general guidelines.</li><li>"
				+ "<b>WiFi </b>- The WiFi has been enabled in the LDI main block for your use. If you face any concerns"
				+ " with the connectivity, please contact the Trivandrum IS Team.</li><li><b>Laptops</b> - LAN has been "
				+ "provided in the rooms. In case of any issues, please connect with the <b>IS team @ 2626</b> or <b>2727</b>"
				+ "</li><li>Kindly cooperate at all times with Security staff who may require to check your TCS ID cards/belongings"
				+ " at the entrance/in and around the premises.</li><li> Kindly hand over the laundry bag at the reception. "
				+ "Laundry services would be closed three days prior to the closure of the program.</li><li><b>Personal</b>"
				+ " and <b>Client</b> provisioned laptops are not allowed in the LDI main block.</li><li>Please adhere to <b>Tata"
				+ " Code of Conduct / IS Security guidelines</b> during the entire duration of the program.</li><li>On all days,"
				+ " you are requested to be in the session hall 10 minutes before the commencement of the session.</li><li>Kindly"
				+ " check the NOTICEBOARD kept at LDI entrance/accommodation for regular updates.</li><li><b>Canteen Timings</b>"
				+ "<table><tr><td>Breakfast</td><td>7:30 am to 9:00 am</td></tr><tr><td>Lunch</td><td>1:00 pm to 2:00 pm</td></tr>"
				+ "<tr><td>Dinner </td><td>8:00 pm to 10:00 pm</td></tr></table></li></ol></body></html>";
		wv.loadData(customHtml, "text/html", "UTF-8");
		
		return view;
	}
}
