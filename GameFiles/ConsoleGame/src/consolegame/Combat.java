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
        return true;
    }
    
    private void doNpcTurn()
    {
        
    }

    private void doPlayerTurn()
    {
        
    }
}
