package com.FCI.SWE.SocialNetwork;

import com.FCI.SWE.Controllers.Application;
import com.FCI.SWE.Controllers.UserController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity {

	TextView helloTextView;
	Button showrequests;
	Button Signout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityhome);
		Bundle extras = getIntent().getExtras();
		String status = extras.getString("status");
		String name = "", welcome = "Hello";
		Signout = (Button) findViewById(R.id.Signout);
		showrequests = (Button) findViewById(R.id.showrequests);

		if (extras.containsKey("name")) {
			name = extras.getString("name");
			welcome = "Welcome " + name;
		}
		helloTextView = (TextView) findViewById(R.id.helloText);
		String text = status + " ... " + welcome;
		helloTextView.setText(text);

		Signout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent signout = new Intent(getApplicationContext(), Home.class);
				startActivity(signout);
			}
		});
		showrequests.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//UserController controller = Application.getUserController();
				//Bundle extras = getIntent().getExtras();
				//String status = extras.getString("status");
				//String name = "";
				//if (extras.containsKey("name")) {
			//		name = extras.getString("name");
				//}
			//	controller.Show(name);
				Intent show = new Intent(getApplicationContext(),
						ShowRequest.class);
				startActivity(show);
				
			}
		});
	}
}