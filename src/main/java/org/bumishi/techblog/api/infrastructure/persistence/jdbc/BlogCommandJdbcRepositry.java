package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/11/26.
 */
@Repository
public class BlogCommandJdbcRepositry implements BlogCommandRepositry {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void save(Blog blog) {
        jdbcTemplate.update("INSERT blog (id,title,`catalog`,display,md,auther,publishTime,img) VALUE (?,?,?,?,?,?,?,?)",blog.getId(),blog.getTitle(),blog.getCatalog(),blog.getDisplay(),blog.getMd(),blog.getAuther(),blog.getPublishTime(),blog.getImg());
    }

    @Override
    public void update(Blog blog) {
        jdbcTemplate.update("update blog SET title=?,`catalog`=?,display=?,md=?,auther=?,publishTime=?,img=? WHERE IDENTITY =?",blog.getTitle(),blog.getCatalog(),blog.getDisplay(),blog.getMd(),blog.getAuther(),blog.getPublishTime(),blog.getImg(),blog.getId());

    }


    @Override
    public void remove(String id) {
          jdbcTemplate.update("DELETE FROM blog WHERE id=?",id);
    }


}
