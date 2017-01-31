package po;

public class FolderWithBLOBs extends Folder {

    //文件名
    private String file_name;

    //父路径
    private String parent_path;

    //文件层级
    private int level;

    //内容，如果是文件，则填写，否则为空
    private String comment;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}