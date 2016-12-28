package service;

import po.FolderWithBLOBs;

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
}
