package mapper;

import po.UDisk;

import java.util.List;

/**
 * Created by joe on 17-3-3.
 */
public interface UDiskMapper {

    //添加文件
    void addFile(UDisk uDisk) throws Exception;

    //根据联系标识查找文件
    List<UDisk> getFilesByMark(String mark) throws Exception;

    //根据提取码获取文件
    UDisk getFileByCode(int id) throws Exception;

}
