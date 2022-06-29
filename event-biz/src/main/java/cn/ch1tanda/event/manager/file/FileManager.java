package cn.ch1tanda.event.manager.file;

import java.text.ParseException;
import java.util.Date;

public interface FileManager {

    /**
     * 获取预签名文件访问URL
     * @param key 文件key 例test/01.jpg
     * @param expire 过期日期字符串格式化 yyyyMMddHHmmss
     * @return 预签名的文件访问URL
     */
    String getPreSignedURL (String key, String expire) throws ParseException;

    /**
     * 获取预签名文件访问URL
     * @param key 文件key 例test/01.jpg
     * @param expire 过期日期
     * @return 预签名的文件访问URL
     */
    String getPreSignedURL (String key, Date expire);
}
