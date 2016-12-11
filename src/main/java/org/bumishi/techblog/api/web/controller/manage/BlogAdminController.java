package org.bumishi.techblog.api.web.controller.manage;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.web.controller.manage.assembler.BlogAssember;
import org.bumishi.techblog.api.web.controller.manage.command.AddBlogCommand;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 提供admin管理接口
 * Created by xieqiang on 2016/11/27.
 */
@RestController
@RequestMapping("/admin/blog")
public class BlogAdminController {

//    @Autowired
//    protected BlogQueryRepositry blogQueryRepositry;
//
//    @Autowired
//    protected BlogCommandRepositry blogCommandRepositry;

    @Autowired
    protected BlogService blogService;

    @Autowired
    protected BlogAssember blogAssember;

    @PostMapping("/add")
    public RestResponse addBlog(@RequestBody @Valid AddBlogCommand blog){
      blogService.addBlog(blogAssember.addBlogCommandToBlog(blog));
        return RestResponse.ok();
    }
}
