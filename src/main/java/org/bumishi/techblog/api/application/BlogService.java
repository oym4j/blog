package org.bumishi.techblog.api.application;

import com.google.common.eventbus.EventBus;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.model.event.BlogDeleteEvent;
import org.bumishi.techblog.api.domain.model.event.BlogUpdateEvent;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Service
public class BlogService {

    @Autowired
    protected BlogCommandRepositry blogCommandRepositry;

    @Autowired
    protected BlogQueryRepositry blogQueryRepositry;

    @Autowired
    protected EventBus eventBus;


    public void addBlog(Blog blog){
        blogCommandRepositry.save(blog);
        eventBus.post(new BlogUpdateEvent(blog));
    }

    public void updateBlog(Blog blog){
        blogCommandRepositry.update(blog);
        eventBus.post(new BlogUpdateEvent(blog));
    }

    public void delete(String id) {
        blogCommandRepositry.remove(id);
        eventBus.post(new BlogDeleteEvent(id));
    }

    @Cacheable(EventHandler.BLOG_CACHE)
    public Blog getBlog(String id){
        return blogQueryRepositry.get(id);
    }

    @Cacheable(EventHandler.BLOG_PAGE_CACHE)
    public PageModel<Blog> queryByTime(int page,int size){
        return blogQueryRepositry.queryByTime(page, size);
    }

    @Cacheable(EventHandler.BLOG_PAGE_CACHE)
    public PageModel<Blog> queryByCatalog(int page,int size,String catalog){
        return blogQueryRepositry.queryByCatalog(page, size, catalog);
    }

    public PageModel<Blog> search(int page, int size, String keywords) {
        return blogQueryRepositry.queryByKeyword(page, size, keywords);
    }

}
