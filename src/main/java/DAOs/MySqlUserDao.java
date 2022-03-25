package DAOs;

/** OOP Feb 2022
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a particular database (e.g. MySQL or Oracle etc...)
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics.
 *
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by callimng the DAO methods.
 *
 */


import DTOs.Player;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MySqlUserDao extends MySqlDao implements UserDaoInterface
{

    @Override
    public List<Player> findAllPlayers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM players";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("PLAYER_ID");
                String name = resultSet.getString("PLAYER_NAME");
                String country = resultSet.getString("COUNTRY");
                LocalDate date = resultSet.getDate("DOB").toLocalDate();
                int weight = resultSet.getInt("WEIGHTKG");
                double height = resultSet.getDouble("HEIGHTM");
                int appearances = resultSet.getInt("APPEARANCES");
                int goals = resultSet.getInt("GOALS");
                Player p = new Player(name,country,date,weight,height,appearances,goals);
                playersList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllPlayersResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllPlayers() " + e.getMessage());
            }
        }
        return playersList;     // may be empty
    }

    public Player findPlayerByPlayerId(int id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Player player = null;
        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM players WHERE PLAYER_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int playerId = resultSet.getInt("PLAYER_ID");
                String name = resultSet.getString("PLAYER_NAME");
                String country = resultSet.getString("COUNTRY");
                LocalDate date = resultSet.getDate("DOB").toLocalDate();
                int weight = resultSet.getInt("WEIGHTKG");
                double height = resultSet.getDouble("HEIGHTM");
                int appearances = resultSet.getInt("APPEARANCES");
                int goals = resultSet.getInt("GOALS");

                player = new Player(name,country,date,weight,height,appearances,goals);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findPlayerById() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findPlayerById() " + e.getMessage());
            }
        }
        return player;     // reference to User object, or null value
    }

    /*@Override
    public List<User> findAllUsersLastNameContains( String subString ) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> usersList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM USER WHERE LAST_NAME LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + subString + "%");


            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                User u = new User(userId, firstname, lastname, username, password);
                usersList.add(u);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllUsersLastNameContains() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllUsersLastNameContains() " + e.getMessage());
            }
        }
        return usersList;     // may be empty
    }*/




    /*public void addUser(int id ,String firstname, String lastname, String username,String password) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = this.getConnection();

            String query = "INSERT INTO USER VALUES (null,?, ?, ?,?)";

            // Try-with-Resources style

            preparedStatement = connection.prepareStatement(query);


            System.out.println("Connected to the database");
            System.out.println("Building a PreparedStatement to insert a new row in database.");

//          preparedStatement = conn.prepareStatement("INSERT INTO test.Customers VALUES (null, ?, ?, ?)";
//          Parameters are indexed starting with 1, and correspond to order of the question marks above.
//          1 corresponds to first question mark, 2 to the second one, and so on...
//          As the first field is an Auto-Increment field in the database, we specify a null value for it.

            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();

            {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        freeConnection(connection);
                    }
                } catch (SQLException e) {
                    throw new DaoException("addUser() " + e.getMessage());
                }
            }} catch (SQLException throwables) {
            throwables.printStackTrace();
        }}*/}






