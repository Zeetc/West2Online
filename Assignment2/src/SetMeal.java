public class SetMeal {
    private String mealName;
    private double price;
    private String friedChicken;
    private Drinks drink;

    public SetMeal(String mealName, double price, String friedChicken, Drinks drink) {
        this.mealName = mealName;
        this.price = price;
        this.friedChicken = friedChicken;
        this.drink = drink;
    }


    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFriedChicken() {
        return friedChicken;
    }

    public void setFriedChicken(String friedChicken) {
        this.friedChicken = friedChicken;
    }

    public Drinks getDrink() {
        return drink;
    }

    public void setDrink(Drinks drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "SetMeal{" +
                "mealName='" + mealName + '\'' +
                ", price=" + price +
                ", friedChicken='" + friedChicken + '\'' +
                ", drink=" + drink +
                '}';
    }

}
