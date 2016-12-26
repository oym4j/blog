package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.application.NavService;
import org.bumishi.techblog.api.interfaces.manage.facade.NavFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.RestResponse;
import org.bumishi.toolbox.model.repositry.NavigationNodeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author qiang.xie
 * @date 2016/9/18
 */
@RestController
@RequestMapping("/admin/nav")
public class NavController {

    @Autowired
    protected NavFacade navFacade;

    @Autowired
    protected NavService navService;

    @Autowired
    @Qualifier("menuJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;

    @PostMapping(value = "/add")
    public RestResponse create(@RequestBody @Valid NavigationCreateCommand menu) {
        navFacade.add(menu);
        return RestResponse.ok();
    }


    @PostMapping(value = "/{id}/modify")
    public RestResponse modify(@PathVariable("id") String id,@RequestBody @Valid NavigationUpdateCommond menu) {
        navFacade.update(id,menu);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/status")
    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
       navFacade.switchStatus(id,disable);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    public RestResponse delete(@PathVariable("id") String id) {
        navigationNodeRepositry.remove(id);
        return RestResponse.ok();
    }


    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        return RestResponse.ok(navigationNodeRepositry.get(id));
    }

    @GetMapping
    public RestResponse list() {
        return RestResponse.ok(navService.listByOrder());
    }


}
