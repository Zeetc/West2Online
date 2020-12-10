import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Juice extends Drinks{
    private static final int quality=2;

    public Juice(String name, Double cost, LocalDate manufacture) {
        super(name, cost, manufacture, quality);
    }

    @Override
    public boolean isOverdue() {
        long days=this.manufacture.until(LocalDateTime.now(), ChronoUnit.DAYS);
        return days>quality;
    }

    @Override
    public String toString() {
        return "Juice{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", manufacture=" + manufacture +
                ", quality=" + quality +
                '}';
    }

    @Override
    public double getCost() {
        return this.cost;
    }
}
