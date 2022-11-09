package Part_2;

public class Main {
    public static void main(String args[]) {
        new Main().start();
    }
    public void start(){
        try{
            Airport airport = new Airport();
            new Menu(airport).create_menu();
            airport.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
