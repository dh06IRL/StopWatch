package com.casenex.stopwatch.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by davidhodge on 6/22/15.
 */
public class LapTime {

    @SerializedName("lapTime")
    long lapTime;

    @SerializedName("lapDisplay")
    String lapDisplay;

    public long getLapTime() {
        return lapTime;
    }

    public void setLapTime(long lapTime) {
        this.lapTime = lapTime;
    }

    public String getLapDisplay() {
        return lapDisplay;
    }

    public void setLapDisplay(String lapDisplay) {
        this.lapDisplay = lapDisplay;
    }
}
