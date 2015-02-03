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
    private NPC npc;
    
    public Combat(Player player, NPC npc)
    {
        this.player = player;
        this.npc = npc;
    }
    
    public boolean doCombat()
    {
        Game.clearConsole();
        showStatsAndSkills();
        return false;
    }
    private void showStatsAndSkills()
    {
        System.out.println(player.getName() + "\t\t" + npc.getName());
        System.out.println("Level: " + player.getLevel() + " xp: " + player.getXp() + "\t\tLevel" + npc.getLevel());
        System.out.println("--------------------------------------------");
        
        System.out.println("Skils:");
        System.out.println("------");
        int numberToPress = 1;
        for (Skill skill : player.getSkills()) {
            System.out.println(numberToPress + ": " + skill.getName());
            numberToPress++;
        }
        try {
            Game.br.readLine();
        } catch (Exception e) {
        }
    }
    
    private void doNpcTurn()
    {
        
    }

    private void doPlayerTurn()
    {
        
    }
}
