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

import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        UserDaoInterface IUserDao = new MySqlUserDao();  //"IUserDao" -> "I" stands for for

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

        try
        {
            System.out.println("\nCall findAllPlayers()");
            List<Player> players = IUserDao.findAllPlayers();     // call a method in the DAO

            if( players.isEmpty() )
                System.out.println("There are no Players");
            else {
                for (Player player : players)
                    System.out.println("Player: " + player.toString());
            }

            // test dao - with username and password that we know are present in the database
            System.out.println("\nCall: findPlayerById()");
            int id = 4;
            Player player = IUserDao.findPlayerByPlayerId(id);

            if( player != null ) // null returned if userid and password not valid
                System.out.println("Player found: " + player);
            else
                System.out.println("Player with that id not found");

            // test dao - with an invalid username (i.e. not in database)
            id = 865678;
            player = IUserDao.findPlayerByPlayerId(id);
            if(player != null)
                System.out.println("Player: "  + player + " was found: " );
            else
                System.out.println("Player id: " + id + " is not valid");

           /* // test dao - with valid substring

            System.out.println("\nCall: findAllUsersLastNameContains()");
            String subString = "it";
            List <User> usersWithSubInLast = IUserDao.findAllUsersLastNameContains(subString);

            if( usersWithSubInLast.isEmpty() )
                System.out.println("There are no Users with this Substring in their last name");
            else {
                for (User user1 : usersWithSubInLast)
                    System.out.println("User: " + user1.toString());
            }

            // test dao - with invalid substring

            System.out.println("\nCall: findAllUsersLastNameContains()");
            subString = "hwuiiu";
            usersWithSubInLast = IUserDao.findAllUsersLastNameContains(subString);

            if( usersWithSubInLast.isEmpty() )
                System.out.println("There are no Users with this Substring in their last name");
            else {
                for (User user1 : usersWithSubInLast)
                    System.out.println("User: " + user1.toString());
            }

            IUserDao.addUser(6,"Paul","Blart","PB","password");*/

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
