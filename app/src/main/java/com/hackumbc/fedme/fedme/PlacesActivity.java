package com.hackumbc.fedme.fedme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlacesActivity extends AppCompatActivity implements View.OnClickListener {

    Button place1,place2,place3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_places);
        place1 =(Button) findViewById(R.id.button);
        place2 =(Button)findViewById(R.id.button2);
        place3 = (Button)findViewById(R.id.button3);

        place1.setOnClickListener(this);
        place2.setOnClickListener(this);
        place3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intent1 = new Intent(this,FilterListActivity.class);
                intent1.putExtra("tag","1");
                intent1.putExtra("option","Tomato");
                startActivity(intent1);

                break;
            case R.id.button2:
                Intent intent2 = new Intent(this,FilterListActivity.class);
                intent2.putExtra("tag","1");
                intent2.putExtra("option","Masala");
                startActivity(intent2);

                break;
            case R.id.button3:
                Intent intent3 = new Intent(this,FilterListActivity.class);
                intent3.putExtra("tag","1");
                intent3.putExtra("option","TRUEGRITS");
                startActivity(intent3);
        }
    }
}
