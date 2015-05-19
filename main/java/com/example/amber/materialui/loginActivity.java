package com.example.amber.materialui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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


    public void submitLoginInfo() throws JSONException {
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        JSONObject loginInfo = new JSONObject();
        loginInfo.put("username", username);
        loginInfo.put("password", password);

        HttpClient httpclient = new DefaultHttpClient();
        try {
//            HttpPost httpPost = new HttpPost("http://attu.cs.washington.edu:8000/auth/");
//            StringEntity dataString = new StringEntity(loginInfo.toString());
//            httpPost.setEntity(dataString);
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//            //httpPost.setHeader("Authorization", getB64Auth(username, password));
//            httpPost.setHeader("Authorization", getB64Auth("admin", "password"));
//            Log.v("loginActivity", getB64Auth(username, password));


            HttpGet HttpGet = new HttpGet("http://attu.cs.washington.edu:8000/auth/");
            StringEntity dataString = new StringEntity(loginInfo.toString());
            //HttpGet.setEntity(dataString);
            HttpGet.setHeader("Accept", "application/json");
            HttpGet.setHeader("Content-type", "application/json");
            HttpGet.setHeader("Authorization", getB64Auth("admin", "password"));
            Log.v("loginActivity", getB64Auth(username, password));



            HttpResponse httpResponse = httpclient.execute(HttpGet);
            InputStream is = httpResponse.getEntity().getContent();
            InputStreamReader isr = new InputStreamReader(is);
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(isr);
            String read = br.readLine();
            while(read != null) {
                sb.append(read);
                read = br.readLine();
                Log.v("loginActivity", " response message " + sb.toString());
            }

            Log.v("loginActivity", " response message " + sb.toString());
        } catch (IOException e) {

        }

        Intent goToMainPage = new Intent(this, MainActivity.class);
        startActivity(goToMainPage);
    }


    public String getB64Auth (String username, String password) {
        String source = username+":"+ password;
        return "Basic "+ Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
    }
}
