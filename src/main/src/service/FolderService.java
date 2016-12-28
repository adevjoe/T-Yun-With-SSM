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
}
