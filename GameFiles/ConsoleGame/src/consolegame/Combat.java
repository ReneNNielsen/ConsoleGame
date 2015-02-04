/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruger
 */
public class Combat 
{
    private Player player;
    private int playerHealthRemaining = 0;
    private NPC npc;
    private int npcHealthRemaining = 0;
    private String combatLog = "";
    
    public Combat(Player player, NPC npc)
    {
        this.player = player;
        playerHealthRemaining = player.health;
        this.npc = npc;
        npcHealthRemaining = npc.health;
    }
    
    public boolean doCombat()
    {
        boolean isPlayerTurn = true;
        while (true) 
        {            
            try 
            {
                Game.clearConsole();
                showStatsAndSkills(isPlayerTurn);
                if (isPlayerTurn)
                {
                    doPlayerTurn();
                }
                else
                {
                    doNpcTurn();
                }
                Thread.sleep(1500);
                isPlayerTurn = !isPlayerTurn;
                if (npcHealthRemaining <= 0)
                {
                    Game.clearConsole();
                    System.out.println("You won!! :)");
                    Thread.sleep(1500);
                    return true;
                }
                else if (playerHealthRemaining <= 0)
                {
                    Game.clearConsole();
                    System.out.println("You lost :(");
                    Thread.sleep(1500);
                    return false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void showStatsAndSkills(boolean isPlayerTurn)
    {
        System.out.println(combatLog);
        System.out.println("--------------------------------------------");
        System.out.println(player.getName() + "\t\t\t" + npc.getName());
        System.out.println("Level: " + player.getLevel() + " xp: " + player.getXp() + "\t\tLevel: " + npc.getLevel());
        System.out.println("Health: " + playerHealthRemaining + "/" + player.health + "\t\tHealth: " + npcHealthRemaining + "/" + npc.health);
        System.out.println("---------------------------");
        
        if (isPlayerTurn) 
        {
            try {
                Game.br.reset();
            } catch (Exception e) {
            }
            
            System.out.println("Skils:");
            System.out.println("------");
            int numberToPress = 1;
            for (Skill skill : player.getSkills()) 
            {
                System.out.println(numberToPress + ": " + skill.getName());
                numberToPress++;
            }   
        }
    }
    
    private void doNpcTurn()
    {
        int randomNumber = new Random().nextInt(npc.skills.size());
        if (didAtackMiss(randomNumber)) 
        {
            combatLog += npc.name + " did miss with " + npc.skills.get(randomNumber).name + " when trying to damage " + player.name + "\n";
            System.out.println(npc.name + " did miss with " + npc.skills.get(randomNumber).name + " when trying to damage " + player.name);
        }
        else
        {
            int damageDone = (int)Math.round(npc.getSkillDamage(randomNumber));
            playerHealthRemaining -= damageDone;
            combatLog +=npc.name + " did " + damageDone + " damage with " + npc.skills.get(randomNumber).name + " to " + player.name + "\n";
            System.out.println(npc.name + " did " + damageDone + " damage with " + npc.skills.get(randomNumber).name + " to " + player.name);
        }
    
    }

    private void doPlayerTurn()
    {
        try {
            String readLine = Game.br.readLine();
            if (readLine.equals("run")) 
            {
                playerHealthRemaining = 0;
                System.out.println("You are scared of " + npc.name + " and run away like a little girl");
                return;
            }
            int skillNumber = Integer.parseInt(readLine) - 1;
            if (skillNumber < 4) {
                if (didAtackMiss(skillNumber)) 
                {
                    combatLog += player.name + " did miss with " + player.skills.get(skillNumber).name + " when trying to damage " + npc.name + "\n";
                    System.out.println(player.name + " did miss with " + player.skills.get(skillNumber).name + " when trying to damage " + npc.name);
                }
                else
                {
                    int damageDone;
                    if(wasAttackCritical(skillNumber))
                    {
                        damageDone = (int)Math.round(player.getSkillDamage(skillNumber) * 2);           
                        combatLog += "CRITICAL HIT!";
                    }
                    else
                    {
                        damageDone = (int)Math.round(player.getSkillDamage(skillNumber));
                    }
                    npcHealthRemaining -= damageDone;
                    combatLog += player.name + " did " + damageDone + " damage with " + player.skills.get(skillNumber).name + " to " + npc.name + "\n";
                    System.out.println(player.name + " did " + damageDone + " damage with " + player.skills.get(skillNumber).name + " to " + npc.name);
                }
            }
            else
            {
                System.out.println("You dont have a skill at that number, try another");
                doPlayerTurn();
            }
        } catch (Exception e) 
        {
            System.out.println("That's not a number. Try another number");
                doPlayerTurn();
        }
    }
    
    private boolean didAtackMiss(int missChance)
    {
        int randomNumber = new Random().nextInt(100);
        if (randomNumber <= missChance) 
        {
            return true;
        }
        return false;
    }
    
    private boolean wasAttackCritical(int critChance)
    {
        int randomNumber = new Random().nextInt(100);
        if (randomNumber <= critChance) 
        {
            return true;
        }
        return false;       
    }
}
