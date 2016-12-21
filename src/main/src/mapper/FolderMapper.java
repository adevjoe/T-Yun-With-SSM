package mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import po.Folder;
import po.FolderExample;
import po.FolderWithBLOBs;

public interface FolderMapper {
    int countByExample(FolderExample example);

    int deleteByExample(FolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FolderWithBLOBs record);

    int insertSelective(FolderWithBLOBs record);

    List<FolderWithBLOBs> selectByExampleWithBLOBs(FolderExample example);

    List<Folder> selectByExample(FolderExample example);

    FolderWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FolderWithBLOBs record, @Param("example") FolderExample example);

    int updateByExampleWithBLOBs(@Param("record") FolderWithBLOBs record, @Param("example") FolderExample example);

    int updateByExample(@Param("record") Folder record, @Param("example") FolderExample example);

    int updateByPrimaryKeySelective(FolderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(FolderWithBLOBs record);

    int updateByPrimaryKey(Folder record);
}