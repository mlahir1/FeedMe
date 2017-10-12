package com.hackumbc.fedme.fedme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserRatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_rating);
    }
}
