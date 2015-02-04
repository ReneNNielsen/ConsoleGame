/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.util.Random;

/**
 *
 * @author Bruger
 */
public class Skill 
{
    protected String name;
    protected double damage;
    protected int missChance;
    protected int warmupRounds;

    public Skill() 
    {
        
    }
    
    public String getName() 
    {
        return name;
    }

    public double getDamage() 
    {
        return damage;
    }

    public int getMissChance() 
    {
        return missChance;
    }

    public int getWarmupRounds() 
    {
        return warmupRounds;
    }     
}
