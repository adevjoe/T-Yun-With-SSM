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
    //创建上传对象
    private Zone z = Zone.autoZone();
    private Configuration c = new Configuration(z);
    private UploadManager uploadManager = new UploadManager(c);
    private BucketManager bucketManager = new BucketManager(auth,c);
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken(String bucket){
        return auth.uploadToken(bucket);
    }

    /**
     * 上传文件
     * @param localPath 文件在服务器的本地路径
     * @param key 文件在七牛云中存储使用的key值
     * @param bucket 存储空间
     */
    public void upLoad(String localPath, String key, String bucket){
        try {
            //调用put方法上传
            Response res = uploadManager.put(localPath, key, getUpToken(bucket));
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
     * @param bucket 存储空间
     */
    public void deleteFile(String key, String bucket){
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
        }
    }
}
