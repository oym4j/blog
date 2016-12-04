package org.bumishi.techblog.api.domain.service;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.model.BlogId;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by xieqiang on 2016/12/4.
 */
@Component
public class DefaultBlogId implements BlogId{

    @Override
    public String id(Blog blog) {
        return UUID.randomUUID().toString().replaceAll("_","");
    }
}
