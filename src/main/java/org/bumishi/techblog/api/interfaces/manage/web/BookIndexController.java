package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.interfaces.manage.facade.BookIndexFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookIndexCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookIndexUpdateCommand;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiang.xie
 * @date 2016/9/18
 */
@RestController
@RequestMapping("/admin/bookindex")
public class BookIndexController {

    @Autowired
    protected BookIndexFacade bookIndexFacade;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RestResponse create(BookIndexCreateCommand bookindex) {
        bookIndexFacade.add(bookindex);
        return RestResponse.ok();
    }


    @RequestMapping(value = "/{id}/modify", method = RequestMethod.POST)
    public RestResponse modify(@PathVariable("id") String id, BookIndexUpdateCommand bookindex) {
        bookIndexFacade.update(id,bookindex);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
       bookIndexFacade.switchStatus(id,disable);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResponse delete(@PathVariable("id") String id) {
        bookIndexFacade.delete(id);
        return RestResponse.ok();
    }


    @RequestMapping(method = RequestMethod.GET)
    public RestResponse list() {
        return RestResponse.ok(bookIndexFacade.list());
    }


}
