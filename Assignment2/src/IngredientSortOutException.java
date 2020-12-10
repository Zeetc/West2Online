
public class IngredientSortOutException extends RuntimeException {
    //自定义异常类
    public IngredientSortOutException() {
        super("果汁或啤酒售完");
    }

    public IngredientSortOutException(String message){
        super(message);
    }

}
