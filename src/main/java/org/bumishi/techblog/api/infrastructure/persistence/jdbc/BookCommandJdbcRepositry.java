package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.repository.BookCommandRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Repository
public class BookCommandJdbcRepositry implements BookCommandRepositry{

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void remove(String id) {

    }
}
