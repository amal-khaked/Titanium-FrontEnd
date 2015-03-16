package com.FCI.SWE.SocialNetwork;

import com.FCI.SWE.Controllers.Application;
import com.FCI.SWE.Controllers.UserController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendRequest extends Activity implements OnClickListener {
	Button add;
	EditText friendName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendrequest);
		add=(Button)findViewById(R.id.add);
		friendName=(EditText)findViewById(R.id.friend_name);
		add.setOnClickListener(this);
	}
	@Override
	public void onClick(View v){
		UserController controller = Application.getUserController();
		Bundle extras = getIntent().getExtras();
		if(extras.containsKey("name")){
			String name = extras.getString("name");
		    controller.sendRequest(name, friendName.toString());
		}
	}
}
