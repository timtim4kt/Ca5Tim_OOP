package org.example;

import java.util.Comparator;

public class PlayerNameComparator implements Comparator<Player>
{
    public int compare(Player p1, Player p2) {
        return p1.getName().compareTo(p2.getName());
    }
}