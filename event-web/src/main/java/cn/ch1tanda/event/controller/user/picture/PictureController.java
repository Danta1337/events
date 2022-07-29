package cn.ch1tanda.event.controller.user.picture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/picture")
public class PictureController {

    @RequestMapping("/pixiv")
    public String toPicturePixiv() {
        return "user/picture/pixiv";
    }
}
