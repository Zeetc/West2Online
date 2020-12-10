public interface FriedChickenRestaurant {
    //自定义接口
    //售卖
    abstract String sale(SetMeal setMeal);
    //进货
    abstract String purchase(SetMeal setMeal,int count);

}
