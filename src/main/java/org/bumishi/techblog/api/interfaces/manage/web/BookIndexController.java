package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
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

    @Autowired
    protected BookIndexRepositry bookIndexRepositry;

    @PostMapping(value = "/add")
    public RestResponse create(BookIndexCreateCommand bookindex) {
        bookIndexFacade.add(bookindex);
        return RestResponse.ok();
    }


    @PostMapping(value = "/{id}/modify")
    public RestResponse modify(@PathVariable("id") String id, BookIndexUpdateCommand bookindex) {
        bookIndexFacade.update(id,bookindex);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/status")
    public RestResponse switchStatus(@PathVariable("id") String id, @RequestParam("disable") boolean disable) {
       bookIndexFacade.switchStatus(id,disable);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    public RestResponse delete(@PathVariable("id") String id) {
        bookIndexRepositry.remove(id);
        return RestResponse.ok();
    }


    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        return RestResponse.ok(bookIndexRepositry.get(id));
    }

    @GetMapping
    public RestResponse list() {
        return RestResponse.ok(bookIndexRepositry.list());
    }


}
