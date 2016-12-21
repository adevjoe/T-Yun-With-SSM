package exception;

/**
 * Created by Joe_C on 2016/12/2.
 */
public class CustomException extends Exception {

    //异常信息
    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(String message){
        super(message);
        this.message = message;

    }
}
