package cn.ch1tanda.event.controller.game;

import cn.ch1tanda.event.manager.file.FileManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Calendar;

@Controller
@RequestMapping(path = "/game")
public class GameController {

    @Resource
    private FileManager fileManager;

    @RequestMapping(path = "/apex")
    public String apexLegends () {
        return "apex";
    }
}
