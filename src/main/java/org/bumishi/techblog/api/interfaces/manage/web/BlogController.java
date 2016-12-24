package org.bumishi.techblog.api.interfaces.manage.web;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.interfaces.manage.facade.BlogFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.WriteBlogCommand;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 提供admin管理接口
 * Created by xieqiang on 2016/11/27.
 */
@RestController("adminBlogController")
@RequestMapping("/admin/blog")
public class BlogController {


    @Autowired
    private BlogFacade blogFacade;

    @Autowired
    private BlogService blogService;


    @PostMapping("/add")
    public RestResponse addBlog(@RequestBody @Valid WriteBlogCommand blog){
        blogFacade.createBlog(blog);
        return RestResponse.ok();
    }

    @PostMapping("/{id}/update")
    public RestResponse updateBlog(@PathVariable("id")String id,@RequestBody @Valid WriteBlogCommand blog){
        blogFacade.updateBlog(id,blog);
        return RestResponse.ok();
    }

    @PostMapping(value = "/{id}/delete")
    public RestResponse delete(@PathVariable("id") String id) {
        blogService.delete(id);
        return RestResponse.ok();
    }

    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
            return RestResponse.ok(blogFacade.getBlog(id));
    }

    @GetMapping()
    public RestResponse get(@RequestParam(value = "page",required = false,defaultValue = "1") int page){
        return RestResponse.ok(blogFacade.pageQuery(page,20));
    }
}
