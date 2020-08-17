import java.time.LocalDate;

public class Good {
    private Type type;
    private String fruit;
    private int quantity;
    private LocalDate date;

    public Good(Type type, String fruit, int quantity, LocalDate date) {
        this.type = type;
        this.fruit = fruit;
        this.quantity = quantity;
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public String getFruit() {
        return fruit;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Good{" +
                "type=" + type +
                ", fruit='" + fruit + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
