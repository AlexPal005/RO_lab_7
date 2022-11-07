package Part_1;

public class Airline {
    private int code;
    private String name;
    public Airline(int code, String name){
        this.code = code;
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Id: " + code +"\n" + "Назва: " + name;
    }
}
