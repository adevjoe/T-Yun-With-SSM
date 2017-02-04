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

    @Override
    public FolderWithBLOBs getFileDetail(FolderWithBLOBs folderWithBLOBs) throws Exception {
        return folderMapper.getFileDetail(folderWithBLOBs);
    }

    @Override
    public void delFile(int id) throws Exception {
        if (folderMapper.getInfoById(id).getIs_dir()>0){//递归删除
            FolderWithBLOBs target = folderMapper.getInfoById(id);
            target.setParent_path(target.getFile_name());
            List<FolderWithBLOBs> list = listFolder(target);
            folderMapper.delFile(id);//最后要删除遍历的文件夹
            for (FolderWithBLOBs l : list) {
                delFile(l.getId());
            }
        }{
            folderMapper.delFile(id);
        }
    }

    @Override
    public FolderWithBLOBs getInfoById(int id) throws Exception {
        return folderMapper.getInfoById(id);
    }
}
