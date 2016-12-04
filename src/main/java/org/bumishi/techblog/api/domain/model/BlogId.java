package org.bumishi.techblog.api.domain.model;

/**
 * 为博客生成id
 * Created by xieqiang on 2016/12/4.
 */
public interface BlogId {

    String id(Blog blog);
}
