package Part_1;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Executor executor;
    private List<Airline> airlines;
    private List<Trip> trips;
    private Scanner in;
    public Menu(Executor executor, List<Airline> airlines, List<Trip> trips){
        this.executor = executor;
        this.airlines = airlines;
        this.trips = trips;
        in = new Scanner(System.in);
    }
    public void create(){
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("--------------Меню--------------");
            System.out.println("1. Отримати авіакомпанію за заданим id");
            System.out.println("2. Отримати авіакопанію за заданим порядковим номером");
            System.out.println("3. Знайти кількість авіакомпаній");
            System.out.println("4. Додати нову авіакомпанію");
            System.out.println("5. Видалити авіакомпанію");
            System.out.println("6. Додати новий рейс");
            System.out.println("7. Видалити рейс");
            System.out.println("8. Редагувати рейс");
            System.out.println("9. Показати всі авіакомпанії та рейси");
            int number_of_menu = in.nextInt();
            switch (number_of_menu) {
                case (1):
                    get_airline_by_id_1();
                    break;
                case(2):
                    get_airlines_by_number_2();
                    break;
                case(3):
                    print_count_airlines_3();
                    break;
                case(4):
                    add_new_airline_4();
                    break;
                case(5):
                    delete_airline_5();
                    break;
                case(6):
                    add_new_trip_6();
                    break;
                case(7):
                    delete_trip_7();
                    break;
                case(8):
                    edit_trip_8();
                    break;
                case(9):
                    show_all_9();
                    break;
                default:
                    System.out.println("Уведіть номер пункту меню!");
                    break;

            }
        }
    }
    private void get_airline_by_id_1(){
        System.out.println("Уведіть id:");
        int id = in.nextInt();
        Airline airline = executor.get_airline_by_id(id);
        List<Trip> trips_equals_id = executor.get_trip_by_airline_id(id);
        if(airline.getCode() == -1){
            System.out.println("Авіакомпанії з таким id не знайдено!");
        }
        else{
            System.out.println(airline.toString() + "\n");
            if(trips_equals_id.size() == 0){
                System.out.println("Рейсів немає!");
            }
            else{
                for (int i = 0; i < trips_equals_id.size(); i++) {
                    System.out.println("Рейс");
                    System.out.println(trips_equals_id.get(i).toString());
                }
            }
        }
    }
    private void get_airlines_by_number_2(){
        System.out.println("Уведіть номер: ");
        int index = in.nextInt();
        if(index > airlines.size() || index <= 0) {
            System.out.println("Розмір масиву " + airlines.size() + "! " + "Уведіть інше число!");
        }
        else {
            System.out.println(executor.get_airline_by_index(index).toString());
            List<Trip> trips_by_index = executor.get_trips_by_index(index);
            if (trips_by_index.size() == 0) {
                System.out.println("Рейсів немає!");
            } else {
                for (int i = 0; i < trips_by_index.size(); i++) {
                    System.out.println("Рейс");
                    System.out.println(trips_by_index.get(i).toString());
                }
            }
        }

    }
    private void print_count_airlines_3(){
        System.out.print("Кількість авіакомпаній: ");
        System.out.println(executor.get_count_airlines());
    }
    private void add_new_airline_4(){
        System.out.println("Уведіть id: ");
        int new_id = in.nextInt();
        if(is_exist_id_airline(new_id)){
            System.out.println("Авіакомпанія з таким іd вже існує!");
        }
        else{
            System.out.println("Уведіть назву авіакомпанії: ");
            Scanner in2 = new Scanner(System.in);
            String new_name = in2.nextLine();
            if (is_exist_name_airline(new_name)){
                System.out.println("Авіакомпанія з такою назвою вже існує!");
            }
            else{
                Airline new_airline = new Airline(new_id, new_name);
                executor.add_new_airline(new_airline);
                System.out.println("Успішно додано!");
            }
        }
    }
    private void delete_airline_5(){
        System.out.println("Уведіть id: ");
        int id_for_delete = in.nextInt();
        if(executor.delete_airline_by_id(id_for_delete)){
            System.out.println("Успішно видалено!");
        }
        else {
            System.out.println("Не знайдено авіакомпанію з таким id!");
        }

    }
    private boolean is_exist_id_airline(int id){
        for (int i = 0; i < airlines.size(); i++) {
            if(airlines.get(i).getCode() == id){
                return true;
            }
        }
        return false;
    }
    private boolean is_exist_name_airline(String name){
        for (int i = 0; i < airlines.size(); i++) {
            if(airlines.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    private void add_new_trip_6(){
        System.out.println("Уведіть id: ");
        int new_id = in.nextInt();
        if(is_exist_id_trip(new_id)){
            System.out.println("Рейс з таким id вже існує!");
        }
        else{
            Scanner in1 = new Scanner(System.in);
            Scanner in2= new Scanner(System.in);
            System.out.println("Уведіть місто відправлення!");
            String new_city_from = in1.nextLine();
            System.out.println("Уведіть місто прибуття!");
            String new_city_to = in2.nextLine();
            System.out.println("Уведіть ціну квитка!");
            Double new_price = in.nextDouble();
            System.out.println("Уведіть id авіакомпанії, до якої потрібно додати рейс!");
            int new_airline_id = in.nextInt();
            Trip trip = new Trip(new_id, new_city_from,new_city_to,new_price,new_airline_id);
            executor.add_new_trip(trip);
            System.out.println("Успішно додано!");
        }
    }
    private void delete_trip_7(){
        System.out.println("Уведіть id рейсу: ");
        int id = in.nextInt();
        if(executor.delete_trip(id)){
            System.out.println("Успішно видалено!");
        }
        else{
            System.out.println("Рейсу з таким id не знайдено!");
        }
    }
    private void edit_trip_8(){
        System.out.println("Уведіть id рейсу, який потрібно редагувати:");
        int id = in.nextInt();
        Scanner in1 = new Scanner(System.in);
        Scanner in2= new Scanner(System.in);
        System.out.println("Уведіть місто відправлення!");
        String new_city_from = in1.nextLine();
        System.out.println("Уведіть місто прибуття!");
        String new_city_to = in2.nextLine();
        System.out.println("Уведіть ціну квитка!");
        Double new_price = in.nextDouble();
        if(is_exist_id_trip(id)){
            for (int i = 0; i < trips.size(); i++) {
                if(trips.get(i).getCode() == id){
                    trips.get(i).setCity_from(new_city_from);
                    trips.get(i).setCity_to(new_city_to);
                    trips.get(i).setPrice(new_price);
                    System.out.println("Успішно редаговано!");
                }
            }
            executor.save_to_file();
        }
        else{
            System.out.println("Рейсу з таким id не знайдено!");
        }
    }
    private void show_all_9(){
        for (int i = 0; i < airlines.size(); i++) {
            System.out.println(airlines.get(i).toString());
            for (int j = 0; j < trips.size(); j++) {
                if(trips.get(j).getNumber_airline() == airlines.get(i).getCode()){
                    System.out.println(trips.get(j).toString());
                }
            }
        }
    }
    private boolean is_exist_id_trip(int id){
        for (int i = 0; i < trips.size(); i++) {
            if(trips.get(i).getCode() == id){
                return true;
            }
        }
        return false;
    }

}
