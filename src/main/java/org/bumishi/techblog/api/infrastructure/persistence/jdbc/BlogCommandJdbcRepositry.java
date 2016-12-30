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
        jdbcTemplate.update("INSERT blog (id,title,secondTitle,`catalog`,display,md,auther,publishTime,img) VALUE (?,?,?,?,?,?,?,?,?)",blog.getId(),blog.getTitle(),blog.getSecondTitle(),blog.getCatalog(),blog.getDisplay(),blog.getMd(),blog.getAuther(),blog.getPublishTime(),blog.getImg());
    }

    @Override
    public void update(Blog blog) {
        jdbcTemplate.update("update blog SET title=?,secondTitle=?,`catalog`=?,display=?,md=?,auther=?,publishTime=?,img=? WHERE id =?", blog.getTitle(),blog.getSecondTitle(), blog.getCatalog(), blog.getDisplay(), blog.getMd(), blog.getAuther(), blog.getPublishTime(), blog.getImg(), blog.getId());

    }


    @Override
    public void remove(String id) {
          jdbcTemplate.update("DELETE FROM blog WHERE id=?",id);
    }

    @Override
    public void addViews(String id) {
        try {
            jdbcTemplate.queryForObject("select id from blog_views where id=?", String.class, id);
            jdbcTemplate.update("UPDATE blog_views SET views=views+1 WHERE id=?", id);
        } catch (Exception e) {
            jdbcTemplate.update("INSERT blog_views (id,views) VALUES (?,?)", id, 1);
        }

    }
}
