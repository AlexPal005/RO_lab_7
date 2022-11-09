package Part_2;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Airport airport;
    private Scanner in;
    public Menu(Airport airport){
        this.airport = airport;
        in = new Scanner(System.in);
    }
    private void show_menu(){
        System.out.println("----------Меню----------");
        System.out.println("1. Додати нову авіакомпанію");
        System.out.println("2. Видалити авіакомпанію");
        System.out.println("3. Додати рейс");
        System.out.println("4. Видалити рейс");
        System.out.println("5. Редагувати рейс");
        System.out.println("6. Знайти рейс за його id");
        System.out.println("7. Отримати всі авіакомпанії з їх рейсами");
        System.out.println("8. Отримати всі авіакомпанії");
        System.out.println("9. Отримати рейси заданої авіакомпанії");
    }
    public void create_menu(){
        while(true) {
            show_menu();
            int switch_val;
            switch_val = in.nextInt();
            switch (switch_val) {
                case (1):
                    add_airline();
                    break;
                case (2):
                    delete_airline();
                    break;
                case (3):
                    add_trip();
                    break;
                case (4):
                    delete_trip();
                    break;
                case (5):
                    edit_trip();
                    break;
                case (6):
                    search_trip_by_id();
                    break;
                case (7):
                    get_all_airl_trips();
                    break;
                default:
                    System.out.println("Помилка! Уведіть номер пункту меню!");
                    break;
            }
        }
    }
    private void add_airline(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(id <= 0 ){
            System.out.println();
        }
        System.out.println("Уведіть назву: ");
        String name = in.nextLine();
        if(!airport.add_new_airline(id, name)){
            System.out.println("Помилка! Авіакомпанія не додана!");
        }
        else{
            System.out.println("Видалено!");
        }
    }
    private void delete_airline(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(!airport.delete_airline_by_id(id)){
            System.out.println("Помилка! Авіакомпанія не видалена!");
        }
        else{
            System.out.println("Видалено!");
        }
    }
    private void add_trip(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.println("Уведіть місто відправлення: ");
        String city_from = in.nextLine();
        System.out.println("Уведіть місто прибуття: ");
        String city_to = in.nextLine();
        System.out.println("Уведіть ціну квитка: ");
        double price = in.nextDouble();
        in.nextLine();
        System.out.println("Уведіть id авіакомпанії:");
        int id_airline = in.nextInt();
        in.nextLine();
        if(airport.add_trip(id,city_to,city_from,price,id_airline)){
            System.out.println("Додано!");
        }
        else{
            System.out.println("Помилка!");
        }

    }
    private void delete_trip(){
        System.out.println("Уведіть id: ");
        int id = in.nextInt();
        in.nextLine();
        if(!airport.delete_trip_by_id(id)){
            System.out.println("Помилка! Авіакомпанія не видалена!");
        }
        else{
            System.out.println("Видалено!");
        }
    }
    private void edit_trip(){
        System.out.println("Уведіть id рейсу, який потрібно редагувати: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.println("Уведіть місто відправлення: ");
        String city_from = in.nextLine();
        System.out.println("Уведіть місто прибуття: ");
        String city_to = in.nextLine();
        System.out.println("Уведіть ціну квитка: ");
        double price = in.nextDouble();
        in.nextLine();
        if(airport.edit_trip(id,city_to,city_from,price)){
            System.out.println("Успішно редаговано!");
        }
        else{
            System.out.println("Помилка!");
        }
    }
    private void search_trip_by_id(){
        System.out.println("Уведіть id рейсу, який потрібно показати: ");
        int id = in.nextInt();
        in.nextLine();
        ResultSet res = airport.show_trip(id);
        try{
            if(res.next()){
                int id_res = res.getInt("id_trip");
                String city_to =  res.getString("city_to");
                String city_from =  res.getString("city_from");
                double price = res.getDouble("price");
                int id_airline = res.getInt("id_airl");
                System.out.println("Id:" + id_res);
                System.out.println("Місто прибуття: " + city_to);
                System.out.println("Місто відправлення: " + city_from);
                System.out.println("Ціна квитка: " + price);
                System.out.println("Id авіакомпанії : " + id_airline);
            }
            else{
                System.out.println("Помилка!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void get_all_airl_trips(){
        ResultSet res = airport.get_all();
        try{
            res.next();
            while(res.next()){
                int id_airline = res.getInt("id_airline");
                String name = res.getString("name");
                System.out.println(id_airline + " " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
