/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class MapLevel {
    
    protected String map;
    protected List<NPC> npcs;
    
    public void loadMap(String fileName)
    {
        String applicationDir = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); 
        String fileToLoad = applicationDir + "/levels/" + fileName + ".txt";        
        File file = new File(fileToLoad);       
        String theLevel = "";
        try 
        {
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                theLevel += line;
            }
            sc.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }                
        map = theLevel;
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
