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
    private double calculation = 1.5;

    /**
     * Constructor for the player class. Sets the level to 1.
     */
    public Player()
    {
        level = 1;
    }       
    
    /**
     * Returns the players xp amount as an int
     * @return int
     */
    public int getXp() {
        return xp;
    }
    
    /**
     * Adds xp to the players xp amount. After it has been added it runs the private checkIfNewLevel() method, that 
     * levels the player up if it has reached the checkpoint for a new level.
     * @param moreXp 
     */
    
    public void addXp(int moreXp) {
        xp += moreXp;
        checkIfNewLevel();
    }
    
    // ------------
    // - Privates -
    // ------------    
    
    /**
     * Checks if the player is ready to proceed to the next level based on its xp amount. If the player is ready
     * its level is raised by one.
     */
    private void checkIfNewLevel() {
        int xpToNextLvl = 50; // Starts at 50 for the first level transition from lvl 1 to 2.
        for(int lvl = 1; lvl < level; lvl++)
        {
            xpToNextLvl = xpToNextLvl * 2;
        }
        if(xpToNextLvl < xp)
        {
            raiseStatsOneLvl();
        }        
    }
    
    /**
     * This method raises the other stats of the player coresponding its level.
     */
    private void raiseStatsOneLvl()
    {
        double multiplier = calculation * level;
        level = level + 1;
        health = (int) (40 * (int)multiplier);
    }
    
}
