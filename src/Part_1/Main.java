// Created by Olexandr Rudenko
// Variant 10
// Task 1
package Part_1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Airline> airlines;
    private List<Trip> trips;
    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }
    private void start(){
        airlines = new ArrayList<>();
        trips = new ArrayList<>();
        Executor executor = new Executor(airlines, trips);
        executor.load_from_file();
        Menu menu = new Menu(executor,airlines,trips);
        menu.create();
    }
    private Executor create_data(){
        // list of airlines
        Airline airline1 = new Airline(1,"Авіакомпанія1");
        Airline airline2 = new Airline(2,"Авіакомпанія2");
        Airline airline3 = new Airline(3,"Авіакомпанія3");
        Airline airline4 = new Airline(4,"Авіакомпанія4");
        airlines = new ArrayList<>();
        airlines.add(airline1);
        airlines.add(airline2);
        airlines.add(airline3);
        airlines.add(airline4);

        //list of trips
        Trip trip1 = new Trip(1,"Київ","Лондон", 200,1);
        Trip trip2 = new Trip(2,"Київ","Пекін", 500,2);
        Trip trip3 = new Trip(3,"Київ","Варшава", 600,1);
        trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);

        Executor executor = new Executor(airlines, trips);
        return executor;
    }
}
