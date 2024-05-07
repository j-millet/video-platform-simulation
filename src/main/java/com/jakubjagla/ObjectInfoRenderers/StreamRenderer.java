/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.ObjectInfoRenderers;

import com.jakubjagla.Simulation.SimUtils;
import com.jakubjagla.Simulation.Stream;
import java.util.Date;

/**
 *
 * @author Jake
 */
public class StreamRenderer extends ObjectInfoRenderer {
    public StreamRenderer (Object o){
        super(o);
    }
    @Override
    public String renderObject(int width){
        Stream toRenderStream = (Stream) this.toRender;
        String divider = this.getDividerString().repeat( (int)(width*0.9/this.divStringWidth) )+"\n";
        return String.format(
                        "\n%s\n"
                        + "By %s\n"
                        + "%s"
                        + divider
                        + "Viewers: %d      Likes: %d\n"
                        + "Online for: %d minutes\n"
                        + divider
                        + "Description:\n――――――――――\n %s\n"
                        + divider
                        + "Stream started: %s",
                toRenderStream.getName(),
                toRenderStream.getCreator().getUsername(),
                toRenderStream.isAvailable()? "":"Stream ended.\n",
                toRenderStream.getNumViews(),
                toRenderStream.getLikesAmount(),
                toRenderStream.getOnlineTime(),
                toRenderStream.getDescription(),
                SimUtils.getDateStringFromTimestamp(toRenderStream.getStartTimestamp())
        );
    }
}
