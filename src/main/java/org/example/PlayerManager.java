package org.example;

import DTOs.Player;

import java.util.*;

public class PlayerManager
{

    private final List<Player> playerList;

    private final PlayerNameComparator playerNameComparator = new PlayerNameComparator();


    public PlayerManager(ArrayList<Player> playerList) {
        this.playerList = playerList;

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

    public void displayPlayerTree(Map<Long, Player> tree){
        Set<Long> keySet = tree.keySet();

        System.out.println("Key   Name                    Country       DOB          Weight      Height      Appearances    Goals");
        System.out.println("===   ====================    ===========   ==========   ========    ========    ===========    =============");

        for (Long key : keySet) {
            Player player = tree.get(key);
            System.out.printf("%-6s%-24s%-14s%-13s%-12s%-12s%-15s%-1s\n",
                    key,
                    player.getName(),
                    player.getCountry(),
                    player.getDob(),
                    player.getWeightKilograms(),
                    player.getHeightMetres(),
                    player.getCareerAppearances(),
                    player.getCareerGoals()
            );
        }
    }

    public void getByHashKey(Map<Integer, Player> map)
    {
        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a Key(1-10) to search for a player. Type 0 to exit");
        int key = kb.nextInt();
        Player player = map.get(key);

        System.out.println();
        if (player != null) {
            System.out.println("                                     *  *  *  *  *  *  *  *  *");
            System.out.println("                                     *    Player of key " + key + "    *");
            System.out.println("                                     *  *  *  *  *  *  *  *  *");
            System.out.println();
            System.out.println("Name                    Country       DOB          Weight      Height      Appearances    Goals");
            System.out.println("====================    ===========   ==========   ========    ========    ===========    =============");

            System.out.printf("%-24s%-14s%-13s%-12s%-12s%-15s%-1s\n",
                    player.getName(),
                    player.getCountry(),
                    player.getDob(),
                    player.getWeightKilograms(),
                    player.getHeightMetres(),
                    player.getCareerAppearances(),
                    player.getCareerGoals()
            );

        } else {
            System.out.println("The key: " + key + " could not be found.");
        }


    }

    }

