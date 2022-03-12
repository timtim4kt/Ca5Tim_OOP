package org.example;

import java.util.Comparator;

public class PlayerCountryGoalsComparator implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2)
    {
        boolean countryEqual =
                p1.getCountry().equalsIgnoreCase(p2.getCountry());


        if(countryEqual == true)
        {
            return (p1.getCareerGoals() - p2.getCareerGoals()) * -1;
        }
        else
        {
            return p1.getCountry().compareToIgnoreCase(p2.getCountry());
        }
    }
}
