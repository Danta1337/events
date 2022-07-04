package cn.ch1tanda.event.manager.file.impl;

import cn.ch1tanda.event.manager.file.FileManager;
import cn.ch1tanda.event.manager.file.constant.FileAccessConstant;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class FileManagerImpl implements FileManager {

    private static COSClient cosClient;

    private static final String DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

    @Override
    public String getPreSignedURL(String key, String expire) throws ParseException {
        return this.getPreSignedURL(key, new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(expire));
    }

    @Override
    public String getPreSignedURL(String key, Date expire) {
        return getCosClient().generatePresignedUrl(FileAccessConstant.bucketName, key, expire).toString();
    }

    private static COSClient getCosClient () {
        if (Objects.isNull(cosClient)) {
            BasicCOSCredentials cred = new BasicCOSCredentials(FileAccessConstant.secretId, FileAccessConstant.secretKey);
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setRegion(new Region(FileAccessConstant.region));
            clientConfig.setHttpProtocol(HttpProtocol.https);
            cosClient = new COSClient(cred, clientConfig);
        }
        return cosClient;
    }


}
