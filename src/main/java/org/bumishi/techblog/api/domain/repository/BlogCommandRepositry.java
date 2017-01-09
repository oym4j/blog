package org.bumishi.techblog.api.domain.repository;

import org.bumishi.techblog.api.domain.model.Blog;

/**
 * Created by xieqiang on 2016/11/26.
 */
public interface BlogCommandRepositry {

    void save(Blog blog);

    void update(Blog blog);

    void remove(String id);

}
