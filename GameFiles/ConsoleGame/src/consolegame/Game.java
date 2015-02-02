/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.io.File;
import java.util.List;

/**
 *
 * @author Bruger
 */
public class Game {

    private List<MapLevel> mapList;
    private Player player;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.addMaps();
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
}
