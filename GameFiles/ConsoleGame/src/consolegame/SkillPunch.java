/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

/**
 *
 * @author reneNote
 */
public class SkillPunch extends Skill
{
    public SkillPunch() 
    {
        name = "Punch";
        damage = 15.0;
        warmupRounds = 0;
        missChance = 15;
    }
}
