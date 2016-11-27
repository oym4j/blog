package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Repository
public class BlogQueryJdbcRepositry implements BlogQueryRepositry {

    @Autowired
    protected JdbcTemplate  jdbcTemplate;

    @Override
    public Blog get(String id) {
       try {
           return jdbcTemplate.queryForObject("select * from blog where id=?", BeanPropertyRowMapper.newInstance(Blog.class), id);
       }catch (EmptyResultDataAccessException e){
           return null;
       }
    }

    @Override
    public PageModel<Blog> queryByKeyword(int page, int size, String keyword) {
        throw new UnsupportedOperationException("not yet impl");
    }

    @Override
    public PageModel<Blog> queryByCatalog(int page, int size, String catalog) {
        PageModel<Blog> pageModel=new PageModel<>(page, size);
        List<Blog> blogs=jdbcTemplate.query("select * from blog where `catalog`=? offset ?,limit ?",BeanPropertyRowMapper.newInstance(Blog.class),catalog,(page-1)*size,size);
        if(!CollectionUtils.isEmpty(blogs)){
            pageModel.setList(blogs);
            if(blogs.size()>=size){
                pageModel.setHasNext(true);
            }
        }
        return pageModel;
    }

    @Override
    public PageModel<Blog> queryByTime(int page, int size) {
        PageModel<Blog> pageModel=new PageModel<>(page, size);
        List<Blog> blogs=jdbcTemplate.query("select * from blog ORDER BY publishTime DESC offset ?,limit ?",BeanPropertyRowMapper.newInstance(Blog.class),(page-1)*size,size);
        if(!CollectionUtils.isEmpty(blogs)){
            pageModel.setList(blogs);
            if(blogs.size()>=size){
                pageModel.setHasNext(true);
            }
        }
        return pageModel;
    }
}
