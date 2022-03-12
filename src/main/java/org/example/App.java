package org.example;

import java.time.LocalDate;
import java.util.*;

public class App {
    ArrayList<Player> playerList = new ArrayList<>();
    Map<Integer, Player> playerMap = new HashMap<>();
    Map<Long, Player> playerTree = new TreeMap<>();
    PriorityQueue<Player> queue;
    PriorityQueue<Player> twoFieldQueue;
    PlayerManager playerManager;
    private static final PlayerNameComparator playerNameComparator = new PlayerNameComparator();

    static Player CR = new Player("Cristiano Ronaldo", "Portugal", LocalDate.of(1985, 2, 5), 85, 1.87, 1000, 805);
    static Player LM = new Player("Lionel Messi", "Argentina", LocalDate.of(1987, 6, 24), 67, 1.69, 900, 736);
    static Player NJ = new Player("Neymar Jr", "Brazil", LocalDate.of(1992, 2, 5), 68, 1.75, 600, 450);
    static Player LS = new Player("Luis Suarez", "Uruguay", LocalDate.of(1990, 7, 11), 73, 1.77, 700, 600);
    static Player VJ = new Player("Vinicius Jr", "Brazil", LocalDate.of(2001, 2, 9), 65, 1.80, 130, 40);
    static Player PF = new Player("Phil Foden", "England", LocalDate.of(2000, 3, 14), 73, 1.74, 120, 60);
    static Player MM = new Player("Mason Mount", "England", LocalDate.of(1999, 6, 5), 64, 1.74, 150, 76);
    static Player MS = new Player("Mohammed Salah", "Egypt", LocalDate.of(1995, 8, 15), 70, 1.75, 360, 250);
    static Player RM = new Player("Riyad Mahrez", "Algeria", LocalDate.of(1994, 4, 9), 70, 1.83, 500, 200);
    static Player JV = new Player("Jamie Vardy", "England", LocalDate.of(1992, 2, 14), 67, 1.81, 400, 300);

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        initializeHashmap(playerList,playerMap);
        initializeTreeMap(playerList,playerTree);
        this.queue = new PriorityQueue<>();
        this.twoFieldQueue = new PriorityQueue<>(new PlayerCountryGoalsComparator());
        initialize(playerList,twoFieldQueue);
        playerManager = new PlayerManager(playerList);
        displayMainMenu();
    }

    private void initialize(List list,Queue q) {
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

        q.add(CR);
        q.add(LM);
        q.add(NJ);
        q.add(LS);
        q.add(VJ);
        q.add(PF);
        q.add(MM);
        q.add(MS);
        q.add(RM);
        q.add(JV);
    }

    public void initializeHashmap(List list,Map<Integer, Player> map) {
        for(int i = 0;i < list.size();i++) {
            map.put(i + 1, (Player) list.get(i));
        }
    }

    public void initializeTreeMap(List list,Map<Long, Player> tree) {
        for(int i = 0;i < list.size();i++) {
            tree.put((long) (list.size() - i), (Player) list.get(i));
        }
    }



    public void priorityQueue()
    {
        queue.add(playerList.get(7));
        queue.add(playerList.get(8));

        queue.add(playerList.get(3));
        queue.add(playerList.get(5));

        // remove and display one element
        System.out.println("Remove and display a single element");
        Player p = queue.remove();
        System.out.println(p.toString() + "-  Player weight in KG" + p.getWeightKilograms());

        // add one top-priority element
        queue.add(playerList.get(0));

        // remove and display all elements in priority order
        System.out.println("\nRemove and display all elements");
        while ( !queue.isEmpty() ) {
            Player p1 = queue.remove();
            System.out.println(p1.toString() + "-  Player weight in KG" + p1.getWeightKilograms());
        }
    }

    public void displayTwoFieldQueue()
    {
        while ( !twoFieldQueue.isEmpty() ) {
            System.out.println(twoFieldQueue.remove());
        }
    }

    private void displayMainMenu() {
        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display All Players\n"
                + "2. Find Player by Hash Key\n"
                + "3. Display Player Tree\n"
                + "4. PriorityQueue Sequence Simulation\n"
                + "5. PriorityQueue Two-Field (Country, goals)\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int DISPLAY = 1;
        final int RETRIEVE_OBJECT_BY_HASH_KEY = 2;
        final int RETRIEVE_OBJECT_BY_TREE_KEY = 3;
        final int PRIORITYQUEUE_SEQUENCE_SIMULATION = 4;
        final int PRIORITYQUEUE_COUNTRY_GOALS = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAY:
                        System.out.println("All Players");
                        System.out.println("===========");
                        playerManager.displayAllPlayers();
                        break;
                    case RETRIEVE_OBJECT_BY_HASH_KEY:
                        System.out.println("Hashmap Option Chosen");
                        System.out.println("=====================");
                        playerManager.getByHashKey(playerMap);
                        break;
                    case RETRIEVE_OBJECT_BY_TREE_KEY:
                        System.out.println("Treemap option chosen");
                        System.out.println("=====================");
                        playerManager.displayPlayerTree(playerTree);
                        break;
                    case PRIORITYQUEUE_SEQUENCE_SIMULATION:
                        System.out.println("Priority Queue option chosen");
                        System.out.println("=====================");
                        priorityQueue();
                        break;
                    case PRIORITYQUEUE_COUNTRY_GOALS:
                        System.out.println("Priority Queue (Country/Goals) option chosen");
                        System.out.println("============================================");
                        displayTwoFieldQueue();
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid input - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid input - please enter number in range");
            }
        } while (option != EXIT);
        System.out.println("\nExiting Main Menu, goodbye.");
    }
  }
