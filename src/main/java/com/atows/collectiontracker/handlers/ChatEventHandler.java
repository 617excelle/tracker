package com.atows.collectiontracker.handlers;

import com.atows.collectiontracker.util.Observer;
import com.atows.collectiontracker.util.Subject;
import com.atows.collectiontracker.xptracker.XpTracker;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatEventHandler implements Subject {
    private Set<Observer> observers = new HashSet<>();
    private boolean turnedOn;
    private XpTracker xpTracker;
    private double xp;
    private String skill;
    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent e) {
        if (e.type != 2) {
            return;
        }


        String message = e.message.getUnformattedText();
        System.out.println("Raw Message: " + message);
        String regex = "§3\\+([\\d.]+)\\s(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            
            xp = Double.parseDouble(matcher.group(1));
            skill = matcher.group(2);
            turnedOn = true;
            notifyObservers();
        } else {
            return;
        }
    }


    @Override
    public void addObserver(Observer o) {
        observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);

    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(xp, skill, turnedOn);
            //observer.update(10, "Farming", true);
        }
    }
}

