package com.hackumbc.fedme.fedme;

/**
 * Created by lahir on 10/7/2017.
 */

public class Item {
    int itemId;
    String name;
    String place_name;
    Double cost;
    String placeLocation;
    String weekdayStart;
    String weekdayEnd;
    String weekendStart;
    String weekendEnd;

    public Item(int itemId, String name, String place_name, Double cost, String placeLocation, String weekdayStart, String weekdayEnd, String weekendStart, String weekendEnd) {
        this.itemId = itemId;
        this.name = name;
        this.place_name = place_name;
        this.cost = cost;
        this.placeLocation = placeLocation;
        this.weekdayStart = weekdayStart;
        this.weekdayEnd = weekdayEnd;
        this.weekendStart = weekendStart;
        this.weekendEnd = weekendEnd;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public String getWeekdayStart() {
        return weekdayStart;
    }

    public void setWeekdayStart(String weekdayStart) {
        this.weekdayStart = weekdayStart;
    }

    public String getWeekdayEnd() {
        return weekdayEnd;
    }

    public void setWeekdayEnd(String weekdayEnd) {
        this.weekdayEnd = weekdayEnd;
    }

    public String getWeekendStart() {
        return weekendStart;
    }

    public void setWeekendStart(String weekendStart) {
        this.weekendStart = weekendStart;
    }

    public String getWeekendEnd() {
        return weekendEnd;
    }

    public void setWeekendEnd(String weekendEnd) {
        this.weekendEnd = weekendEnd;
    }
}
