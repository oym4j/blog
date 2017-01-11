package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/11/26.
 */
@Repository("blogCommandJdbcRepositry")
public class BlogCommandJdbcRepositry implements BlogCommandRepositry {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void save(Blog blog) {
        int count=jdbcTemplate.queryForObject("select count(*) from blog where id=?",Integer.class,blog.getId());
        if(count==0) {
            jdbcTemplate.update("INSERT blog (id,title,secondTitle,`catalog`,display,md,auther,publishTime,img) VALUE (?,?,?,?,?,?,?,?,?)", blog.getId(), blog.getTitle(), blog.getSecondTitle(), blog.getCatalog(), blog.getDisplay(), blog.getMd(), blog.getAuther(), blog.getPublishTime(), blog.getImg());
        }else{
            update(blog);
        }
    }

    @Override
    public void update(Blog blog) {
        jdbcTemplate.update("update blog SET title=?,secondTitle=?,`catalog`=?,display=?,md=?,auther=?,publishTime=?,img=? WHERE id =?", blog.getTitle(),blog.getSecondTitle(), blog.getCatalog(), blog.getDisplay(), blog.getMd(), blog.getAuther(), blog.getPublishTime(), blog.getImg(), blog.getId());

    }


    @Override
    public void remove(String id) {
          jdbcTemplate.update("DELETE FROM blog WHERE id=?",id);
    }


}
