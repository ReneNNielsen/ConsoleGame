/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bruger
 */
public class NPC extends Actor {
    
    protected int xpGive;
    private double multiplier = 2.0;
    private String[] names = { "Jens" , "Erik" , "Binger" , "Ping" , "Pong" , "Lee" };
    //List<Skill> skillsArray = new ArrayList<Skill>( SkillBite, SkillFart );
    
    public NPC(int level)
    {
        // regn xp, health osv ud fra level int
        // Random name
        int idx = new Random().nextInt(names.length);
        name = (names[idx]);   
        
        // Calculate health
        health = (int) (100 * multiplier);
        
        // Skills
        int numberOfSkills = 0;
        if(level <= 3)
        {
            numberOfSkills = 1;
        }
        else if((level <= 6) && (level > 4))
        {
            numberOfSkills = 2;
        }
        else
        {
            numberOfSkills = 3;    
        }
        
        for(int i = 0; i > numberOfSkills; i++)
        {            
            Skill npcSkill = new Skill();
            skills.add(npcSkill.getRandomSkill());
        }
        
        //xpGive calculation
        xpGive = (int) (10 * multiplier);        
    }

    public int getXpGive() {
        return xpGive;
    }              
    
}
