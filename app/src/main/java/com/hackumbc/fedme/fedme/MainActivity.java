package com.hackumbc.fedme.fedme;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img1,img2,img3;
    Button find,post;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        img1 = (ImageView)findViewById(R.id.imageView);
        img2 = (ImageView)findViewById(R.id.imageView2);
        img3 = (ImageView)findViewById(R.id.imageView3);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView:
                Intent intent1 = new Intent(this,FilterListActivity.class);
                startActivity(intent1);
                break;
            case R.id.imageView2:
                Intent findfoodIntent = new Intent(this,FreeFoodActivity.class);
                startActivity(findfoodIntent);
                break;
            case R.id.imageView3:
                Intent postIntent = new Intent(this,PostFreeFood.class);
                startActivity(postIntent);
                break;
        }
    }
}
