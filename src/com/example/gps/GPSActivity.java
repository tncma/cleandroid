package com.example.gps;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GPSActivity extends Activity implements OnClickListener {

	Button send_sms;
    EditText bin_number,phone_number;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
	
		bin_number=(EditText)findViewById(R.id.bin_number);
		phone_number=(EditText)findViewById(R.id.phone_number);
		send_sms=(Button)findViewById(R.id.send_sms);
		
		send_sms.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		GPSLocation gps = new GPSLocation(GPSActivity.this);
		Location loc=gps.getLocation();
		
		double latitude = loc.getLatitude();
    	double longitude = loc.getLongitude();
    	
    	Log.v("GPS App","latitude="+latitude);
    	Log.v("GPS App","longitude="+longitude);
    	
       	String bin_num=bin_number.getText().toString();
    	String ph_num=phone_number.getText().toString();
    	
    	
//    	TelephonyManager telMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
//    	String current_number=telMgr.getSimSerialNumber();
//    	
//    	Toast.makeText(getApplicationContext(), "current_number="+current_number, Toast.LENGTH_LONG).show();
//    	
    	String message="Bin Number: "+bin_num +",Latitude: "+latitude+",Longitude: "+longitude;
    	
    	SmsManager sms=SmsManager.getDefault();
    	sms.sendTextMessage(ph_num, null, message, null, null);    	
    	
    	Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    	Toast.makeText(getApplicationContext(), "SMS sent successfully", Toast.LENGTH_LONG).show();
	}

	
}
