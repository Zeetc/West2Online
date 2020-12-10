import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Beer extends Drinks{

    private float degrees;

    private static final int quality=30;

    public Beer(String name, Double cost, LocalDate manufacture) {
        super(name, cost, manufacture,quality);
    }

    @Override
    public boolean isOverdue() {
        long days=this.manufacture.until(LocalDateTime.now(), ChronoUnit.DAYS);
        return days>quality;
    }

    public float getDegrees() {
        return degrees;
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "degrees=" + degrees +
                ", name='" + name + '\'' +
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
