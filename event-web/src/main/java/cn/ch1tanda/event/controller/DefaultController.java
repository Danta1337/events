package cn.ch1tanda.event.controller;

import cn.ch1tanda.event.convention.exception.ServiceException;
import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.manager.file.FileManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DefaultController {

    @Resource
    private FileManager fileManager;

    @RequestMapping(path = {"index", "/"})
    public String index () {
        return "index";
    }

}
