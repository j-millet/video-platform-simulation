/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jake
 */
public class Channel extends User {
    
    private ArrayList<User> subscribers;
    private ArrayList<Video> videos;
    private volatile Stream stream;
    
    public Channel(Simulation parent, String name, String profilePicUri, long joinDate) {
        super(parent, name, profilePicUri,joinDate, true); //all channels are premium users, as the almighty algorithm is thankful for new content \o/
        subscribers = new ArrayList<User>();
        videos = new ArrayList<Video>();
        stream = null;
    }
    
    /**
     * A single step of the channels's behavior. User behavior -> (Add to queue, watch, like) and additionally posting a new video and/or starting/ending a new stream.
     */
    @Override
    protected void step() {

        float r = ThreadLocalRandom.current().nextFloat();
        if (r < 0.001) {
            this.publishVideo();
        }
        r = ThreadLocalRandom.current().nextFloat();
        if (r < 0.01) {
            if (stream == null) {
                this.startStream();
            } else {
                this.endStream();
            }
        }
        if(stream != null){
            this.stream.addOnlineTime(1);
        }
        super.step();
    }

    
    
    /**
     * Adds the user to the list of subscribers if not already present. (Only if the user has this channel in their list of followed channels).
     * @param u The user to be added.
     */
    public synchronized void addSubscriber(User u) {
        if (!subscribers.contains(u) && u.getSubscriptions().contains(this)) {
            subscribers.add(u);
        }
    }
    
    /**
     * Adds a new randomly generated video to the channel's list of videos. See {@link com.jakubjagla.Simulation.Video}
     */
    public synchronized void publishVideo() {
        Video v = Video.generateRandomVideoForChannel(this);
        this.videos.add(v);
        for (User u : subscribers) {
            u.getNotification(v);
        }
    }
    
    /**
     * Creates a new {@link com.jakubjagla.Simulation.Stream} object assigned to the channel.
     */
    public synchronized void startStream() {
        this.stream = new Stream(String.format("%s's stream", this.getUsername()), 
                String.format("Ultra giga pogchamp stream amirite chat? Remember to follow, give bits, donate and raid!\nDonations: https://www.give-me-your-money.net/%s",this.getUsername()),
                SimUtils.getRandomThumbnailURI(),
                String.format("channel/%s/stream", this.getUsername()),
                this);
        for (User u : subscribers) {
            u.getNotification(this.stream);
        }
    }
    
    /**
     * Ends the channel's {@link com.jakubjagla.Simulation.Stream}
     */
    public synchronized void endStream() {
        this.stream.end();
        this.stream = null;
    }
    
    public synchronized ArrayList<User> getSubscribers() {
        return subscribers;
    }
    
    public synchronized ArrayList<Video> getVideos() {
        return videos;
    }

    public synchronized Stream getStream() {
        return stream;
    }
    
    
}
