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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.applet.Main;

/**
 *
 * @author Bruger
 */
public class Game 
{
    private List<MapLevel> mapList = new ArrayList<MapLevel>();
    private Player player;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int mapNumber;
    private static String applicationDir = "";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Game game = new Game();
        game.addMaps();
        clearConsole();
        game.doGame();
    }
    
    public static void brClear()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        
    }
    
    private void addMaps()
    {
        applicationDir = getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " ");
        if (applicationDir.contains(".")) {
            applicationDir = applicationDir.substring(0,applicationDir.lastIndexOf('/'));
        }
        
        String levelsPath = applicationDir;
        levelsPath += "/levels/";
        File folder = new File(levelsPath);
        System.out.println("Path: " + levelsPath);
        for (File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
            MapLevel map = new MapLevel();
            map.loadMap(levelsPath + fileEntry.getName());
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
            for (int i = 0; i < 15; i++) 
            {
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
                
                switch (next) 
                {
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
                if (!writeMap()) 
                {
                    if (mapNumber >= mapList.size()) 
                    {
                        runGame = false;
                        clearConsole();
                        System.out.println("   You won the Game!");
                        System.out.println("------------------------");
                        System.out.println("You are TOTALY AWESOME");
                        System.out.println("no one can beat you!");
                        System.out.println("------------------------");
                        System.out.println("YOU ARE THE ONE AND ONLY!!");
                        System.out.println("NOBODY CAN'T TAKE THAT AWAY FROM YOU!!!!!!");
                        File file = new File(applicationDir + "I_am-the_one_and_only.wav");
                        try 
                        {
                            playClip(file);
                        } 
                        catch (Exception e) 
                        {
                        }
                        continue;
                    }
                    point.x = x;
                    point.y = y;
                    player.setPosition(point);
                    clearConsole();
                    writeMap();
                }
            }
        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void initGame() throws IOException
    {
        System.out.println("How to use The Game:");
        System.out.println("You are the *(star) and the monsters is the M.");
        System.out.println("You can gain xp/level by killing theis monsters.");
        System.out.println("To go to  another map level you have to go into $");
        System.out.println("and you will sporn in a new map.");
        System.out.println("For quiting the game enter quit (not avaible in combat mode).");
        System.out.println("You need to press enter to aplay all commands");
        System.out.println("------------------------------");
        
        System.out.println("To move around in the map use:");
        System.out.println("    'a' for left");
        System.out.println("    'd' for right");
        System.out.println("    'w' for up");
        System.out.println("    's' for down");
        System.out.println("------------------------------");
        
        System.out.println("Combat mode:");
        System.out.println("To win you will have to kill the monster with your skills");
        System.out.println("Combat mode is turn based, you will hit first then the monster");
        System.out.println("hits you and that will go on intil one of you is dead.");
        System.out.println("Use your skills by clicking its number and press enter.");
        System.out.println("If you can see that you cant kill the monster you can run");
        System.out.println("by typing run and then hit enter");
        
        System.out.println("------------------------------");
        System.out.println("---------Game started---------");
        System.out.println("------------------------------");
        
        System.out.println("Enter Name:");
        String name = br.readLine();
        
        player = new Player();
        player.name = name;
        player.health = 100;
        player.xp = 0;
        player.position = mapList.get(0).getStartPosition();
        player.skills.add(new SkillSlap());
        mapNumber = 0;
    }
    
    private boolean writeMap()
    {
        MapLevel map = mapList.get(mapNumber);
        for (NPC npc : map.getNpcs()) 
        {
            if (npc.position.x == player.position.x && npc.position.y == player.position.y) 
            {
                File battleFile = new File(applicationDir + "Battle_Music.wav");
                try 
                {
                    playClip(battleFile);
                } 
                catch (Exception e) 
                {
                }
                clearConsole();
                Combat combat = new Combat(player, npc);
                boolean didWin = combat.doCombat();
                if (didWin) 
                {
                    player.addXp(npc.xpGive);
                    map.killNpc(npc);
                    break;
                }
                else
                {
                    return false;
                }
            }
        }
        clearConsole();
        Scanner mapContainer = new Scanner(map.getMap());
        int y = 0;
        System.out.println("Player: " + player.getName() + "  level: " + player.getLevel() + "  xp: " + player.getXp() + "/"+ player.getXpToNextLevel());
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
                    mapNumber++;
                    if (mapNumber >= mapList.size()) {
                        return false;
                    }
                    player.setPosition(mapList.get(mapNumber).getStartPosition());
                    clearConsole();
                    writeMap();
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
    
    public static synchronized void playSound(final String url) 
    {
        try 
        {
          Clip clip = AudioSystem.getClip();
          
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(applicationDir + url));
          clip.open(inputStream);
          clip.start(); 
        } 
        catch (Exception e) 
        {
          System.err.println(e.getMessage());
        }
    }
    private static void playClip(File clipFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException 
    {
        class AudioListener implements LineListener 
        {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) 
            {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) 
                {
                  done = true;
                  notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException 
            {
                while (!done) 
                { 
                    wait(); 
                }
            }
        }
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try 
        {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try 
            {
              clip.start();
              listener.waitUntilDone();
            } finally 
            {
              clip.close();
            }
        } 
        finally 
        {
          audioInputStream.close();
        }
    }
    
}
