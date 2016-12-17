package org.bumishi.techblog.api.web.controller.manage;

import org.bumishi.techblog.api.domain.repository.NavigationNodeRepositry;
import org.bumishi.techblog.api.web.controller.manage.assembler.NavigationAssembler;
import org.bumishi.techblog.api.web.controller.manage.command.NavigationCreateCommand;
import org.bumishi.techblog.api.web.controller.manage.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiang.xie
 * @date 2016/9/18
 */
@RestController
@RequestMapping("/admin/catalog")
public class CatalogController {

    @Autowired
    @Qualifier("catalogJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse create(NavigationCreateCommand menu) {
        navigationNodeRepositry.add(NavigationAssembler.createCommendToDomain(menu));
        return RestResponse.ok();
    }


    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public RestResponse modify(@PathVariable("id") String id, NavigationUpdateCommond menu) {
        navigationNodeRepositry.update(NavigationAssembler.updateCommendToDomain(id, menu));
        return RestResponse.ok();
    }


//    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
//    @ResponseBody
//    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
//        navigationNodeRepositry.s(id,disable);
//    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResponse delete(@PathVariable("id") String id) {
        navigationNodeRepositry.remove(id);
        return RestResponse.ok();
    }


    @RequestMapping(method = RequestMethod.GET)
    public RestResponse list(Model model) {
        return RestResponse.ok(navigationNodeRepositry.list());
    }


}
