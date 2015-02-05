package consolegame;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author René & Daniel
 */
public class Combat 
{
    private Player player;
    private int playerHealthRemaining = 0;
    private NPC npc;
    private int npcHealthRemaining = 0;
    private String combatLog = "";
    
    /**
     * Constructor of Combat
     * @param player: Player
     * @param npc: NPC
     */
    public Combat(Player player, NPC npc)
    {
        this.player = player;
        playerHealthRemaining = player.health;
        this.npc = npc;
        npcHealthRemaining = npc.health;
    }
    
    /**
     * This method is used to start a Combat between the Player and a NPC. It checks which turn it is, and if the Player 
     * has lost or won. Returns true when the Player wins, and false if the Player loses
     * @return boolean
     */
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
    
    /**
     * Method to display the players skills on a list and stats. If its the players turn in a combat the list of skills is shown
     * @param isPlayerTurn: boolean 
     */
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
    
    /**
     * Method that controls the NPC when it is its turn. It handles its chances of missing an attack and also if its a critical hit
     */
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
            if(wasAttackCritical(npc.skills.get(randomNumber).getCritChance()))
            {
                damageDone = (int)Math.round(npc.getSkillDamage(randomNumber) * 2);           
                combatLog += "CRITICAL HIT! ";
                System.out.print("CRITICAL HIT! ");
            }           
            playerHealthRemaining -= damageDone;
            combatLog +=npc.name + " did " + damageDone + " damage with " + npc.skills.get(randomNumber).name + " to " + player.name + "\n";
            System.out.println(npc.name + " did " + damageDone + " damage with " + npc.skills.get(randomNumber).name + " to " + player.name);
        }
    
    }

    /**
     * Method that controls the output of what the player chooses to do under a battle. Handles critical hit / miss chance as well
     */
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
                if (didAtackMiss(player.skills.get(skillNumber).getMissChance())) 
                {
                    combatLog += player.name + " did miss with " + player.skills.get(skillNumber).name + " when trying to damage " + npc.name + "\n";
                    System.out.println(player.name + " did miss with " + player.skills.get(skillNumber).name + " when trying to damage " + npc.name);
                }
                else
                {
                    int damageDone = (int)Math.round(player.getSkillDamage(skillNumber));
                    if(wasAttackCritical(player.skills.get(skillNumber).getCritChance()))
                    {
                        damageDone = (int)Math.round(player.getSkillDamage(skillNumber) * 2);           
                        combatLog += "CRITICAL HIT! ";
                        System.out.print("CRITICAL HIT! ");
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
    
    /**
     * This method uses the miss chance of the skill and returns a true/false if the attack missed or not 
     * @param missChance: int
     * @return boolean
     */
    private boolean didAtackMiss(int missChance)
    {
        int randomNumber = new Random().nextInt(100);
        if (randomNumber <= missChance) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * This method uses the critical hit chance of the skill and returns a true/false if the attack was critical or not 
     * @param critChance: int
     * @return boolean
     */
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
