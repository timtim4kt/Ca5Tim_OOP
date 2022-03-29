package BusinessObjects;

/** OOP Feb 2022
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses Data Access Objects (DAOs),
 * Data Transfer Objects (DTOs), and  a DAO Interface to define
 * a contract between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "CreateUsers.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

import DAOs.MySqlUserDao;
import DAOs.UserDaoInterface;
import DTOs.Player;
import Exceptions.DaoException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App
{
    static UserDaoInterface IUserDao = new MySqlUserDao();  //"IUserDao" -> "I" stands for for

    public static void main(String[] args)
    {
//        // Notice that the userDao reference is an Interface type.
//        // This allows for the use of different concrete implementations.
//        // e.g. we could replace the MySqlUserDao with an OracleUserDao
//        // (accessing an Oracle Database)
//        // without changing anything in the Interface.
//        // If the Interface doesn't change, then none of the
//        // code in this file that uses the interface needs to change.
//        // The 'contract' defined by the interface will not be broken.
//        // This means that this code is 'independent' of the code
//        // used to access the database. (Reduced coupling).
//
//        // The Business Objects require that all User DAOs implement
//        // the interface called "UserDaoInterface", as the code uses
//        // only references of the interface type to access the DAO methods.
displayMainMenu2();
        }


    private static void displayMainMenu2() {
        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS PART 2***\n"
                + "1. Display All Players\n"
                + "2. Find Player by ID\n"
                + "3. Add Player\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int DISPLAY = 1;
        final int RETRIEVE_OBJECT_BY_ID = 2;
        final int ADD_PLAYER = 3;
        final int EXIT = 4;

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
                        List<Player> players = IUserDao.findAllPlayers();     // call a method in the DAO
                        if( players.isEmpty() )
                            System.out.println("There are no Players");
                        else {
                            for (Player player : players)
                                System.out.println("Player: " + player.toString());
                        }
                        break;
                    case RETRIEVE_OBJECT_BY_ID:
                        System.out.println("ID Option Chosen");
                        System.out.println("=====================");
                        System.out.println("\nCall: findPlayerById()");
                        System.out.print("Enter ID: ");
                        int id = keyboard.nextInt();
                        keyboard.nextLine();
                        Player player = IUserDao.findPlayerByPlayerId(id);
                        System.out.println(player.toString());
                        break;
                    case ADD_PLAYER:
                        System.out.println("Add Player Option Chosen");
                        System.out.println("========================");
                        System.out.println("\nCall: addPlayer()");
                        System.out.print("Enter Player ID: ");
                        id = keyboard.nextInt();
                        keyboard.nextLine();
                        System.out.println("Enter Player Name: ");
                        String name = keyboard.nextLine();
                        System.out.println("Enter Player Country: ");
                        String country = keyboard.nextLine();
                        System.out.println("Enter Player Birth Year: ");
                        int year = keyboard.nextInt();
                        System.out.println("Enter Player Birth Month: ");
                        int month = keyboard.nextInt();
                        System.out.println("Enter Player Birth Date: ");
                        int date = keyboard.nextInt();
                        System.out.println("Enter Player Weight: ");
                        LocalDate DOB = LocalDate.of(year,month,date);
                        int weight = keyboard.nextInt();
                        System.out.println("Enter Player Height: ");
                        double height = keyboard.nextDouble();
                        System.out.println("Enter Player Appearances: ");
                        int appearances = keyboard.nextInt();
                        System.out.println("Enter Player Goals: ");
                        int goals = keyboard.nextInt();
                        keyboard.nextLine();
                        IUserDao.addPlayer(id,name,country,DOB,weight,height,appearances,goals);
                        player = IUserDao.findPlayerByPlayerId(id);
                        if(player!=null){
                            System.out.println("Player added :" + player.toString());
                        }
                        else{
                            System.out.println("Player not added");
                        }
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
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } while (option != EXIT);
        System.out.println("\nExiting Main Menu, goodbye.");
    }

}
