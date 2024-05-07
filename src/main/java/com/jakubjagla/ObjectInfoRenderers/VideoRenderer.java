/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.ObjectInfoRenderers;

import com.jakubjagla.Simulation.SimUtils;
import com.jakubjagla.Simulation.Video;
import java.util.Date;

/**
 *
 * @author Jake
 */
public class VideoRenderer extends ObjectInfoRenderer {
    public VideoRenderer (Object o){
        super(o);
    }
    
    public String renderObject(int width){
        Video toRenderVideo = (Video)this.toRender;
        String divider = this.getDividerString().repeat( (int)(width*0.9/this.divStringWidth) )+"\n";
        return  String.format(
                "\n%s\n"
                + "By %s\n"
                +divider
                + "Views: %d     Likes: %d     %s video\n"
                +divider
                + "Description:\n―――――――――――\n%s\n"
                +divider
                + "Date created: %s",
                toRenderVideo.getName(),
                toRenderVideo.getCreator().getUsername(),
                toRenderVideo.getNumViews(),
                toRenderVideo.getLikesAmount(),
                toRenderVideo.isPremium() ? "Premium" : "Free",
                toRenderVideo.getDescription(),
                SimUtils.getDateStringFromTimestamp(toRenderVideo.getUploadDateTimestamp())
        );
    }
}
