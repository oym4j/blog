package org.bumishi.techblog.api.domain.repository;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.toolbox.model.PageModel;

/**
 * Created by xieqiang on 2016/11/26.
 */
public interface BlogRepositry {

    void save(Blog blog);

    void update(Blog blog);

    Blog get(String id);

    void delete(String id);

    PageModel<Blog> queryByKeyword(String keyword);

    PageModel<Blog> queryBycatalog(String catalog);



}
