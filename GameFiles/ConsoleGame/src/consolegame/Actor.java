package consolegame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author René & Daniel
 */
public class Actor 
{   
    protected String name;
    protected int health;
    protected int level;
    protected List<Skill> skills = new ArrayList<>();
    protected Point position;

    /**
     * Gets the position of the actor
     * @return position: Point
     */
    public Point getPosition() 
    {
        return position;
    }

    /**
     * Gets the health of the actor
     * @return int: health
     */    
    public int getHealth() 
    {
        return health;
    }

    /**
     * Gets the level of the actor
     * @return int: level
     */    
    public int getLevel() 
    {
        return level;
    }

    /**
     * Gets the name of the actor
     * @return String: name
     */    
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the list of skills of the actor
     * @return List<skills>: skills
     */    
    public List<Skill> getSkills() 
    {
        return skills;
    }
    
    /**
     * Sets the actors position
     * @param position: Point
     */
    public void setPosition(Point position) 
    {
        this.position = position;
    }
    
    /**
     * Sets the actors health
     * @param health: int
     */
    public void setHealth(int health) 
    {
        this.health = health;
    }

    /**
     * Sets the actors level
     * @param level: int
     */
    public void setLevel(int level) 
    {
        this.level = level;
    }

    /**
     * Sets the actors name
     * @param name: String
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Sets the actors skills
     * @param skills: List<Skill>
     */
    public void setSkills(List<Skill> skills) 
    {
        this.skills = skills;
    }      
    
    /**
     * Gets the base damage of the skill indicated by the provided index parameter. This method also psuedo randomly varies in a 
     * margin of 5%, so that the returned damage can vary within that margin.
     * @param skill: int
     * @return double: calculatedDmg
     */
    public double getSkillDamage(int skill)
    {
        double baseDmg = skills.get(skill).damage;
        // Dmg can vary a fixed percentage from basedmg: 5%
        double variationPercentage = (baseDmg / 100) * 5;        
        Random rand = new Random();
        double diffBaseDmg = (baseDmg-variationPercentage) + ((baseDmg+variationPercentage) - (baseDmg-variationPercentage)) * rand.nextDouble();
        double calculatedDmg;
        if(level > 1)
        {
            return calculatedDmg = diffBaseDmg * (level / 1.5);
        }
        else
        {
            return calculatedDmg = diffBaseDmg;
        }        
    }

    /**
     * Gets a psuedo random skill from the collection of skills
     * @return Skill: randomSkill
     */    
    public Skill getRandomSkill() {   
        int randomNumber = new Random().nextInt(7);
        Skill randomSkill;
        switch (randomNumber) {
            case 1:
                randomSkill = new SkillBite();
            break;
            case 2:
                randomSkill = new SkillFart();
            break;
            case 3:
                randomSkill = new SkillKick();
            break;
            case 4:
                randomSkill = new SkillPunch();               
            break;                
            case 5:
                randomSkill = new SkillScratch();
            break;                
            case 6:
                randomSkill = new SkillSlap();
            break;
            case 7:
                randomSkill = new SkillUppercut();
            break;
            default:
                randomSkill = new SkillPunch();
            break;   
        }
        for (Skill skill : skills) {
            if (skill.name == randomSkill.name) {
                randomSkill = getRandomSkill();
            }
        }
        return randomSkill;
    }
}
