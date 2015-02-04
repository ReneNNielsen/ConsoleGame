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
public class SkillKick extends Skill{

    public SkillKick() {
        name = "Kick";
        damage = 20.0;
        warmupRounds = 0;
        missChance = 25;
    }
}
