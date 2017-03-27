package po;

/**
 * Created by joe on 17-3-3.
 * 移动 U 盘 po 类
 */
public class UDisk {

    // id
    private int id;

    // 联系标识，用于寻找之前上传的文件
    private String mark;

    // 提取码
    private String code;

    // url
    private String url;

    // 创建时间
    private long create_time;

    public UDisk() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
