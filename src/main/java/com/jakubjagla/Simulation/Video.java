/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jake
 */
public class Video extends Watchable {
    private int durationSeconds;
    private volatile int numViews = 0;
    private long uploadDateTimestamp;
    private String contentId;
    private boolean premium;
    
    
    public Video(String name, String description,String thumbnailUri,String contentId, int duration, boolean premium,Channel creator) {
        super(thumbnailUri, name, description, creator);
        this.durationSeconds = duration;
        this.uploadDateTimestamp = System.currentTimeMillis();
        this.contentId = contentId;
        this.premium = premium;
    }
    
    @Override
    public int getDurationSeconds() {
        return this.durationSeconds;
    }
    
    /**
     * Increments the view counter.
     */
    public synchronized void addView(){
        this.numViews += 1;
    }
    
    @Override
    public synchronized int getNumViews(){
        return this.numViews;
    }

    public long getUploadDateTimestamp() {
        return uploadDateTimestamp;
    }

    public String getContentId() {
        return contentId;
    }

    public boolean isPremium() {
        return premium;
    }
    
    /**
     * Generates a new random video with Channel c as the owner.
     * @param c
     * @return 
     */
    public static Video generateRandomVideoForChannel(Channel c){
        String title = SimUtils.getRandomTitle();
        Random r = new Random();
        return new Video(title,
                SimUtils.getVideoDescription(),
                SimUtils.getRandomThumbnailURI(),
                String.format("channel/%s/videos/%s", c.getUsername(),title),
                r.nextInt(40)+10,
                (ThreadLocalRandom.current().nextFloat()<0.1)?true:false,
                c);
    }
}
