package com.FCI.SWE.SocialNetwork;

import com.FCI.SWE.Controllers.Application;
import com.FCI.SWE.Controllers.UserController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends Activity implements OnClickListener {

	Button login, signUp;
	EditText userNameEditText;
	EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		login = (Button) findViewById(R.id.loginButton);
		signUp = (Button) findViewById(R.id.signUp);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
	
		login.setOnClickListener(this);

		signUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent registerationIntent = new Intent(
						getApplicationContext(), RegistrationActivity.class);
				startActivity(registerationIntent);
			}
		});

	}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		UserController controller = Application.getUserController();
		controller.login(userNameEditText.getText().toString(),
				passwordEditText.getText().toString());

	}

}
