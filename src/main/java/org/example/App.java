package org.example;

import java.time.LocalDate;
import java.util.*;

public class App 
{
    PlayerManager playerManager;

    static Player CR = new Player("Cristiano Ronaldo", "Portugal", LocalDate.of(1985, 2, 5), 85, 1.87, 1000, 805);
    static Player LM = new Player("Lionel Messi", "Argentina", LocalDate.of(1987, 6, 24), 67, 1.69, 900, 736);
    static Player NJ = new Player("Neymar Jr", "Brazil", LocalDate.of(1992, 2, 5), 68, 1.75, 600, 805);
    static Player LS = new Player("Luis Suarez", "Uruguay", LocalDate.of(1990, 7, 11), 73, 1.77, 700, 600);
    static Player VJ = new Player("Vinicius Jr", "Brazil", LocalDate.of(2001, 2, 9), 65, 1.80, 130, 40);
    static Player PF = new Player("Phil Foden", "England", LocalDate.of(2000, 3, 14), 62, 1.74, 120, 60);
    static Player MM = new Player("Mason Mount", "England", LocalDate.of(1999, 6, 5), 64, 1.74, 150, 76);
    static Player MS = new Player("Mohammed Salah", "Egypt", LocalDate.of(1995, 8, 15), 68, 1.75, 360, 250);
    static Player RM = new Player("Riyad Mahrez", "Algeria", LocalDate.of(1994, 4, 9), 70, 1.83, 500, 200);
    static Player JV = new Player("Jamie Vardy", "England", LocalDate.of(1992, 2, 14), 67, 1.81, 400, 300);

    public static void main( String[] args )
    {
        App app = new App();
        app.start();
    }

    public void start(){

        ArrayList<Player> playerList = new ArrayList<>();
        Map<Integer, Player> playerMap = new HashMap<>();
        initialize(playerList,playerMap);
        playerManager = new PlayerManager(playerList,playerManager);
        displayMainMenu();
    }

    private void initialize(List list,Map<Integer, Player> playerMap) {

        list.add(CR);
        list.add(LM);
        list.add(NJ);
        list.add(LS);
        list.add(VJ);
        list.add(PF);
        list.add(MM);
        list.add(MS);
        list.add(RM);
        list.add(JV);



    }
    private void displayMainMenu()
    {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display All Players\n"
                + "2. HashMap\n"
                + "3. X\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int DISPLAY = 1;
        final int RETRIEVE_OBJECT_BY_HASH_KEY = 2;
        final int X1 = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do
        {
            System.out.println("\n" + MENU_ITEMS);
            try
            {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option)
                {
                    case DISPLAY:
                        System.out.println("All Players");
                        System.out.println("===========");
                        playerManager.displayAllPlayers();
                        break;
                    case RETRIEVE_OBJECT_BY_HASH_KEY:
                          HashMap();
                        break;
                    case X1:
                        System.out.println("X1 option chosen");
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid input - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.print("Invalid input - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    public static void HashMap()
    {
        Map<Integer, Player> playerMap = new HashMap<>();

        playerMap.put(1, CR);
        playerMap.put(2, LM);
        playerMap.put(3, NJ);
        playerMap.put(4, LS);
        playerMap.put(5, VJ);
        playerMap.put(6, PF);
        playerMap.put(7, MM);
        playerMap.put(8, MS);
        playerMap.put(9, RM);
        playerMap.put(10, JV);

        System.out.println("");
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter a Key(1-10) to search for a player. Type 0 to exit");
        int key = kb.nextInt();
        Player player = playerMap.get(key);

    if(player != null)
    {
        System.out.println(key + " favourite player is: " + player);
    }else{
        System.out.println("The key: " + key + " could not be found.");
    }


    }
}
