package com.hackumbc.fedme.fedme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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


public class FilterListActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ListView list;
    ArrayList<Item> itemList = new ArrayList<Item>();
    CheckBox openNowFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_filter_list);
        list=(ListView)findViewById(R.id.list);
        openNowFlag = (CheckBox) findViewById(R.id.open_now_y_n);
        openNowFlag.setChecked(true);
        String open_flag = "Y";
        new FilterListActivity.InvokeWeService().execute(open_flag);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openNowFlag.setOnCheckedChangeListener(this);
    }

    void callAdaptor()
    {


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        itemList = new ArrayList<Item>();
        new FilterListActivity.InvokeWeService().execute(isChecked ? "Y" : "N");
    }

    public class InvokeWeService extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            String response = "";
            String requestUrl = "http://ec2-54-213-169-9.us-west-2.compute.amazonaws.com/list_all.php";
            StringBuilder str = new StringBuilder();
            StringBuilder result = new StringBuilder();
            //str.append("test=" + "parameter&");
            str.append("?open_flag="+strings[0]);
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

                if (200 == HttpURLConnection.HTTP_OK)
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
                    Item item = new Item(innerJsonObject.getInt("item_id"),
                    innerJsonObject.getString("name"),
                    innerJsonObject.getString("place_name"),
                    innerJsonObject.getDouble("cost"),
                    innerJsonObject.getString("place_location"),
                    innerJsonObject.getString("wd_start"),
                    innerJsonObject.getString("wd_end"),
                    innerJsonObject.getString("we_start"),
                    innerJsonObject.getString("we_end"));

                    itemList.add(item);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomAdaptor adapter=new CustomAdaptor(FilterListActivity.this, itemList);
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
