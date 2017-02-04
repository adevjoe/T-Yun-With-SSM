package mapper;

import java.util.List;
import po.FolderWithBLOBs;

public interface FolderMapper {

    //插入文件记录
    void addFolder(FolderWithBLOBs folderWithBLOBs) throws Exception;

    //获取文件列表
    List<FolderWithBLOBs> listFolder(FolderWithBLOBs folderWithBLOBs) throws Exception;

    //获取当前路径的信息
    FolderWithBLOBs getPathInfo(FolderWithBLOBs folderWithBLOBs) throws Exception;

    //根据id获取文件详细信息
    FolderWithBLOBs getFileDetail(FolderWithBLOBs folderWithBLOBs) throws Exception;

    //删除文件
    void delFile(int id) throws Exception;

    //根据id查询文件信息
    FolderWithBLOBs getInfoById(int id) throws Exception;

}