package cn.ch1tanda.event.controller.file;

import cn.ch1tanda.event.manager.tools.file.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping(path = "/file")
public class FileController {

    @Resource
    private FileManager fileManager;

    @GetMapping(path = "/get")
    public String getFile (@RequestParam("key") String fileKey, @RequestParam("expire") Date expire) throws ParseException {
        return fileManager.getPreSignedUrl(fileKey, expire);
    }
}
