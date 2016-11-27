package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.repository.BookQueryRepositry;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Repository
public class BookQueryJdbcRepositry implements BookQueryRepositry {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public Book get(String id) {
        return null;
    }

    @Override
    public PageModel<Book> queryByCatalog(int page, int size, String catalog) {
        return null;
    }

    @Override
    public PageModel<Book> queryByTime(int page, int size) {
        return null;
    }
}
