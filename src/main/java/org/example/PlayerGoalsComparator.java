package org.example;

import DTOs.Player;

import java.util.Comparator;

public class PlayerGoalsComparator implements Comparator<Player>
{
    public int compare(Player p1, Player p2) {
        return (p1.getCareerGoals() - p2.getCareerGoals()) * -1;
    }
}