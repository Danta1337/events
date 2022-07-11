package cn.ch1tanda.event.test.manager.tools;

import cn.ch1tanda.event.manager.tools.file.FileManager;
import cn.ch1tanda.event.test.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.File;
import java.util.Calendar;

public class FileManagerTest extends BaseTest {

    @Resource
    private FileManager fileManager;

    @Test
    public void doesObjectExistTest() {
        boolean b = fileManager.doesObjectExist("cute.jpeg");
        System.out.println(b);
    }

    @Test
    public void getObjectUrlTest() {
        String objectUrl = fileManager.getObjectUrl("cute.jpeg");
        System.out.println(objectUrl);
    }

    @Test
    public void getPreSignedUrlTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        String preSignedUrl = fileManager.getPreSignedUrl("cute.jpeg", calendar.getTime());
        System.out.println(preSignedUrl);
    }

    @Test
    public void uploadFileTest() {
        File file = new File("/Users/ch1tanda/Downloads/cute.jpeg");
        String objectUrl = fileManager.uploadFile(file, "loli/" + file.getName());
        System.out.println(objectUrl);
    }

    @Test
    public void deleteFileTest() {
        fileManager.deleteFile("cute.jpeg");
    }
}
