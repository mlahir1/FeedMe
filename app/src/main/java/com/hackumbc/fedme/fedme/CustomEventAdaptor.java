package com.hackumbc.fedme.fedme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Nikhil Kumar Mengani on 10/7/2017.
 */

public class CustomEventAdaptor extends ArrayAdapter<Event> implements View.OnClickListener {

    ArrayList<Event> EventList;
    private final Activity context;
    Handler handler = new Handler();

    public CustomEventAdaptor(Activity context,  ArrayList<Event> EventArrayList) {
        super(context, R.layout.list_events_layout, EventArrayList);

        this.context=context;
        this.EventList = EventArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_events_layout, parent, false);
            holder.eventName = (TextView) view.findViewById(R.id.eventName);
            holder.Location = (TextView) view.findViewById(R.id.Location);
            holder.startTime = (TextView) view.findViewById(R.id.startTime);
            holder.endTime = (TextView) view.findViewById(R.id.endTime);
            holder.upVotes = (TextView) view.findViewById(R.id.upCount);
            holder.likeButton = (ImageView) view.findViewById(R.id.likeButton);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.eventName.setText(EventList.get(position).getName());
        holder.Location.setText(EventList.get(position).getLocation());
        String [] s = String.valueOf(EventList.get(position).getStartTime()).split(" ");
        String [] s1 = s[1].split(":");
        holder.startTime.setText(s1[0]+":"+s1[1]);
        s = String.valueOf(EventList.get(position).getEndTime()).split(" ");
        s1 = s[1].split(":");
        holder.endTime.setText(s1[0]+":"+s1[1]);
        holder.upVotes.setText(EventList.get(position).getUpCount());
        holder.likeButton.setTag(EventList.get(position).getId());

        holder.likeButton.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        String ffid = String.valueOf(v.getTag());
        String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        new CustomEventAdaptor.InvokeWeService().execute(ffid, android_id);
    }


    public class InvokeWeService extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url;

            String requestUrl = "http://ec2-54-213-169-9.us-west-2.compute.amazonaws.com/upvote.php";

            StringBuilder result = new StringBuilder();

            requestUrl = requestUrl + "?ff_id=" + strings[0] + "&user_id=" + strings[1];
            try {
                url = new URL(requestUrl);
                HttpURLConnection myconnection = (HttpURLConnection) url.openConnection();
                myconnection.setReadTimeout(15000);
                myconnection.setConnectTimeout(15000);
                myconnection.setRequestMethod("GET");
                myconnection.setDoInput(true);
                myconnection.setDoOutput(true);

                if (myconnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "nikhil";
        }

        @Override
        protected void onPostExecute (String s){
            super.onPostExecute(s);


        }
    }

    static class ViewHolder
    {
        TextView eventName, Location, startTime, endTime, upVotes;
        ImageView likeButton;
    }


}
