package cn.ch1tanda.event.controller;

import cn.ch1tanda.event.manager.file.FileManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DefaultController {

    @Resource
    private FileManager fileManager;

    private static List<String> fileKeyList = Arrays.asList("test/01.jpg", "test/02.jpg", "test/03.jpg");

    @RequestMapping(path = {"index", "/"})
    public String index () {
        /**
        // 获取轮播图片地址
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        List<String> fileUrl = fileKeyList.stream().map(item -> fileManager.getPreSignedURL(item, tomorrow.getTime())).collect(Collectors.toList());
        model.addAttribute("carousel", fileUrl);
         **/
        return "index";
    }
}
