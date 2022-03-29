package DAOs;

/** OOP Feb 2022
 * UserDaoInterface
 *
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User DAOs or Oracle User DAOs etc...
 *
 * Classes from the Business Layer (users of this DAO interface)
 * should use reference variables of this interface type to avoid
 * dependencies on the underlying concrete classes (e.g. MySqlUserDao).
 *
 * More sophisticated implementations will use a factory
 * method to instantiate the appropriate DAO concrete classes
 * by reading database configuration information from a
 * configuration file (that can be changed without altering source code)
 *
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */

import DTOs.Player;
import Exceptions.DaoException;
import org.example.PlayerGoalsComparator;

import java.time.LocalDate;
import java.util.List;

public interface UserDaoInterface
{
    public List<Player> findAllPlayers() throws DaoException;

    public Player findPlayerByPlayerId(int id) throws DaoException;

    public void addPlayer(int id, String name, String country, LocalDate date, int weight, double height, int appearances, int goals) throws DaoException;

    List<Player> findAllPlayersGoalsFilter(int g, PlayerGoalsComparator playerGoalsComparator) throws DaoException;

    public void deletePlayerById(int id) throws DaoException;

}

