package util;

import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class YmlUtil {
    /**
     * 获取配置文件中一级节点的内容
     * @param key 节点名称
     * @return 节点中的内容
     */
    @SuppressWarnings("rawtypes")
	public static String getValue(String key){
        String value = null;
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
			Map m = Yaml.loadType(new FileInputStream(new File(path+ "config/config.yml")), HashMap.class);
            value = m.get(key).toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取配置文件中二级节点的内容
     * @param key1 一级节点名称
     * @param key2 二级节点名称
     * @return 二级节点中的内容
     */
    @SuppressWarnings("rawtypes")
	public static String getValue(String key1, String key2){
        String value = null;
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
            Map m = Yaml.loadType(new FileInputStream(new File(path+ "config/config.yml")), HashMap.class);
            m = (Map) m.get(key1);
            value = m.get(key2).toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
    public YmlUtil(){

    }
}
