/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.ObjectInfoRenderers;

import com.jakubjagla.Simulation.Channel;
import com.jakubjagla.Simulation.SimUtils;
import com.jakubjagla.Simulation.User;
import com.jakubjagla.Simulation.Watchable;
import java.util.Date;

/**
 *
 * @author Jake
 */
public class UserRenderer extends ObjectInfoRenderer{
    public UserRenderer (Object o){
        super(o);
    }
    @Override
    public String renderObject(int width){
        User toRenderUser = (User) this.toRender;
        String divider = this.getDividerString().repeat( (int)(width*0.9/this.divStringWidth) )+"\n";
        String retstring = String.format("\nUsername: %s\n\n"
                        + "Date joined: %s\n\n"
                        + "Currently watching: %s\n"
                        + divider
                        + "Queue\n――――――\n",   
                toRenderUser.getUsername(),
                SimUtils.getDateStringFromTimestamp(toRenderUser.getJoinDateTimestamp()),
                toRenderUser.getCurrentlyWatching().getName()
        );
        for(Watchable w:toRenderUser.getQueue()){
            retstring += String.format("● %s\n", w.getName());
        }
        
        if (toRenderUser.getSubscriptions().size() > 0){
            retstring += divider;
            retstring += "Following\n―――――――――\n";
            for(Channel c:toRenderUser.getSubscriptions()){
                retstring += String.format("● %s\n", c.getUsername());
            }
            
        }
        return retstring;
    }
}
