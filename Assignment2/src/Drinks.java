import java.time.LocalDate;

public abstract class Drinks {
    //定义抽象类
    protected String name;
    protected double cost;
    protected LocalDate manufacture;
    protected int quality;

    public Drinks(String name, double cost, LocalDate manufacture, int quality) {
        this.name = name;
        this.cost = cost;
        this.manufacture = manufacture;
        this.quality = quality;
    }

    public abstract boolean isOverdue();

    public abstract String toString();

    public abstract double getCost();
}
