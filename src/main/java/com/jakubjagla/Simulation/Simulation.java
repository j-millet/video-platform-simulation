/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jake
 */
public class Simulation extends Thread implements Serializable {

    private volatile boolean running = false;
    private boolean started = false;
    private volatile boolean paused = false;

    private final int defaultMilisToWait = 50;

    private double speed = 1;

    private ArrayList<User> users;
    private ArrayList<Channel> channels;
    

    public Simulation() {
        this.users = new ArrayList<>();
        this.channels = new ArrayList<>();
        this.generateChannels();
        this.generateUsers();
    }

    
    @Override
    public void run() {
        this.startUsers();
        while (running) {
            try {
                Thread.sleep(this.getMilisToWait());
            } catch (InterruptedException ex) {
            }
            if (!paused && running) {
                this.getRandomUserToSubscribeToRandomChannel();
            }
        }

    }
    
    /**
     * Pause the simulation
     */
    public synchronized void pause() {
        paused = true;
    }
    /**
     * Unpause the simulation
     */
    public synchronized void unpause() {
        paused = false;
    }
    
    /**
     * Stop the simulation. (Will no longer be able to run as a {@link java.lang.Thread} can be only started once.)
     */
    public void stopSimulation() {
        if (!this.isAlive() && !running) {
            return;
        }
        System.out.println("Change the world\nMy final message\nGoodbye!");
        running = false;

        for (User u : users) {
            u.interrupt();
        }
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Starts the simulation and the simulation's collection of {@link com.jakubjagla.Simulation.User} threads.
     */
    public void startSimulation() {
        if (started) {
            System.out.println("The simulation was already started once.\n");
            return;
        }
        System.out.println("Wake up samurai\n");
        running = true;
        started = true;
        paused = false;
        this.start();
    }
    
    /**
     * Saves simulation to a file.
     * @param toSave The simulation object to save.
     * @param filename The path to the file.
     * @throws IOException
     */
    public static void SaveSimulation(Simulation toSave, String filename) throws IOException {
        boolean wasPaused = toSave.isPaused();
        toSave.pause();
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            out.writeObject(toSave);
        }catch(Exception ex){
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println(ex.getMessage());
        }
        if (!wasPaused) {
            toSave.unpause();
        }
    }
    
    /**
     * Creates a new simulation instance with the state loaded from the file.
     * @param filename The path to the file.
     * @return A new simulation instance
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Simulation LoadSimulation(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        Simulation s;
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            s = (Simulation) in.readObject();
            s.started = false;
            s.setSpeed(1.0);
        }
        return s;
    }
    
    /**
     * Sets the speed of the simulation.
     * @param speed 
     */
    public synchronized void setSpeed(double speed) {
        this.speed = speed;
        System.out.println(this.speed);
    }
    
    /**
     * Returns the miliseconds to wait, based on speed and the default value to wait.
     * @return 
     */
    public long getMilisToWait() {
        return Math.round(this.defaultMilisToWait / this.speed);
    }

    private void generateUsers() {
        for (int i = 0; i < 2000; i++) {
            Random r = new Random();
            long randomJoinDate = System.currentTimeMillis() - Math.abs(r.nextLong() % (long) 631152000000.0); //can go 20 years to the past
            users.add(new User(
                    this,
                    String.format("Just a user #%d", i),
                    SimUtils.getRandomProfilePictureURI(),
                    randomJoinDate,
                    (r.nextFloat() < 0.5)));
        }
    }

    private void generateChannels(){
        Channel c;
        //Courtesy of chatGPT
        String[] cnames = {
            "QuantumQuest",
            "PixelPioneer",
            "StellarSnippets",
            "TechnoTraverse",
            "CosmicCraze",
            "EchoEssence",
            "SerenitySpectacle",
            "RogueRendezvous",
            "ZenithZest",
            "GravityGrove",
            "QuantumQuasar",
            "NebulaNexus",
            "ElysianEcho",
            "FluxFusion",
            "VortexVoyage",
            "SynergySphere",
            "VelocityVista",
            "LuminousLabyrinth",
            "EtherealEchoes",
            "CipherChronicle",
            "DynamoDive",
            "HorizonHarbor",
            "SpectrumSafari",
            "EchoEnigma",
            "NovaNectar",
            "PixelPulse",
            "CelestialCipher",
            "QuantumQuill",
            "SonicSculptor",
            "InfinityInsight",
            "MarketPlier",
            "MultiVideoGamePlayAndCommentGuyDudePerson",
        };
        for (String s : cnames) {
            Random r = new Random();
            long randomJoinDate = System.currentTimeMillis() - Math.abs(r.nextLong() % (long) 631152000000.0); //can go 20 years to the past
            c = new Channel(
                    this,
                    s,
                    SimUtils.getRandomProfilePictureURI(),
                    randomJoinDate
            );
            users.add(c);
            channels.add(c);
        }
    }

    private void startUsers() {
        for (User u : users) {
            u.start();
        }
    }

    private void getRandomUserToSubscribeToRandomChannel() {
        float r = ThreadLocalRandom.current().nextFloat();
        if (r < 0.4) {
            User u = SimUtils.getRandomElementFromArray(this.users);
            Channel c = SimUtils.getRandomElementFromArray(this.channels);
            if (u != c){
                u.subscribeToChannel(c);
            }
        }
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    public synchronized ArrayList<Watchable> getAllWatchables() {
        ArrayList<Watchable> retList = new ArrayList<Watchable>();
        for (Channel c : channels) {
            retList.addAll(c.getVideos());
            if (c.getStream() != null) {
                retList.add(c.getStream());
            }
        }
        return retList;
    }
    
    public synchronized ArrayList<Watchable> getFreeWatchables() {
        ArrayList<Watchable> retList = new ArrayList<Watchable>();
        for (Channel c : channels) {
            for (Video v: c.getVideos()){
                if(v.isPremium()){continue;}
                retList.add(v);
            }
            if (c.getStream() != null) {
                retList.add(c.getStream());
            }
        }
        return retList;
    }

    public synchronized Watchable getRandomWatchable() {
        ArrayList<Watchable> l = this.getAllWatchables();
        if (l.size() < 1) {
            return null;
        }
        int randIdx = ThreadLocalRandom.current().nextInt(l.size()) % l.size();
        return l.get(randIdx);
    }
    
    public synchronized Watchable getRandomFreeWatchable() {
        ArrayList<Watchable> l = this.getFreeWatchables();
        if (l.size() < 1) {
            return null;
        }
        int randIdx = ThreadLocalRandom.current().nextInt(l.size()) % l.size();
        return l.get(randIdx);
    }

    public synchronized ArrayList<User> getUsers() {
        return this.users;
    }

    public synchronized ArrayList<Channel> getChannels() {
        synchronized(channels){
            return channels;
        }
    }
}