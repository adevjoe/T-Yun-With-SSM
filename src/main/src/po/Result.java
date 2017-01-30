package po;

/**
 * Created by Joe_C on 2017/1/3.
 * API 返回的结果
 */
public class Result {
    //响应码
    private int responseCode;

    //返回消息
    private String msg;

    //返回的对象
    private Object object;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
