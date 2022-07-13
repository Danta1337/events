package cn.ch1tanda.event.manager.tools.file.impl;

import cn.ch1tanda.event.manager.tools.file.FileManager;
import cn.ch1tanda.event.manager.tools.file.constant.FileAccessConstant;
import cn.ch1tanda.event.mapper.ConfigMapper;
import cn.ch1tanda.event.mapper.dataobject.ConfigDO;
import cn.ch1tanda.event.utils.exception.AssertUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO 定时关闭CosClient
@Component
public class FileManagerImpl implements FileManager {

    @Resource
    private ConfigMapper configMapper;

    private static COSClient cosClient;

    @Override
    public boolean doesObjectExist(String key) {
        AssertUtils.isNotBlank(key, "File key can not be null!");
        return this.getCosClient().doesObjectExist(FileAccessConstant.BUCKET_NAME, key);
    }

    @Override
    public String getObjectUrl(String key) {
        AssertUtils.isNotBlank(key, "File key can not be null!");
        return this.getCosClient().getObjectUrl(FileAccessConstant.BUCKET_NAME, key).toString();
    }

    @Override
    public String getPreSignedUrl(String key, Date expirationTime) {
        AssertUtils.isNotBlank(key, "File key can not be null!");
        AssertUtils.afterNow(expirationTime, "Expiration time must be after now");
        return getCosClient().generatePresignedUrl(FileAccessConstant.BUCKET_NAME, key, expirationTime).toString();
    }

    @Override
    public String uploadFile(File file, String key) {
        checkCosClient();
        AssertUtils.isTrue(Objects.nonNull(file), "File can not be null!");
        AssertUtils.isNotBlank(key, "File key can not be null!");
        PutObjectRequest putRequest = new PutObjectRequest(FileAccessConstant.BUCKET_NAME, key, file);
        PutObjectResult putObjectResult = this.getCosClient().putObject(putRequest);
        return this.getObjectUrl(key);
    }

    @Override
    public void deleteFile(String key) {
        AssertUtils.isNotBlank(key, "File key can not be null!");
        this.getCosClient().deleteObject(FileAccessConstant.BUCKET_NAME, key);
    }

    private COSClient getCosClient() {
        if (Objects.isNull(cosClient)) {
            initCosClient();
        }
        return cosClient;
    }

    private void checkCosClient() {
        if (Objects.isNull(cosClient)) {
            initCosClient();
        }
    }

    private void initCosClient() {
        // 第一次请求时初始化COSClient，后续基本不会再次初始化，所以不考虑缓存配置
        Map<String, String> fileConfigs = configMapper.selectAllConfigKeyAndConfigValueByConfigType(FileAccessConstant.FILE_CONFIG_TYPE)
                .stream().collect(Collectors.toMap(ConfigDO::getConfigKey, ConfigDO::getConfigValue));
        FileAccessConstant.REGION = fileConfigs.get(FileAccessConstant.REGION_CONFIG_KEY);
        FileAccessConstant.BUCKET_NAME = fileConfigs.get(FileAccessConstant.BUCKET_NAME_CONFIG_KEY);
        BasicCOSCredentials cred = new BasicCOSCredentials(fileConfigs.get(FileAccessConstant.SECRET_ID_CONFIG_KEY)
                , fileConfigs.get(FileAccessConstant.SECRET_KEY_CONFIG_KEY));
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(FileAccessConstant.REGION));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }
}
