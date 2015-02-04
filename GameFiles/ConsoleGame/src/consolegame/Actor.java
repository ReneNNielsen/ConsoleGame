/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bruger
 */
public class Actor {
    
    protected String name;
    protected int health;
    protected int level;
    protected List<Skill> skills = new ArrayList<>();
    protected Point position;

    // Gets
    
    public Point getPosition() {
        return position;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public List<Skill> getSkills() {
        return skills;
    }
    
    // Sets

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }      
    
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
