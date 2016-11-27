package org.bumishi.techblog.api.domain.repository;

import org.bumishi.techblog.api.domain.model.Book;

/**
 * gitbook书仓储
 * Created by xieqiang on 2016/11/27.
 */
public interface BookCommandRepositry {

    void save(Book book);

    void update(Book book);

    void remove(String id);
    
}
