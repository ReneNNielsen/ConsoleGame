/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolegame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }      
    
}
