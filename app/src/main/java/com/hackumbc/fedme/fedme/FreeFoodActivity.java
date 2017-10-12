package com.hackumbc.fedme.fedme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class FreeFoodActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Event> eventList = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_free_food);
        list=(ListView)findViewById(R.id.list);
        new FreeFoodActivity.InvokeWeService().execute();
    }


    void callAdaptor()
    {


    }



    public class InvokeWeService extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            String response = "";
            String requestUrl = "http://ec2-54-213-169-9.us-west-2.compute.amazonaws.com/list_ff.php";

            StringBuilder result = new StringBuilder();
            try {
                url = new URL(requestUrl);
                HttpURLConnection myconnection = (HttpURLConnection) url.openConnection();
                myconnection.setReadTimeout(15000);
                myconnection.setConnectTimeout(15000);
                myconnection.setRequestMethod("GET");
                myconnection.setDoInput(true);
                myconnection.setDoOutput(true);

                if (myconnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                {
                    InputStream in = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result.toString();


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject innerJsonObject = jsonArray.getJSONObject(i);
                    Event event = new Event(
                            innerJsonObject.getInt("ff_id"),
                            innerJsonObject.getString("name"),
                            innerJsonObject.getString("location"),
                            innerJsonObject.getString("start_time"),
                            innerJsonObject.getString("end_time"),
                            innerJsonObject.getString("up_count")
                            );

                    eventList.add(event);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }   
            CustomEventAdaptor adapter=new CustomEventAdaptor(FreeFoodActivity.this, eventList);
            list.setAdapter(adapter);
        }
    }

    void addingElementsToArray(ArrayList<String> al,JSONArray jsonArray){
        for(int i=0;i<jsonArray.length();i++){
            try {
                al.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}
