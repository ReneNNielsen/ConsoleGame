/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;

/**
 *
 * @author Bruger
 */
public class Player extends Actor {
    
    protected int xp;
    
    //private int[] levelsAtXp = { 50 , 100 , 200 , 400 , 800 , 1600 , 3200 , 6400 };

    public void checkIfNewLevel() {
        int xpToNextLvl = 50;
        for(int lvl = 1; lvl < level; lvl++)
        {
            xpToNextLvl = xpToNextLvl * 2;
        }
        if(xpToNextLvl > xp)
        {
            level = level + 1;
        }        
    }
    
    public int getXp() {
        return xp;
    }
    
    public void addXp(int moreXp) {
        xp += moreXp;
        checkIfNewLevel();
    }
    
    
}
