/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bruger
 */
public class Game {

    private List<MapLevel> mapList = new ArrayList<MapLevel>();
    private Player player;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int mapNumber;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.addMaps();
        clearConsole();
        game.doGame();
    }
    
    private void addMaps()
    {
        String applicationDir = getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ") + "levels/";
        File folder = new File(applicationDir);
        System.out.println("Path: " + applicationDir);
        for (File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
            MapLevel map = new MapLevel();
            map.loadMap(applicationDir + fileEntry.getName());
            System.out.println(map.getMap());
            mapList.add(map);
        }
        System.out.println("Done");
    }
    public static void clearConsole()
    {
        try
        {
            String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            for (int i = 0; i < 15; i++) {
                System.out.println("");
            }
        }
        
    }
    
    private void doGame()
    {
        boolean runGame = true;
        try {
            initGame();
            writeMap();
            while (runGame) {
                Point point = player.getPosition();
                int x = point.x;
                int y = point.y;
                String next = br.readLine();
                clearConsole();
                System.out.println("Player: " + player.getName() + "  level: " + player.getLevel() + "  xp: " + player.xp);
                switch (next) {
                    case "a":
                        point.x--;
                        player.setPosition(point);
                        break;
                    case "d":
                        point.x++;
                        player.setPosition(point);
                        break;
                    case "w":
                        point.y--;
                        player.setPosition(point);
                        break;
                    case "s":
                        point.y++;
                        player.setPosition(point);
                        break;
                    case "quit":
                        runGame = false;
                        continue;
                }
                if (!writeMap()) {
                    point.x = x;
                    point.y = y;
                    player.setPosition(point);
                    writeMap();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void initGame() throws IOException
    {
        System.out.println("Enter Name:");
        String name = br.readLine();
        
        player = new Player();
        player.name = name;
        player.level = 0;
        player.health = 100;
        player.xp = 0;
        player.position = mapList.get(0).getStartPosition();
        player.skills.add(new SkillSlap());
        mapNumber = 0;
    }
    
    private boolean writeMap()
    {
        MapLevel map = mapList.get(mapNumber);
        
        for (NPC npc : map.getNpcs()) {
            if (npc.position == player.position) {
                Combat combat = new Combat(player, npc);
                boolean didWin = combat.doCombat();
                if (didWin) {
                    player.xp += npc.xpGive;
                }
                else
                {
                    return false;
                }
            }
        }
         
        Scanner mapContainer = new Scanner(map.getMap());
        int y = 0;
        
        while(mapContainer.hasNextLine())
        {
            String currentLine = mapContainer.nextLine();
            if (player.getPosition().y == y) 
            {
                String test =currentLine.substring(player.getPosition().x,player.getPosition().x+1);
                if (test.equals("#"))
                {
                    return false;
                }
                else if (currentLine.substring(player.getPosition().x,player.getPosition().x+1).equals("$"))
                {
                    System.out.println("NOT MADE YET");
                    System.out.println("NOT MADE YET");
                    System.out.println("NOT MADE YET");
                }
                System.out.print(currentLine.substring(0, player.getPosition().x));
                System.out.print("*");
                System.out.println(currentLine.substring(player.getPosition().x + 1));
            }
            
            else
            {
                System.out.println(currentLine);
            }
            y++;
        }
        return true;
    }
    
}
