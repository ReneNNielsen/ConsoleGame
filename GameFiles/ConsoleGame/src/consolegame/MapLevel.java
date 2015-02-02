/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class MapLevel {
    
    private String map;
    private List<NPC> npcs;
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
        mapNpcSearch();
    }
      
    public String getMap()
    {
        return map;
    }
    
    public Point getStartPosition()
    {
        return new Point(0,0);
    }

    public List<NPC> getNpcs() {
        return npcs;
    }        
    
    private void mapNpcSearch()
    {
        Scanner npcSearch = new Scanner(map);
        int linecount = 0;
        
        while(npcSearch.hasNextLine())
        {
            String currentLine = npcSearch.nextLine();
            if(currentLine.matches(".*\\d.*"))
            {
                /*
                for (int i = -1; (i = currentLine.indexOf(word, i + 1)) != -1; ) {
                    System.out.println(i);
                } */
                
                String numberHolder = currentLine.replaceAll("[^0-9]+", " ");
                System.out.println(currentLine + linecount + " -> " + Arrays.asList(numberHolder.trim().split(",")));
            }
            else
            {
                //System.out.println("True");
            }
            linecount++;
        }
    } 
    
}
