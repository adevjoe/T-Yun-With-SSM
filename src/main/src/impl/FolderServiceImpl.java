package impl;

import mapper.FolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import po.FolderWithBLOBs;
import service.FolderService;

import java.util.List;

/**
 * Created by Joe_C on 2016/12/26.
 */
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderMapper folderMapper;

    @Override
    public void addFolder(FolderWithBLOBs folderWithBLOBs) throws Exception {
        folderMapper.addFolder(folderWithBLOBs);
    }

    @Override
    public List<FolderWithBLOBs> listFolder(FolderWithBLOBs folderWithBLOBs) throws Exception {
        return folderMapper.listFolder(folderWithBLOBs);
    }

    @Override
    public FolderWithBLOBs getPathInfo(FolderWithBLOBs folderWithBLOBs) throws Exception {
        return folderMapper.getPathInfo(folderWithBLOBs);
    }
}
