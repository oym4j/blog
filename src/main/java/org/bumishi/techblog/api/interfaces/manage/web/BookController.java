package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.interfaces.manage.facade.BookFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookUpdateCommand;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 提供admin管理接口
 * Created by xieqiang on 2016/11/27.
 */
@RestController("adminBookController")
@RequestMapping("/admin/book")
public class BookController {


    @Autowired
    protected BookFacade blogFacade;

    @PostMapping("/add")
    public RestResponse addBook(@RequestBody @Valid BookUpdateCommand blog){
        blogFacade.createBook(blog);
        return RestResponse.ok();
    }

    @PostMapping("/{id}/update")
    public RestResponse updateBook(@PathVariable("id")String id,@RequestBody @Valid BookUpdateCommand blog){
        blogFacade.updateBook(id,blog);
        return RestResponse.ok();
    }

    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
            return RestResponse.ok(blogFacade.getBook(id));
    }

    @GetMapping
    public RestResponse get(@RequestParam(value = "page",required = false,defaultValue = "1") int page){
        return RestResponse.ok(blogFacade.pageQuery(page,20));
    }
}
