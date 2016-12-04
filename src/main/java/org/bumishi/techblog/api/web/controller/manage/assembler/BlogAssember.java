package org.bumishi.techblog.api.web.controller.manage.assembler;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.model.BlogId;
import org.bumishi.techblog.api.domain.model.MarkDownToHtml;
import org.bumishi.techblog.api.web.controller.manage.command.AddBlogCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xieqiang on 2016/12/4.
 */
@Component
public class BlogAssember {

    @Autowired
    protected BlogId blogId;

    @Autowired
    protected MarkDownToHtml markDownToHtml;

    public Blog addBlogCommandToBlog(AddBlogCommand command){
        Blog blog=new Blog();
        BeanUtils.copyProperties(command,blog);
        blog.setAuther("bumishi");//多用户博客程序情况下扩展字段
        blog.setPublishTime(new Date());
        blog.setId(blogId.id(blog));
        blog.setDisplay(markDownToHtml.convert(blog.getMd()));
        return blog;
    }
}
