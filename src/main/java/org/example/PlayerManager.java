package org.example;

import java.util.*;

public class PlayerManager
{

    private final List<Player> playerList;
    private PlayerManager playerManager;
    private final PlayerNameComparator playerNameComparator = new PlayerNameComparator();


    public PlayerManager(ArrayList<Player> playerList, PlayerManager playerManager) {
        this.playerList = playerList;
        this.playerManager = playerManager;
    }

    public List<Player> getAllPlayers() {
        return this.playerList;
    }


    public void displayAllPlayers() {
        playerList.sort(playerNameComparator);
        System.out.println("Name                    Country       DOB          Weight      Height      Appearances    Goals");
        System.out.println("====================    ===========   ==========   ========    ========    ===========    =============");
        for (Player p : this.playerList) {
            System.out.printf("%-24s%-14s%-13s%-12s%-12s%-15s%-1s\n",
                    p.getName(),
                    p.getCountry(),
                    p.getDob(),
                    p.getWeightKilograms(),
                    p.getHeightMetres(),
                    p.getCareerAppearances(),
                    p.getCareerGoals()
            );
        }
        }
    }

