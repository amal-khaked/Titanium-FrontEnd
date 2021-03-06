package com.FCI.SWE.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.FCI.SWE.Models.UserEntity;
import com.FCI.SWE.SocialNetwork.HomeActivity;
import com.FCI.SWE.SocialNetwork.ShowRequest;

public class UserController {

	private static UserEntity currentActiveUser;
	private static UserController userController;

	public static UserController getInstance() {
		if (userController == null)
			userController = new UserController();
		return userController;
	}

	private UserController() {

	}

	public void login(String userName, String password) {

		new Connection().execute(
				"http:/tit-anium.appspot.com/rest/LoginService", userName,
				password, "LoginService");
	}

	public void AddFriend(String RecEamil, String SenderEmail) {
		new Connection().execute("http://tit-anium.appspot.com/rest/AddFriend",
				RecEamil, SenderEmail, "AddFriend");
	}

	public void signUp(String userName, String email, String password) {
		new Connection().execute(
				"http:/tit-anium.appspot.com/rest/RegistrationService",
				userName, email, password, "RegistrationService");
	}

	public void sendRequest(String fromName, String toName) {
		new Connection().execute(
				"http:/tit-anium.appspot.com/rest/SendRequest", fromName,
				toName, "SendRequest");
	}

	static private class Connection extends AsyncTask<String, String, String> {

		String serviceType;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL url;
			serviceType = params[params.length - 1];
			String urlParameters = "";
			if (serviceType.equals("LoginService"))
				urlParameters = "uname=" + params[1] + "&password=" + params[2];
			else if (serviceType.equals("RegistrationService"))
				urlParameters = "uname=" + params[1] + "&email=" + params[2]
						+ "&password=" + params[3];
			else if (serviceType.equals("SendRequest"))
				urlParameters = "fromName=" + params[1] + "&fromName="
						+ params[2];
			else if (serviceType.equals("Show"))
				urlParameters = "RecEamil" + params[1];
			HttpURLConnection connection;
			try {
				url = new URL(params[0]);

				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setInstanceFollowRedirects(false);
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(60000); // 60 Seconds
				connection.setReadTimeout(60000); // 60 Seconds

				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				String line, retJson = "";
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				while ((line = reader.readLine()) != null) {
					retJson += line;
				}
				return retJson;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				JSONObject object = new JSONObject(result);

				if (!object.has("Status")
						|| object.getString("Status").equals("Failed")) {
					Toast.makeText(Application.getAppContext(),
							"Error occured", Toast.LENGTH_LONG).show();
					return;
				}

				if (serviceType.equals("LoginService")) {

					currentActiveUser = UserEntity.createLoginUser(result);

					Intent homeIntent = new Intent(Application.getAppContext(),
							HomeActivity.class);
					System.out.println("--- " + serviceType + "IN LOGIN "
							+ object.getString("Status"));

					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					/* here you should initialize user entity */
					homeIntent.putExtra("status", object.getString("Status"));
					homeIntent.putExtra("name", object.getString("name"));

					Application.getAppContext().startActivity(homeIntent);
				} else {
					if (serviceType.equals("SendRequest")) {
						Intent homeIntent = new Intent(
								Application.getAppContext(), HomeActivity.class);
						homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						homeIntent.putExtra("status", "Requested successfully");
						Application.getAppContext().startActivity(homeIntent);
					} else if (serviceType.equals("Show")) {
						Intent replayIntent = new Intent(
								Application.getAppContext(), ShowRequest.class);
						replayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						replayIntent.putExtra("name", result);
						Application.getAppContext().startActivity(replayIntent);

						currentActiveUser = UserEntity.createLoginUser(result);

					} else {
						Intent homeIntent = new Intent(
								Application.getAppContext(), HomeActivity.class);
						homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						homeIntent
								.putExtra("status", "Registered successfully");
						Application.getAppContext().startActivity(homeIntent);
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}