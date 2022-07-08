package cn.ch1tanda.event.controller;

import cn.ch1tanda.event.manager.tools.file.FileManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class DefaultController {

    @Resource
    private FileManager fileManager;

    @RequestMapping(path = {"index", "/"})
    public String index () {
        return "index";
    }

}
