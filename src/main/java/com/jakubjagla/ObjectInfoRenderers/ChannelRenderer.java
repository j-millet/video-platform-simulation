/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.ObjectInfoRenderers;

import com.jakubjagla.Simulation.Channel;
import com.jakubjagla.Simulation.User;
import com.jakubjagla.Simulation.Video;

/**
 *
 * @author Jake
 */
public class ChannelRenderer extends UserRenderer {
    public ChannelRenderer(Object o){
        super(o);
    }
    
    @Override
    public String renderObject(int width){
        Channel toRenderChannel = (Channel) this.toRender;
        String divider = this.getDividerString().repeat( (int)(width*0.9/this.divStringWidth) )+"\n";
        String retstring = super.renderObject(width);
        if (toRenderChannel.getSubscribers().size() > 0){
            retstring += divider;
            retstring += "Subscribers\n――――――――――\n";
            for(User u:toRenderChannel.getSubscribers()){
                retstring += String.format("● %s\n", u.getUsername());
            }
            
        }
        if (toRenderChannel.getVideos().size() > 0){
            retstring += divider;
            retstring += "Videos\n―――――――\n";
            for(Video v:toRenderChannel.getVideos()){
                retstring += String.format("● %s\n", v.getName());
            }
            
        }
        return retstring;
    }
}
