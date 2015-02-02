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
public class Skill {
    protected String name;
    protected double damage;
    protected int missChance;
    protected int warmupRounds;

    public Skill() {
        
    }
    
    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public int getMissChance() {
        return missChance;
    }

    public int getWarmupRounds() {
        return warmupRounds;
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
        return randomSkill;
    }
}
