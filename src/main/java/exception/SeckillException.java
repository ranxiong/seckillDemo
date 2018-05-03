package exception;

/**
 * 秒杀相关异常
 * Created by ranxiong on 2016/6/20.
 */
public class SeckillException extends  RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
