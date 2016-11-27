package org.bumishi.techblog.api.web.controller.rest;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xieqiang on 2016/11/27.
 */
@RestController
@RequestMapping("/rest/blog")
public class BlogRestController {

    private final int PAGE_SIZE=10;

    @Autowired
    protected BlogQueryRepositry blogQueryRepositry;

    @GetMapping("/{id}")
    public RestResponse get(@PathVariable("id")String id){
        Blog blog=blogQueryRepositry.get(id);
        if(blog==null){
            return RestResponse.fail("404","博客不存在");
        }
       return RestResponse.ok(blog);
    }

    @GetMapping("/catalog/{catalog}")
    public RestResponse catalog(@PathVariable("catalog")String catalog){
       return RestResponse.ok(blogQueryRepositry.queryByCatalog(1,PAGE_SIZE,catalog));
       
    }

    @GetMapping("/latest")
    public RestResponse latest(@RequestParam(value = "page",required = false,defaultValue = "1")int page,@RequestParam(value = "size",required = false,defaultValue = PAGE_SIZE+"")int size){
        return RestResponse.ok(blogQueryRepositry.queryByTime(page, size));
    }



}
