public class OverdraftBalanceException extends RuntimeException{
    //自定义异常类
    public OverdraftBalanceException(){
        super("进货费用超出拥有余额");
    }

    public OverdraftBalanceException(String message){
        super(message);
    }
}
