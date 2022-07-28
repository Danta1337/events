package cn.ch1tanda.event.controller;

import cn.ch1tanda.event.service.generic.config.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BasicController {

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private HttpServletResponse httpServletResponse;

    public String getRequestPath () {
        return httpServletRequest.getRequestURI();
    }
}
