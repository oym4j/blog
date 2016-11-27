package org.bumishi.techblog.api.web.controller.manage;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供admin管理接口
 * Created by xieqiang on 2016/11/27.
 */
@RestController
@RequestMapping("/admin")
public class BlogAdminController {

    @Autowired
    protected BlogQueryRepositry blogQueryRepositry;

    @Autowired
    protected BlogCommandRepositry blogCommandRepositry;

    @Autowired
    protected BlogService blogService;

    @PostMapping("/add")
    public RestResponse addBlog(@RequestBody Blog blog){
      blogService.addBlog();
        return RestResponse.ok();
    }
}
