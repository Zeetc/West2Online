import java.time.LocalDateTime;
import java.util.*;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant{

    private double balance=100000;
    private static final List<Drinks> beerList;
    private static final List<Drinks> juiceList;
    private static final List<SetMeal> mealList;

    private static List<SetMeal> store;
    private static List<Drinks> juiceStore;
    private static List<Drinks> beerStore;


    //初始化的数据结构均选择ArrayList
    //菜单初始化之后不再改变，并且后续添加商品可以很方便的添加，故用
    //仓库列表是因为需要经常遍历仓库list并且删除某些商品，若用linkedList或者其他虽然删除耗时短，但操作较为麻烦

    //静态初始化
    static {
        beerList=new ArrayList<>();//啤酒列表
        juiceList=new ArrayList<>();//果汁列表
        mealList=new ArrayList<>();//套餐列表

        Drinks beer1=new Beer("雪花",5.12, LocalDateTime.now().toLocalDate());
        Drinks beer2=new Beer("肾宝",999.9, LocalDateTime.now().toLocalDate());
        Drinks beer3=new Beer("无水乙醇",666.66, LocalDateTime.now().toLocalDate());
        beerList.add(beer1);
        beerList.add(beer2);
        beerList.add(beer3);

        Drinks juice1=new Juice("恒河水",6.6,LocalDateTime.now().toLocalDate());
        Drinks juice2=new Juice("肥宅水",9.99,LocalDateTime.now().toLocalDate());
        juiceList.add(juice1);
        juiceList.add(juice2);

        SetMeal setMeal1=new SetMeal("致死量",666.66+1,"老八秘制炸鸡",beer3);
        SetMeal setMeal2=new SetMeal("精神小伙",6.6+1,"旺仔炸鸡",juice1);
        SetMeal setMeal3=new SetMeal("网抑套餐",5.12+1,"憨包炸鸡",beer1);
        mealList.add(setMeal1);
        mealList.add(setMeal2);
        mealList.add(setMeal3);

        store=new ArrayList<>();
        juiceStore=new ArrayList<>();
        beerStore=new ArrayList<>();
    }

    /**
     * 售卖套餐
     * @param setMeal 销售的套餐种类
     * @return
     */
    @Override
    public String sale(SetMeal setMeal) {
        checkStore();
        String msg;
        boolean haveMeal=false;
        for(int i=0;i< store.size();i++){
            SetMeal meal=store.get(i);
            if(meal.getMealName().equals(setMeal.getMealName())){
                Drinks drinks=store.get(i).getDrink();
                boolean isBeer=drinks instanceof Beer;
                if(isBeer)use((Beer) drinks);
                else use((Juice) drinks);
                haveMeal=true;
                store.remove(i);
                break;
            }
        }
        if(haveMeal){
            msg="出售成功";
            balance+=setMeal.getPrice();
        }else msg="仓库里无该套餐";
        return msg;
    }

    /**
     *  商店进货
     * @param setMeal 套餐种类
     * @param count 套餐数量
     * @return
     */
    @Override
    public String purchase(SetMeal setMeal,int count) {
        checkStore();
        Object drinks= setMeal.getDrink();
        //instanceof判断是否是啤酒类
        boolean isBeer=drinks instanceof Beer;
        Drinks drink;
        if(isBeer)drink=(Beer)drinks;
        else drink=(Juice)drinks;
        double cost=drink.cost*count;
        String msg;
        if(balance>=cost){
            balance-=cost;
            for (int i = 0; i < count; i++) {
                store.add(setMeal);
                if(isBeer)beerStore.add(drink);
                else juiceStore.add(drink);
            }
            msg="进货成功";
        }else msg="进货失败，余额不足";
        return msg;
    }

    /**
     * 遍历商店list，清除所有过期商品
     */
    public void checkStore(){
        for (int i = 0; i < store.size(); i++) {
            if(store.get(i).getDrink().isOverdue()){
                Drinks drinks=store.get(i).getDrink();
                boolean isBeer=drinks instanceof Beer;
                if(isBeer)use((Beer) drinks);
                else use((Juice) drinks);
                store.remove(i);
            }
        }
    }

    /**
     * 查询商店余额
     * @return 返回商店余额
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 查询套餐列表
     * @return 返回套餐列表
     */
    public List<SetMeal> getMealList() {
        return mealList;
    }


    public void use(Beer beer){
        for (int i = 0; i < beerStore.size(); i++) {
            if(beerStore.get(i).name.equals(beer.name)){
                beerStore.remove(i);
                break;
            }
        }
    }


    public void use(Juice juice){
        for (int i = 0; i < juiceStore.size(); i++) {
            if(juiceStore.get(i).name.equals(juice.name)){
                juiceStore.remove(i);
                break;
            }
        }
    }

}
