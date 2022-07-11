package cn.ch1tanda.event.manager.tools.file;

import java.io.File;
import java.util.Date;

public interface FileManager {

    /**
     * 判断对象是否存在
     * @param key 文件key
     * @return 是否存在
     */
    boolean doesObjectExist (String key);

    /**
     * 获取对象访问链接
     * @param key 文件key
     * @return 永久的文件访问URL
     */
    String getObjectUrl (String key);

    /**
     * 获取预签名文件访问URL
     * @param key 文件key 例test/01.jpg
     * @param expire 过期日期
     * @return 预签名的文件访问URL
     */
    String getPreSignedUrl(String key, Date expire);

    /**
     * 上传不超过5GB的文件
     * @param file 文件对象
     * @param key 文件key，如果为common/picture.jpg，则表示picture.jpg上传到/common路径下，如果/common路径不存在，会自动创建路径
     * @return 文件的公共访问地址
     */
    String uploadFile (File file, String key);

    /**
     * 删除文件
     * @param key 文件key
     */
    void deleteFile (String key);
}
