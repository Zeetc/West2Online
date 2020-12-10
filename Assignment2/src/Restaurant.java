import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    private static Object OverdraftBalanceException=new OverdraftBalanceException("进货费用超出拥有余额");
    private static Object IngredientSortOutException=new IngredientSortOutException("果汁或啤酒售完");

    public static void main(String[] args) {
        West2FriedChickenRestaurant restaurant=new West2FriedChickenRestaurant();
        Scanner sc = new Scanner(System.in);
        List<SetMeal> mealList=restaurant.getMealList();
        System.out.println("------------------------------");
        System.out.print("选择模式 : ");
        System.out.print(" 1:进货 ");
        System.out.print(" 2.销售 ");
        System.out.println(" 3.查询余额 ");
        System.out.println("------------------------------");
        while (sc.hasNext()) {
            String model = sc.next();
            if(model.equals("exit")) {
                System.out.println("系统退出");
                break;
            }
            switch (model) {
                //进货模式
                case "1":
                    System.out.println("------------------------------");
                    System.out.println("进货界面");
                    System.out.println("请输入要购入的套餐编号：(仅输入数字)");
                    System.out.println("------------------------------");
                    for (int i = 0; i < mealList.size(); i++) {
                        System.out.println("套餐"+(i+1)+" : "+mealList.get(i).getMealName());
                    }
                    try {
                        int type = sc.nextInt();
                        System.out.println("请输入购买数量");
                        System.out.println("------------------------------");
                        int count = sc.nextInt();
                        SetMeal setMeal = mealList.get(type - 1);
                        String msg = restaurant.purchase(setMeal, count);
                        if(msg.equals("进货失败，余额不足"))throw (Throwable) OverdraftBalanceException;
                        else System.out.println(msg);
                        System.out.println("如需继续购买，请继续输入套餐编号，如需退出该模式，请输入exit");
                        while(sc.hasNext()){
                            String line=sc.next();
                            if("exit".equals(line))break;
                            type=Integer.parseInt(line.trim());
                            System.out.println("请输入购买数量");
                            System.out.println("------------------------------");
                            count=sc.nextInt();
                            setMeal = mealList.get(type - 1);
                            msg = restaurant.purchase(setMeal, count);
                            if(msg.equals("进货失败，余额不足"))throw (Throwable) OverdraftBalanceException;
                            else System.out.println(msg);
                            System.out.println("如需继续购买，请继续输入套餐编号，如需退出该模式，请输入exit");
                        }

                    }catch (NumberFormatException e){
                        System.out.println("输入有误");
                    } catch (InputMismatchException e){
                        System.out.println("输入有误");
                    }catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    System.out.println("------------------------------");
                    break;

                case "2":
                    //销售模式
                    System.out.println("------------------------------");
                    System.out.println("销售界面");
                    for (int i = 0; i < mealList.size(); i++) {
                        System.out.println("套餐"+(i+1)+" : "+mealList.get(i).getMealName());
                    }

                    System.out.println("请输入要购买的套餐编号：(仅输入数字)");
                    System.out.println("------------------------------");
                    try {
                        int need = sc.nextInt() - 1;
                        String msg2 = restaurant.sale(mealList.get(need));
                        if(msg2.equals("仓库里无该套餐"))throw (Throwable) IngredientSortOutException;
                        else System.out.println(msg2);
                        System.out.println("如需继续购买，请继续输入套餐编号，如需退出该模式，请输入exit");
                        while(sc.hasNext()){
                            String line=sc.next();
                            if("exit".equals(line))break;
                            need=Integer.parseInt(line.trim())-1;
                            msg2 = restaurant.sale(mealList.get(need));
                            if(msg2.equals("仓库里无该套餐"))throw (Throwable) IngredientSortOutException;
                            else System.out.println(msg2);
                            System.out.println("------------------------------");
                            System.out.println("如需继续购买，请继续输入套餐编号，如需退出该模式，请输入exit");
                        }


                    }catch (NumberFormatException e){
                        System.out.println("输入有误");
                    } catch (InputMismatchException e){
                        System.out.println("输入有误");
                    }catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                    System.out.println("------------------------------");
                    break;
                case "3":
                    //获取余额
                    System.out.println("------------------------------");
                    System.out.println("当前余额 : " + restaurant.getBalance());
                    System.out.println("------------------------------");
                    break;
                default:
                    //非法输入
                    System.out.println("非法输入");
            }
            System.out.println("------------------------------");
            System.out.print("选择模式 : ");
            System.out.print(" 1:进货 ");
            System.out.print(" 2.销售 ");
            System.out.println(" 3.查询余额 ");
            System.out.println("------------------------------");
        }

        System.out.println("感谢使用本购物系统！");
    }
}
