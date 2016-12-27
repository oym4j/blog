package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.interfaces.manage.facade.BookFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.BookIndexFacade;
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
public class BookManageController {


    @Autowired
    protected BookFacade bookFacade;

    @Autowired
    protected BookIndexFacade bookIndexFacade;


    @PostMapping("/add")
    public RestResponse addBook(@RequestBody @Valid BookUpdateCommand blog){
        bookFacade.createBook(blog);
        return RestResponse.ok();
    }

    @PostMapping("/{id}/update")
    public RestResponse updateBook(@PathVariable("id")String id,@RequestBody @Valid BookUpdateCommand blog){
        bookFacade.updateBook(id, blog);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    public RestResponse delete(@PathVariable("id") String id) {
        bookFacade.delete(id);
        return RestResponse.ok();
    }

    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        return RestResponse.ok(bookFacade.getBook(id));
    }

    @GetMapping
    public RestResponse get(@RequestParam(value = "page",required = false,defaultValue = "1") int page){
        return RestResponse.ok(bookFacade.pageQuery(page, 20));
    }


    @GetMapping("/{bookId}/indexs")
    public RestResponse list(@PathVariable("bookId") String bookId) {
        return RestResponse.ok(bookFacade.listByBookId(bookId));
    }
}
