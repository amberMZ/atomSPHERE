package com.example.amber.materialui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Amber on 5/18/15.
 */
public class loginActivity extends Activity {
    private EditText usernameView;
    private EditText passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        usernameView = (EditText)findViewById(R.id.usernameInput);
        passwordView = (EditText)findViewById(R.id.passwordInput);

    }


    public void onloginSubmitClick(View view){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    submitLoginInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }


    public void submitLoginInfo() throws JSONException, IOException {
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        JSONObject loginInfo = new JSONObject();
        loginInfo.put("username", username);
        loginInfo.put("password", password);

        HttpClient httpclient = new DefaultHttpClient();

//            HttpPost httpPost = new HttpPost("http://attu.cs.washington.edu:8000/auth/");
//            StringEntity dataString = new StringEntity(loginInfo.toString());
//            httpPost.setEntity(dataString);
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//            //httpPost.setHeader("Authorization", getB64Auth(username, password));
//            httpPost.setHeader("Authorization", getB64Auth("admin", "password"));
//            Log.v("loginActivity", getB64Auth(username, password));

        try {
            HttpGet HttpGet = new HttpGet("http://attu.cs.washington.edu:8000/auth/");
            StringEntity dataString = new StringEntity(loginInfo.toString());
            //HttpPost.setEntity(dataString);
            String loginInfoAuth = getB64Auth(username, password);
            HttpGet.setHeader("Accept", "application/json");
            HttpGet.setHeader("Content-type", "application/json");
            HttpGet.setHeader("Authorization", loginInfoAuth);
            Log.i("loginActivity", loginInfoAuth);

            HttpResponse httpResponse = httpclient.execute(HttpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200){
                Log.i("loginActivity","Login successful");
                Intent goToMainPage = new Intent(this, MainActivity.class);
                startActivity(goToMainPage);
            } else {
                Log.i("loginActivity","Error logging in, calling showLoginErrorDialog");
                showLoginErrorDialog();
            }

        } catch (IOException e){
            Log.i("loginActivity","IO exception");
        }

    }


    public String getB64Auth (String username, String password) {
        String source = username+":"+ password;
        return "Basic "+ Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
    }

    public void showLoginErrorDialog(){
        Log.i("loginActivity","showLoginErrorDialog called");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

        alertDialogBuilder.setTitle("Error in logging in");
        alertDialogBuilder
                .setMessage("Username and password do not match, please try again")
                .setCancelable(false)
                .setPositiveButton("Try again",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close current activity
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close the dialog box and do nothing
                        loginActivity.this.finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
