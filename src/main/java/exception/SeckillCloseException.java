package exception;

/**
 * 秒杀关闭异常
 * Created by ranxiong on 2016/6/20.
 */
public class SeckillCloseException extends  SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }


}
