package po;

public class Folder {

    //文件或文件夹唯一标识
    private Integer id;

    //是否为文件夹  0为文件  1为文件夹
    private Integer is_dir;

    //创建时间
    private long create_time;

    //最后更新时间
    private long update_time;

    //操作的用户ID
    private Integer user_id;

    //文件大小 可以为空
    private long size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIs_dir() {
        return is_dir;
    }

    public void setIs_dir(Integer is_dir) {
        this.is_dir = is_dir;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}