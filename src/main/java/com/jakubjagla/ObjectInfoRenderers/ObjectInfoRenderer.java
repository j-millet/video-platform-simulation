/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.ObjectInfoRenderers;

/**
 *
 * @author Jake
 */
public class ObjectInfoRenderer {
    Object toRender;
    private String divString;
    protected int divStringWidth;
    
    /**
     * 
     * @param toRender Object assigned for rendering;
     */
    public ObjectInfoRenderer(Object toRender){
        this.toRender = toRender;
        this.divString = " ";
        this.divStringWidth = 1;
    }
    
    /**
     * Sets the string used as a divider in rendering the object info.
     * @param div Divider string.
     * @param pixelStringWidth The length in pixels of the divider string (For the font which will be used by the receiving element).
     */
    public void setDividerString(String div, int pixelStringWidth){
        this.divString = div;
        this.divStringWidth = pixelStringWidth;
    }
    public String getDividerString(){
        return this.divString;
    }
    
    /**
     * Renders info about assigned object as plain text.
     * @param width Width (in pixels) of text needed.
     * @return Object info text less or equal the width specified.
     */
    public String renderObject(int width){
        return "\nNothing to render";
    }
}
