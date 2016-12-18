package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.interfaces.manage.facade.CatalogFacade;
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
@RequestMapping("/admin/catalog")
public class CatalogController {

    @Autowired
  private CatalogFacade catalogFacade;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse create(NavigationCreateCommand catalog) {
        catalogFacade.add(catalog);
        return RestResponse.ok();
    }


    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public RestResponse modify(@PathVariable("id") String id, NavigationUpdateCommond catalog) {
            catalogFacade.update(id,catalog);
            return RestResponse.ok();
    }


    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
        catalogFacade.switchStatus(id,disable);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResponse delete(@PathVariable("id") String id) {
       catalogFacade.delete(id);
        return RestResponse.ok();
    }


    @RequestMapping(method = RequestMethod.GET)
    public RestResponse list() {
        return RestResponse.ok(catalogFacade.list());
    }


}
