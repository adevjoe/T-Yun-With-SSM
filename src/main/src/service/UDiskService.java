package service;

import po.UDisk;

import java.util.List;

/**
 * Created by joe on 17-3-3.
 */
public interface UDiskService {

    /**
     * 添加文件
     * @param uDisk 文件信息
     * @throws Exception 异常
     */
    void addFile(UDisk uDisk) throws Exception;

    /**
     * 根据联系标识查找文件
     * @param mark 文件标识，可以是用户手机号，QQ等等
     * @return 文件列表
     * @throws Exception 异常
     */
    List<UDisk> getFilesByMark(String mark) throws Exception;

    /**
     * 根据提取码获取文件
     * @param code 提取码
     * @return 文件信息
     * @throws Exception 异常
     */
    UDisk getFileByCode(String code) throws Exception;
}
