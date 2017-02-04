package service;

import po.FolderWithBLOBs;

import java.util.List;

/**
 * Created by Joe_C on 2016/12/26.
 */
public interface FolderService {

    /**
     * 存储上传的文件信息
     * @param folderWithBLOBs
     * @throws Exception
     */
    void addFolder(FolderWithBLOBs folderWithBLOBs) throws Exception;

    /**
     * 获取文件列表
     * @param folderWithBLOBs
     * @return 文件集合
     * @throws Exception
     */
    List<FolderWithBLOBs> listFolder(FolderWithBLOBs folderWithBLOBs) throws Exception;

    /**
     * 获取当前路径的信息
     * @param folderWithBLOBs
     * @return 当前路径的信息
     * @throws Exception
     */
    FolderWithBLOBs getPathInfo(FolderWithBLOBs folderWithBLOBs) throws Exception;

    /**
     * 根据id获取文件详细信息
     * @param folderWithBLOBs id
     * @return 文件详细信息
     * @throws Exception
     */
    FolderWithBLOBs getFileDetail(FolderWithBLOBs folderWithBLOBs) throws Exception;

    /**
     * 删除文件
     * @param id 文件id
     * @throws Exception
     */
    void delFile(int id) throws Exception;

    /**
     * 根据id查询文件信息
     * @param id id
     * @return 文件信息
     * @throws Exception
     */
    FolderWithBLOBs getInfoById(int id) throws Exception;
}
