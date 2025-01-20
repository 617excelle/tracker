package com.atows.collectiontracker.xptracker;

import java.util.Date;

public class XpTracker {

    private Date startDate;
    private double startXp;
    private Date currDate ;
    private double currXp;

    public XpTracker(Date startDate, double startXp){
        this.startDate = startDate;
        this.startXp = startXp;
    }
    public double getXpPerHour(){
        return currXp - startXp ;
    }

    public long getTimeSinceStart(){
        return currDate.getTime() - startDate.getTime();
    }



}
