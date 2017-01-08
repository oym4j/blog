package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.application.SiteConfigService;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xieqiang on 2017/1/4.
 */
@RestController
@RequestMapping("/admin/siteconfig")
public class SiteConfigManageController {

    @Autowired
    private SiteConfigService siteConfigService;

    @PostMapping("/update")
    public RestResponse update(@RequestBody Map<String,String> blog){
        siteConfigService.update(blog);
        return RestResponse.ok();
    }

    @GetMapping
    public RestResponse get(){
        return RestResponse.ok(siteConfigService.siteConfig());
    }
}
