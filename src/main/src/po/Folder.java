package po;

public class Folder {

    //文件或文件夹唯一标识
    private Integer id;

    //文件名
    private String file_name;

    //是否为文件夹  0为文件  1为文件夹
    private Integer isDir;

    //父路径
    private String parent_path;

    //内容，如果是文件，则填写，否则为空
    private String comment;

    //创建时间
    private Integer createTime;

    //最后更新时间
    private Integer updateTime;

    //操作的用户ID
    private Integer userId;

    //文件大小 可以为空
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsDir() {
        return isDir;
    }

    public void setIsDir(Integer isDir) {
        this.isDir = isDir;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getParent_path() {
        return parent_path;
    }

    public void setParent_path(String parent_path) {
        this.parent_path = parent_path;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}