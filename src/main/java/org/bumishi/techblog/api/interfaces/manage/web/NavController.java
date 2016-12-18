package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.interfaces.manage.facade.NavFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiang.xie
 * @date 2016/9/18
 */
@RestController
@RequestMapping("/admin/nav")
public class NavController {

    @Autowired
    protected NavFacade navFacade;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse create(NavigationCreateCommand menu) {
        navFacade.add(menu);
        return RestResponse.ok();
    }


    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public RestResponse modify(@PathVariable("id") String id, NavigationUpdateCommond menu) {
        navFacade.update(id,menu);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
       navFacade.switchStatus(id,disable);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResponse delete(@PathVariable("id") String id) {
        navFacade.delete(id);
        return RestResponse.ok();
    }


    @RequestMapping(method = RequestMethod.GET)
    public RestResponse list() {
        return RestResponse.ok(navFacade.list());
    }


}
