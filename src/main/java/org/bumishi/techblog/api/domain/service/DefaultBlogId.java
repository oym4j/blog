package org.bumishi.techblog.api.domain.service;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.model.BlogId;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by xieqiang on 2016/12/4.
 */
@Component
public class DefaultBlogId implements BlogId{

    @Autowired
    @Qualifier("blogElasticSearchRepositry")
    protected BlogQueryRepositry blogQueryRepositry;

    @Override
    public String id(Blog blog) {
        return (blogQueryRepositry.getCount() + 1) + "";
    }
}
