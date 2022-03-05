package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    PlayerManager playerManager;

    public static void main( String[] args )
    {
        App app = new App();
        app.start();
    }

    public void start(){

        ArrayList<Player> playerList = new ArrayList<>();
        initialize(playerList);
        playerManager = new PlayerManager(playerList,playerManager);
        displayMainMenu();
    }

    private void initialize(List list){
        list.add(new Player("Cristiano Ronaldo","Portugal", LocalDate.of(1985,2,5),85,1.87,1000,805));
        list.add(new Player("Lionel Messi","Argentina", LocalDate.of(1987,6,24),67,1.69,900,736));
        list.add(new Player("Neymar Jr","Brazil", LocalDate.of(1992,2,5),68,1.75,600,805));
        list.add(new Player("Luis Suarez","Uruguay", LocalDate.of(1990,7,11),73,1.77,700,600));
        list.add(new Player("Vinicius Jr","Brazil", LocalDate.of(2001,2,9),65,1.80,130,40));
        list.add(new Player("Phil Foden","England", LocalDate.of(2000,3,14),62,1.74,120,60));
        list.add(new Player("Mason Mount","England", LocalDate.of(1999,6,5),64,1.74,150,76));
        list.add(new Player("Mohammed Salah","Egypt", LocalDate.of(1995,8,15),68,1.75,360,250));
        list.add(new Player("Riyad Mahrez","Algeria", LocalDate.of(1994,4,9),70,1.83,500,200));
        list.add(new Player("Jamie Vardy","England", LocalDate.of(1992,2,14),67,1.81,400,300));
    }

    private void displayMainMenu()
    {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display All Players\n"
                + "2. X\n"
                + "3. X\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int DISPLAY = 1;
        final int X = 2;
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
                    case X:
                        System.out.println("X option chosen");
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


}
