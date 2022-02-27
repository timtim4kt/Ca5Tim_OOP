package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        App app = new App();
        app.start();
    }

    public void start(){
        System.out.println("Hello World!");
        System.out.println("Project part 1 - CA5");
        ArrayList<Player> playerList = new ArrayList<>();
        initialize(playerList);
    }

    private void initialize(List list){
        list.add(new Player("Cristiano Ronaldo","Portugal", LocalDate.of(1985,2,5),85,1.87,1000,805));
        list.add(new Player("Lionel Messi","Argentina", LocalDate.of(1987,6,24),67,1.69,900,736));
        list.add(new Player("Neymar Jr","Brazil", LocalDate.of(1992,2,5),68,1.75,60,805));
        list.add(new Player("Luis Suarez","Uruguay", LocalDate.of(1987,6,24),67,1.69,900,736));
        list.add(new Player("Vinicius Jr","Brazil", LocalDate.of(1985,2,5),85,1.87,1000,805));
        list.add(new Player("Phil Foden","England", LocalDate.of(1987,6,24),67,1.69,900,736));
        list.add(new Player("Mason Mount","England", LocalDate.of(1985,2,5),85,1.87,1000,805));
        list.add(new Player("Mohammed Salah","Egypt", LocalDate.of(1992,6,15),73,1.75,900,736));
        list.add(new Player("Riyad Mahrez","Algeria", LocalDate.of(1985,2,5),85,1.87,1000,805));
        list.add(new Player("Jamie Vardy","England", LocalDate.of(1987,6,24),67,1.69,900,736));
    }
}
