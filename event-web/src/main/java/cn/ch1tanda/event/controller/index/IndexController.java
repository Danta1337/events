package cn.ch1tanda.event.controller.index;

import cn.ch1tanda.event.controller.BasicController;
import cn.ch1tanda.event.service.generic.config.ConfigService;
import cn.ch1tanda.event.service.generic.config.constant.enums.ConfigTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class IndexController extends BasicController {

    @Resource
    private ConfigService configService;

    @RequestMapping(path = {"/index", "/"})
    public String index (Model model) {
        model.addAttribute("introduce"
                , configService.getConfigValueByTypeAndKey(ConfigTypeEnum.INTRODUCE.getCode()
                        , this.URIAdaptor(this.getRequestPath())));
        return "index";
    }


    @RequestMapping(path = {"/favicon.ico", "/apple-touch-icon.png", "/apple-touch-icon-precomposed.png"})
    public String getFavicon () {
        // 页面图标，重定向至静态资源
        return "redirect:/static/icon/favicon.ico";
    }

    private String URIAdaptor(String URI) {
        if (URI.equals("/")) {
            return "/index";
        }
        return URI;
    }
}
