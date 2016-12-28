package util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * Created by Joe_C on 2016/12/26.
 * 七牛云工具类
 */
public class QiNiu {
    private static final String ACCESS_KEY = YmlUtil.getValue("ACCESS_KEY");
    private static final String SECRET_KEY = YmlUtil.getValue("SECRET_KEY");
    //密钥配置
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //要上传的空间
    private String bucket = YmlUtil.getValue("bucket");
    //创建上传对象
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);
    private UploadManager uploadManager = new UploadManager(c);
    BucketManager bucketManager = new BucketManager(auth,c);
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken(){
        return auth.uploadToken(bucket);
    }

    /**
     * 上传文件
     * @param path 文件服务器路径
     * @param key 文件在七牛云中存储使用的key值
     */
    public void upLoad(String path,String key){
        try {
            //调用put方法上传
            Response res = uploadManager.put(path, key, getUpToken());
            //打印返回信息
            System.out.printf(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.printf(r.toString());
            try {
                System.out.printf(r.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     * @param key 文件的key
     */
    public void deleteFile(String key){
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
        }
    }

    /**
     * 获取文件下载链接
     * @param key 文件的key
     * @return 文件的下载链接
     */
    public String getDownloadURL(String key){
        return "http://" + YmlUtil.getValue("chain_domain") + "/" + key;
    }
}
