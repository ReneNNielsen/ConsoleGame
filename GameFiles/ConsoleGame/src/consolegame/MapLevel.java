/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class MapLevel 
{
    private String map;
    private List<NPC> npcs = new ArrayList<NPC>();
    private Point playerStartPosition;
    
    /**
     * Loads the map from filename inside this class' map variable. It also creates a list of NPCs and their position 
     * in the npcs variable.
     * @param String 
     */
    public void loadMap(String fileName)
    {
               
        File file = new File(fileName);       
        String theLevel = "";
        try 
        {
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine() + "\n";
                theLevel += line;
            }
            sc.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }                
        theLevel = theLevel.replaceAll("\t", "    ");
        map = theLevel;
        mapObjectsSearch();
    }
      
    /**
     * Gets the map and returns it as a string
     * @return String
     */
    public String getMap()
    {
        return map;
    }
    
    /**
     * Gets and returns the players start position in the map as Point(x,y).
     * @return Point
     */
    public Point getStartPosition()
    {
        return playerStartPosition;
    }

    /**
     * Gets and returns the npc List from the map.
     * @return List
     */
    public List<NPC> getNpcs() 
    {
        return npcs;
    }        
    
    /**
     * Removes a single NPC from the map. It is replaced by a whitespace.
     * @param position 
     */
    public void killNpc(NPC npc)
    {
        int lineNum = 0;
        String theLevel = "";
        try 
        {
            Scanner npcSearch = new Scanner(map);
            while (npcSearch.hasNextLine()) {   
                String currentLine = npcSearch.nextLine();
                if(lineNum == npc.getPosition().y)
                {
                    currentLine = currentLine.substring(0, npc.getPosition().x) + " " + currentLine.substring(npc.getPosition().x + 1);
                }
                theLevel += currentLine + "\n";
                lineNum++;
            }
            npcSearch.close();
            map = theLevel;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        npcs.remove(npc);
    }
    
    // ------------
    // - Privates -
    // ------------
    
    /**
     * Private method that searches the map for different object types like npcs and players startposition
     */
    private void mapObjectsSearch()
    {
        Scanner npcSearch = new Scanner(map);
        int x = 0;  // vandret
        int y = 0;  // lodret
        char[] mobs = {'1','2','3','4','5','6','7','8','9'};
        char[] player = {'*'};    
        while(npcSearch.hasNextLine())
        {          
            x = 0;
            String currentLine = npcSearch.nextLine();
            //System.out.println(currentLine);
            if(currentLine.matches(".*\\d.*"))
            {
                for (int i = 0; i < currentLine.length(); i++) {
                    char character = currentLine.charAt(i);
                    if(isThereChar(mobs, character))
                    {
                        NPC monster = new NPC(Character.getNumericValue(character));
                        monster.setPosition(new Point(x, y));
                        npcs.add(monster);
                        //System.out.println(" -> X: " + x + " Y: " + y);
                    }
                    else if(isThereChar(player, character))
                    {
                        playerStartPosition = new Point(x,y);
                    }                    
                    x++;
                }
            }
            y++;
        }
        removeObjectsFromMap();
    }
    
    /**
     * Private method that replaces the NPC in the map with M. The player is completely 
     * removed but position is saved and used for drawing elsewhere.
     * @param x
     * @param y 
     */
    private void removeObjectsFromMap()
    {        
        map = map.replaceAll("[1-9]", "M");
        map = map.replaceAll("[*]", " ");
    }
    
    /**
     * Checks if the character in chr is inside the chaArray, and returns true if it is found.
     * @param chaArray
     * @param chr
     * @return boolean
     */
    private boolean isThereChar(char[] chaArray, char chr)
    {
        boolean bool = false;
        for(int i=0; i < chaArray.length; i++) {
            if(chr==chaArray[i])
            {
                 bool = true;
            }
        }
    return bool;        
    }    
}
