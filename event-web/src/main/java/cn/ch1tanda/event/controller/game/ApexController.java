package cn.ch1tanda.event.controller.game;

import cn.ch1tanda.event.convention.response.Result;
import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.manager.game.apex.constant.enums.ApexPlatformEnum;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPlayerStatisticsQueryResp;
import cn.ch1tanda.event.manager.game.apex.resp.ApexPredatorResp;
import cn.ch1tanda.event.service.game.apex.ApexLegendsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(path = "/game/apex")
public class ApexController {

    @Resource
    private ApexLegendsService apexLegendsService;

    @RequestMapping(path = "")
    public String init(Model model) {
        model.addAttribute("mapRotation", apexLegendsService.getMapRotationInfo());
        model.addAttribute("crafting", apexLegendsService.getDailyOrWeeklyCraftingContent());
        model.addAttribute("predators", apexLegendsService.getApexPredatorInfo());
        return "game/apex";
    }

    @ResponseBody
    @RequestMapping(path = "/query.json", method = RequestMethod.GET)
    public Result<ApexPlayerStatisticsQueryResp.BasicInfo> queryPlayerStatisticsInfo (@RequestParam("username") String username,
                                                                                      @RequestParam("platform") ApexPlatformEnum platform) {
        return Results.success(apexLegendsService.getPlayerStatisticsInfo(username, platform));
    }

    @ResponseBody
    @RequestMapping(path = "/predator.json", method = RequestMethod.GET)
    public Result<ApexPredatorResp> queryPredatorInfo() {
        return Results.success(apexLegendsService.getApexPredatorInfo());
    }
}
