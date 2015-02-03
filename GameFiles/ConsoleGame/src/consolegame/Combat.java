/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

/**
 *
 * @author Bruger
 */
public class Combat {
    
    private Player player;
    private int playerHealthRemaining = 0;
    private NPC npc;
    private int npcHealthRemaining = 0;
    
    public Combat(Player player, NPC npc)
    {
        this.player = player;
        playerHealthRemaining = player.health;
        this.npc = npc;
        npcHealthRemaining = npc.health;
        
    }
    
    public boolean doCombat()
    {
        Game.clearConsole();
        showStatsAndSkills();
        return true;
    }
    private void showStatsAndSkills()
    {
        System.out.println(player.getName() + "\t\t\t" + npc.getName());
        System.out.println("Level: " + player.getLevel() + " xp: " + player.getXp() + "\t\tLevel: " + npc.getLevel());
        System.out.println("Health: " + playerHealthRemaining + "/" + player.health + "\t\tHealth: " + npcHealthRemaining + "/" + npc.health);
        System.out.println("--------------------------------------------");
        
        System.out.println("Skils:");
        System.out.println("------");
        int numberToPress = 1;
        for (Skill skill : player.getSkills()) {
            System.out.println(numberToPress + ": " + skill.getName());
            numberToPress++;
        }
    }
    
    private void doNpcTurn()
    {
        
    }

    private void doPlayerTurn()
    {
        try {
            String readLine = Game.br.readLine();
            int skillNumber = Integer.parseInt(readLine);
            if (skillNumber < 4) {
                npcHealthRemaining -= player.skills.get(skillNumber).getDamage();
                System.out.println(player.name + " did " + player.skills.get(skillNumber).getDamage() + " with " + player.skills.get(skillNumber).name + " to " + npc.name);
            }
        } catch (Exception e) {
        }
    }
}
