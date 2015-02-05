package consolegame;

import java.util.Random;

/**
 *
 * @author René & Daniel
 */
public class Skill 
{
    protected String name;
    protected double damage;
    protected int missChance;
    protected int critChance;

    /**
     * Get the name of the skill
     * @return String: name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Get the damage of the skill
     * @return double: damage
     */
    public double getDamage() 
    {
        return damage;
    }

    /**
     * Get the missChance of the skill
     * @return int: missChance
     */
    public int getMissChance() 
    {
        return missChance;
    }

    /**
     * Get the critChance of the skill
     * @return int: critChance
     */
    public int getCritChance() 
    {
        return critChance;
    }     
}
