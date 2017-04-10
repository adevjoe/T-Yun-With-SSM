package impl;

import mapper.UDiskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import po.UDisk;
import service.UDiskService;

import java.util.List;

/**
 * Created by joe on 17-3-3.
 * 移动u盘功能实现类
 */
public class UDiskImpl implements UDiskService {

    @Autowired
    private UDiskMapper uDiskMapper;

    @Override
    public void addFile(UDisk uDisk) throws Exception {
        uDiskMapper.addFile(uDisk);
    }

    @Override
    public List<UDisk> getFilesByMark(String mark) throws Exception {
        return uDiskMapper.getFilesByMark(mark);
    }

    @Override
    public UDisk getFileByCode(int code) throws Exception {
        return uDiskMapper.getFileByCode(code);
    }
}
