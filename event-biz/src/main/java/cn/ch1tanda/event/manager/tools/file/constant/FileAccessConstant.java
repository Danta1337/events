package cn.ch1tanda.event.manager.tools.file.constant;

import org.springframework.beans.factory.annotation.Value;

//@Component
public class FileAccessConstant {

    public static String secretId;

    public static String secretKey;

    public static String region;

    public static String bucketName;

    @Value("${file.access.secretId}")
    private void setSecretId(String secretId) {
        FileAccessConstant.secretId = secretId;
    }

    @Value("${file.access.secretKey}")
    private void setSecretKey(String secretKey) {
        FileAccessConstant.secretKey = secretKey;
    }

    @Value("${file.access.region}")
    public void setRegion(String region) {
        FileAccessConstant.region = region;
    }

    @Value("${file.access.bucketName}")
    public void setBucketName(String bucketName) {
        FileAccessConstant.bucketName = bucketName;
    }
}
