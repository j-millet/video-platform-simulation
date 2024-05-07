/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jake
 */
public class User extends Thread implements Serializable {

    private Simulation parentSimulation;
    private String username;
    private String profilePictureUri;
    private long joinDateTimestamp;
    private ArrayList<Channel> subscriptions;
    private boolean premium;
    private volatile Watchable currentlyWatching;
    private ArrayList<Watchable> queue;

    private int busyTimer = 0;

    public User(Simulation parent, String name, String profilePicUri, long joinDate,boolean premium) {
        this.parentSimulation = parent;
        this.username = name;
        this.profilePictureUri = profilePicUri;
        this.premium = premium;
        
        
        this.joinDateTimestamp = joinDate;
        queue = new ArrayList<Watchable>();
        subscriptions = new ArrayList<Channel>();
    }
    
    /**
     * User object main behavior loop.
     */
    @Override
    public void run() {
        while (parentSimulation.isRunning()) {
            if (!parentSimulation.isPaused()) {
                this.step();
            }
        }
    }
    
    /**
     * A single step of the user's behavior. (Add to queue, watch, like).
     */
    protected void step() {

        if (queue.size() < 2) {
            Watchable w = (this.isPremium())? parentSimulation.getRandomWatchable(): parentSimulation.getRandomFreeWatchable();
            if (w != null) {
                this.addToQueue(w);
            } else {
                return;
            }
        }

        if (busyTimer <= 0) {
            if(this.currentlyWatching != null){
                if(this.currentlyWatching.isAvailable() && ThreadLocalRandom.current().nextFloat() < 0.5){
                    this.currentlyWatching.addLike(this);
                }
                if (this.currentlyWatching instanceof Stream) {
                    ((Stream) this.currentlyWatching).leaveStream(this);
                }
            }
            
            this.currentlyWatching = queue.remove(0);
            busyTimer = this.currentlyWatching.getDurationSeconds();
            
            if (this.currentlyWatching instanceof Video) {
                ((Video) this.currentlyWatching).addView();
            }
            if (this.currentlyWatching instanceof Stream) {
                ((Stream) this.currentlyWatching).joinStream(this);
            }
        }
        if (!this.currentlyWatching.isAvailable()) {
            busyTimer = 0;
            return;
        }
        try {
            Thread.sleep(this.parentSimulation.getMilisToWait());
        } catch (InterruptedException ex) {
            //we handle the interrupt by doing nothing about it :>
        }
        busyTimer -= 1;
    }
    
    /**
     * Add a Watchable type object to the queue of content that the user will watch.
     * @param item Content piece to be added.
     */
    public synchronized void addToQueue(Watchable item) {
        if (!this.queue.contains(item)) {
            this.queue.add(item);
        }
    }
    
    /**
     * In theory would handle sending a push notification to the User. Implementation only adds the content piece to the User's queue.
     * @param item Content piece to be added.
     */
    public synchronized void getNotification(Watchable item) {
        this.addToQueue(item);
    }
    
    /**
     * Adds a given Channel to the list of followed channels if not already present. Also calls the Channels {@link com.jakubjagla.Simulation.Channel#addSubscriber(User)} method to achieve consistency.
     * @param c The channel to be followed.
     */
    public synchronized void subscribeToChannel(Channel c) {
        //System.out.println(String.format("Subscribing to %s", c.getUsername()));
        if (!this.subscriptions.contains(c)) {
            this.subscriptions.add(c);
        }
        c.addSubscriber(this);
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePictureUri() {
        return profilePictureUri;
    }

    public long getJoinDateTimestamp() {
        return joinDateTimestamp;
    }

    public synchronized ArrayList<Channel> getSubscriptions() {
        return subscriptions;
    }

    public boolean isPremium() {
        return premium;
    }

    public synchronized Watchable getCurrentlyWatching() {
        return currentlyWatching;
    }

    public synchronized ArrayList<Watchable> getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }
}
