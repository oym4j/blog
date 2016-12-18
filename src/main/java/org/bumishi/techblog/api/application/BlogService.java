package org.bumishi.techblog.api.application;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Service
public class BlogService {

    @Autowired
    protected BlogCommandRepositry blogCommandRepositry;

    @Autowired
    protected BlogQueryRepositry blogQueryRepositry;


    public void addBlog(Blog blog){
        blogCommandRepositry.save(blog);

    }

    public void updateBlog(Blog blog){
        blogCommandRepositry.update(blog);

    }
}
