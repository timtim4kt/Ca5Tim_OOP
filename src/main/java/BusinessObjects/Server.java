package BusinessObjects;


/**
 * SERVER  - MULTITHREADED                                         March 2021
 * <p>
 * Server accepts client connections, creates a ClientHandler to handle the
 * Client communication, creates a socket and passes the socket to the handler,
 * runs the handler in a separate Thread.
 * <p>
 * <p>
 * The handler reads requests from clients, and sends replies to clients, all in
 * accordance with the rules of the protocol. as specified in
 * "ClientServerBasic" sample program
 * <p>
 * The following PROTOCOL is implemented:
 * <p>
 * If ( the Server receives the request "Time", from a Client ) then : the
 * server will send back the current time
 * <p>
 * If ( the Server receives the request "Echo message", from a Client ) then :
 * the server will send back the message
 * <p>
 * If ( the Server receives the request it does not recognize ) then : the
 * server will send back the message "Sorry, I don't understand"
 * <p>
 * This is an example of a simple protocol, where the server's response is based
 * on the client's request.
 *
 *  Each client is handled by a ClientHandler running in a separate worker Thread
 *  which allows the Server to continually listen for and handle multiple clients
 */


import DAOs.MySqlUserDao;
import DAOs.UserDaoInterface;
import DTOs.Player;
import Exceptions.DaoException;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.PlayerGoalsComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Server
{

    public UserDaoInterface IUserDao = new MySqlUserDao();
    PlayerGoalsComparator playerGoalsComparator = new PlayerGoalsComparator();
    Gson gsonParser =  Converters.registerLocalDate(new GsonBuilder()).create();

    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();

    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);


                    if (message.startsWith("DisplayPlayerById"))
                    {
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);
                        String player = IUserDao.findPlayerByPlayerIdJsonServer(id);
                        socketWriter.println(player);
                    }
                    else if(message.startsWith("DisplayAllPlayers"))
                    {
                        String playerList = IUserDao.findAllPlayersJsonServer();
                        socketWriter.println(playerList);
                    }
                    else if(message.startsWith("add"))
                    {
                        String jsonString = message.substring(4);
                        System.out.println(jsonString);
                        Player p = gsonParser.fromJson(jsonString, Player.class);
                        System.out.println(p);
                        IUserDao.addPlayer(1,p.getName(),p.getCountry(),p.getDob(),p.getWeightKilograms(),p.getHeightMetres(),p.getCareerAppearances(),p.getCareerGoals());
                        if(IUserDao.findPlayerByPlayerName(p.getName()) != null){
                            socketWriter.println("Player added: " + IUserDao.findPlayerByPlayerName(p.getName()));
                        }
                        else
                        {
                            socketWriter.println("Player not added");
                        }
                    }
                    else if (message.startsWith("DeletePlayerById"))
                    {
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);
                        IUserDao.deletePlayerById(id);
                        Player x = IUserDao.findPlayerByPlayerId(id);
                        if(x!= null){
                            socketWriter.println("player not deleted");
                        }
                        else
                        {
                            socketWriter.println("player deleted");
                        }

                    }
                    else if (message.startsWith("FilterByGoals"))
                    {
                        String[] tokens = message.split(" ");
                        int goals = Integer.parseInt(tokens[1]);
                        String pList = IUserDao.findAllPlayersGoalsFilterServer(goals,playerGoalsComparator);
                        if(pList != null){
                            socketWriter.println(pList);
                        }
                        else
                        {
                            socketWriter.println("No players have up to " + goals + " goals");
                        }

                    }


                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
