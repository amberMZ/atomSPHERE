package com.example.amber.materialui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Amber on 5/18/15.
 */
public class welcomeActivity extends Activity {

    public void onLoginClick(View view) {
        Intent goToLoginPage = new Intent(this, loginActivity.class);
        startActivity(goToLoginPage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }
}
