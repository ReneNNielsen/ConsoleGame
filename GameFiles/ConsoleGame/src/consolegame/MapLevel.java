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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class MapLevel {
    
    private String map;
    private List<NPC> npcs = new ArrayList<NPC>();
    private Point playerStartPosition;
    
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
        map = theLevel;
        mapObjectsSearch();
    }
      
    public String getMap()
    {
        return map;
    }
    
    public Point getStartPosition()
    {
        return playerStartPosition;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }        
    
    private void mapObjectsSearch()
    {
        Scanner npcSearch = new Scanner(map);
        int x = 0;  // vandret
        int y = 0;  // lodret
        char[] mobs = {'1','2','3','4','5','6','7','8','9'};
        char[] player = {'*'};
        
        while(npcSearch.hasNextLine())
        {            
            String currentLine = npcSearch.nextLine();
            //System.out.println(currentLine);
            if(currentLine.matches(".*\\d.*"))
            {
                for (int i = 0; i < currentLine.length(); i++) {
                    char character = currentLine.charAt(i);
                    if(isThereChar(mobs, character))
                    {
                        NPC monster = new NPC(character);
                        monster.setPosition(new Point(x, y));
                        npcs.add(monster);
                        //System.out.println(" -> X: " + x + " Y: " + y);
                    }
                    else if(isThereChar(player, character))
                    {
                        playerStartPosition = new Point(x,y);
                    }                    
                    y++;
                }
            }
            x++;
        }
    }
    
    private boolean isThereChar(char[] chaArray, char chr){
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
