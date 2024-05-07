/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public class Watchable implements Serializable {
    
    private String name;
    private String description;
    
    private String thumbnailUri;
    
    private int likesAmount;
    private ArrayList<User> likesFrom;
    
    private Channel creator;
    
    protected boolean available;

    public Watchable(String thumbnailUri, String name, String description, Channel creator) {
        this.thumbnailUri = thumbnailUri;
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.likesFrom = new ArrayList<User>();
        this.available = true;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public Channel getCreator() {
        return creator;
    }
    
    public boolean isAvailable(){
        return available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Add like from User u. One like per User will be added.
     * @param u 
     */
    public synchronized void addLike(User u) {
        if (!this.likesFrom.contains(u)) {
            this.likesFrom.add(u);
            this.likesAmount += 1;
        }
    }
    
    /**
     * Remove like from User u. One like per User will be removed.
     * @param u 
     */
    public synchronized void removeLike(User u) {
        if (this.likesFrom.contains(u)) {
            this.likesFrom.remove(u);
            this.likesAmount -= 1;
        }
    }

    public int getDurationSeconds() {
        return 0;
    }

    protected synchronized void addView(User u) {
        return;
    }

    public synchronized int getNumViews() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
