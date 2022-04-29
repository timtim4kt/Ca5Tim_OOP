package BusinessObjects;

/** CLIENT                                                  March 2021
 *
 * This Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */
import DTOs.Player;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.GsonBuilder;
import org.example.PlayerGoalsComparator;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Gson gsonParser =  Converters.registerLocalDate(new GsonBuilder()).create();
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");
            System.out.println("******************************");
            System.out.println("*      Client Side Menu      *");
            System.out.println("******************************");
            System.out.println();
            System.out.println("Option 1. Display Entity by ID");
            System.out.println("Option 2. Display All Entities");
            System.out.println("Option 3. Add Entity");
            System.out.println("Option 4. Delete Entity");
            System.out.println("Option 5. Display players with X or more goals");
            System.out.println("Option 6. Exit");
            System.out.println("Enter your option: ");

            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            boolean continueLooping = true;
            while(continueLooping==true) {
                if (command.equalsIgnoreCase("1"))   //we expect the server to return a time
                {
                    System.out.println("Enter ID: ");
                    String id = in.nextLine();
                    socketWriter.println("DisplayPlayerById " + id);
                    String input = socketReader.nextLine();
                    Player p = gsonParser.fromJson(input, Player.class);
                    if(p!=null) {
                        System.out.printf("%-24s%-14s%-13s%-12s%-12s%-15s%-1s\n",
                                p.getName(),
                                p.getCountry(),
                                p.getDob(),
                                p.getWeightKilograms(),
                                p.getHeightMetres(),
                                p.getCareerAppearances(),
                                p.getCareerGoals());
                    }
                    else{
                        System.out.println("Player not found");
                    }
                }
                else if(command.equalsIgnoreCase("2"))
                {
                    socketWriter.println("DisplayAllPlayers");
                    String input = socketReader.nextLine();
                    Player[] players = gsonParser.fromJson(input, Player[].class);

                    displayAllPlayers(players);
                }
                else if(command.equalsIgnoreCase("3"))
                {
                    System.out.println("Enter ID: ");
                    String id = in.nextLine();
                    System.out.println("Enter Name: ");
                    String name = in.nextLine();
                    System.out.println("Enter Country: ");
                    String country = in.nextLine();
                    System.out.println("Enter Year: ");
                    String year = in.nextLine();
                    System.out.println("Enter Month: ");
                    String month = in.nextLine();
                    System.out.println("Enter Date: ");
                    String date = in.nextLine();
                    System.out.println("Enter Weight: ");
                    String weight = in.nextLine();
                    System.out.println("Enter Height: ");
                    String height = in.nextLine();
                    System.out.println("Enter Appearances: ");
                    String appearances = in.nextLine();
                    System.out.println("Enter Goals: ");
                    String goals = in.nextLine();
                    int pYear = Integer.parseInt(year);
                    int pMonth = Integer.parseInt(month);
                    int pDay = Integer.parseInt(date);
                    LocalDate dob = LocalDate.of(pYear,pMonth,pDay);
                    Player newP = new Player(name,country,dob,Integer.parseInt(weight),Double.parseDouble(height),Integer.parseInt(appearances),Integer.parseInt(goals));
                    String newPlayer = gsonParser.toJson(newP);
                    command = "add" + " " + newPlayer;
                    socketWriter.println(command);
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if (command.equalsIgnoreCase("4"))   //we expect the server to return a time
                {
                    System.out.println("Enter ID: ");
                    String id = in.nextLine();
                    socketWriter.println("DeletePlayerById " + id);
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if (command.equalsIgnoreCase("5"))   //we expect the server to return a time
                {
                    System.out.println("Enter Goal Amount: ");
                    String goals = in.nextLine();
                    socketWriter.println("FilterByGoals " + goals);
                    String input = socketReader.nextLine();
                    Player[] filteredPlayers = gsonParser.fromJson(input, Player[].class);
                    displayAllPlayers(filteredPlayers);
                }
                else{
                    continueLooping = false;
                }

                System.out.println("******************************");
                System.out.println("*      Client Side Menu      *");
                System.out.println("******************************");
                System.out.println();
                System.out.println("Option 1. Display Entity by ID");
                System.out.println("Option 2. Display All Entities");
                System.out.println("Option 3. Add Entity");
                System.out.println("Option 4. Delete Entity");
                System.out.println("Option 5. Display players with X or more goals");
                System.out.println("Option 6. Exit");
                System.out.println("Enter your option: ");

                command = in.nextLine();
                socketWriter.println(command);
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }

    public static void displayAllPlayers(Player[] players) {
        for(Player p: players) {
            System.out.printf("%-24s%-14s%-13s%-12s%-12s%-15s%-1s\n",
                    p.getName(),
                    p.getCountry(),
                    p.getDob(),
                    p.getWeightKilograms(),
                    p.getHeightMetres(),
                    p.getCareerAppearances(),
                    p.getCareerGoals());
        }
    }
}
