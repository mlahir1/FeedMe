package com.hackumbc.fedme.fedme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PostFreeFood extends AppCompatActivity implements View.OnClickListener {

    EditText eventName,location;
    TimePicker startTime, endTime;
    ImageView post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_post_free_food);
        eventName =(EditText)findViewById(R.id.ff_name);
        location =(EditText)findViewById(R.id.ff_location);
        startTime =(TimePicker)findViewById(R.id.ff_start);
        endTime = (TimePicker) findViewById(R.id.ff_end);
        post =(ImageView)findViewById(R.id.post);
        post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch(v.getId()){
          case R.id.post:
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                String dateStr = dateFormat.format(new Date().getTime());
                String temp[] = new String[4];
                temp[0] =eventName.getText().toString();
              temp[1] = location.getText().toString();
              temp[2] = dateStr+"%20"+startTime.getHour()+":"+startTime.getMinute()+":00";
              temp[3] = dateStr+"%20"+endTime.getHour()+":"+endTime.getMinute()+":00";
                new PostFreeFood.InvokeWeService().execute(temp);

            break;
      }
    }
    public class InvokeWeService extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            String response = "";
            String requestUrl = "http://ec2-54-213-169-9.us-west-2.compute.amazonaws.com/post_ff.php";
            StringBuilder str = new StringBuilder();
            StringBuilder result = new StringBuilder();
            str.append("?name="+strings[0]+"&location=" + strings[1]+"&start_time="+strings[2]+"&end_time="+strings[3]);
            String mystring = str.toString();
            requestUrl = requestUrl +mystring;
            try {
                url = new URL(requestUrl);
                HttpURLConnection myconnection = (HttpURLConnection) url.openConnection();
                myconnection.setReadTimeout(15000);
                myconnection.setConnectTimeout(15000);
                myconnection.setRequestMethod("GET");
                myconnection.setDoInput(true);
                myconnection.setDoOutput(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "nikhil";


        }

        @Override
        protected void onPostExecute(String v) {
            super.onPostExecute(v);
            Toast.makeText(getApplicationContext(), "Succesfully Posted",
                    Toast.LENGTH_LONG).show();
            Intent reverse = new Intent(PostFreeFood.this,MainActivity.class);
            startActivity(reverse);
                }
        }

}
