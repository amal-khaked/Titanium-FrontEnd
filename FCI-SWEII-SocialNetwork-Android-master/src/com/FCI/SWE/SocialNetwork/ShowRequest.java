package com.FCI.SWE.SocialNetwork;

import java.util.ArrayList;

import com.FCI.SWE.Controllers.Application;
import com.FCI.SWE.Controllers.UserController;
import com.FCI.SWE.Models.UserEntity;

import android.R.anim;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class ShowRequest extends Activity {
	EditText Sender;
	Button accept;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_request);
		// ArrayList<String> array = new ArrayList<String>();
		// Bundle extras = getIntent().getExtras();
		// String status = extras.getString("status");
		// String name = "";

		Sender = (EditText) findViewById(R.id.Friend);
		accept = (Button) findViewById(R.id.accept);

		/*
		 * while (extras.containsKey("name")) { name = extras.getString("name");
		 * array.add(name);
		 * 
		 * }
		 * 
		 * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1,array); setListAdapter(adapter);
		 */

		accept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				UserController controller = Application.getUserController();
				Bundle extras = getIntent().getExtras();
				String status = extras.getString("status");
				String name = "";
				if (extras.containsKey("name")) {
					 name = extras.getString("name");
				}
				controller.AddFriend(name, Sender.getText().toString());

			}
		});

	}

}
