/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.ObjectInfoRenderers;

import com.jakubjagla.Simulation.Channel;
import com.jakubjagla.Simulation.Stream;
import com.jakubjagla.Simulation.User;
import com.jakubjagla.Simulation.Video;

/**
 *
 * @author Jake
 */
public class ObjectInfoRendererFactory {
    /**
     * 
     *  Returns ObjectInfoRenderer subclass appropriate for toRender type or default ObjectInfoRenderer if type of toRender is not supported.
     */
    
    public static ObjectInfoRenderer getRenderer(Object toRender){
        if(toRender instanceof Video){
            return new VideoRenderer(toRender);
        }
        if(toRender instanceof Stream){
            return new StreamRenderer(toRender);
        }
        if(toRender instanceof Channel){
            return new ChannelRenderer(toRender);
        }
        if(toRender instanceof User){
            return new UserRenderer(toRender);
        }
        return new ObjectInfoRenderer(null);
    }
}
