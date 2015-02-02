/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.util.List;

/**
 *
 * @author Bruger
 */
public class MapLevel {
    
    protected String map;
    protected List<NPC> npcs;
    
    public void loadMap(String filename)
    {
        
    }
    
    public String getMap()
    {
        return map;
    }
    
    public Point getStartPosition()
    {
        return new Point(0,0);
    }
    
}
