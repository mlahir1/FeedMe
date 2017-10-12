package com.hackumbc.fedme.fedme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Nikhil Kumar Mengani on 10/7/2017.
 */

public class CustomAdaptor extends ArrayAdapter<Item> {

    ArrayList<Item> itemList;

    private final Activity context;

    public CustomAdaptor(Activity context,  ArrayList<Item> itemArrayList) {
        super(context, R.layout.list_items_layout, itemArrayList);

        this.context=context;
        this.itemList = itemArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_items_layout, parent, false);
            holder.itemName = (TextView) view.findViewById(R.id.itemName);
            holder.placeName = (TextView) view.findViewById(R.id.placeName);
            holder.cost = (TextView) view.findViewById(R.id.cost);
            holder.place_location = (TextView) view.findViewById(R.id.place_loc);
            holder.wd_start = (TextView) view.findViewById(R.id.wd_start);
            holder.wd_end = (TextView) view.findViewById(R.id.wd_end);
            holder.we_start = (TextView) view.findViewById(R.id.we_start);
            holder.we_end = (TextView) view.findViewById(R.id.we_end);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.itemName.setText(itemList.get(position).getName());
        holder.placeName.setText(itemList.get(position).getPlace_name());
        holder.cost.setText(String.valueOf(itemList.get(position).getCost()));
        holder.place_location.setText(itemList.get(position).getPlaceLocation());
        String [] s = itemList.get(position).getWeekdayStart().split(":");
        holder.wd_start.setText(s[0]+":"+s[1]);
        s = itemList.get(position).getWeekdayEnd().split(":");
        holder.wd_end.setText(s[0]+":"+s[1]);
        s = itemList.get(position).getWeekendStart().split(":");
        holder.we_start.setText(s[0]+":"+s[1]);
        s = itemList.get(position).getWeekendEnd().split(":");
        holder.we_end.setText(s[0]+":"+s[1]);

        return view;

    }


    static class ViewHolder
    {
        TextView itemName, placeName, cost, place_location, wd_start, wd_end, we_start, we_end;
    }


}
