/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jake
 */
public class Stream extends Watchable {

    private ArrayList<User> viewers;
    private long startTimestamp;
    
    private String streamId;
    private long onlineTime;
    
    private long endTimestamp;

    public Stream(String name, String description, String thumbnailUri, String streamId, Channel creator) {
        super(thumbnailUri, name, description, creator);
        this.viewers = new ArrayList<User>();
        this.startTimestamp = System.currentTimeMillis();
        this.streamId = streamId;
        this.onlineTime = 0;
    }
    
    /**
     * As a stream has no predefined length, return a random value between 1 and 50 for the {@link com.jakubjagla.Simulation.User} to 'watch' the stream for.
     * @return 
     */
    @Override
    public int getDurationSeconds() {
        Random rand = new Random();
        return rand.nextInt(49) + 1;
    }
    
    /**
     * Adds the user to the list of accounts watching the stream.
     * @param u The user to add.
     */
    public synchronized void joinStream(User u) {
        if (!viewers.contains(u)) {
            viewers.add(u);
        }
    }

    /**
     * Removes the user from the list of accounts watching the stream.
     * @param u The user to remove.
     */
    public synchronized void leaveStream(User u) {
        if (viewers.contains(u)) {
            viewers.remove(u);
        }
    }

    @Override
    public synchronized int getNumViews() {
        return viewers.size();
    }
    
    public long getStartTimestamp(){
        return startTimestamp;
    }
    
    /**
     * Ends the stream. (Makes it no longer available)
     */
    public synchronized void end(){
        this.available = false;
        this.endTimestamp = System.currentTimeMillis();
    }
    
    public synchronized long getOnlineTime(){
        return this.onlineTime;
    }
    
    /**
     * Adds online time to the stream.
     * @param amount The amount of time to add.
     */
    public synchronized void addOnlineTime(long amount){
        this.onlineTime += amount;
    }

}
