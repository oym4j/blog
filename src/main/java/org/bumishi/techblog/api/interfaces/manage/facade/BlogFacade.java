package org.bumishi.techblog.api.interfaces.manage.facade;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BlogAssember;
import org.bumishi.techblog.api.interfaces.manage.facade.command.WriteBlogCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.dto.BlogDto;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Service
public class BlogFacade {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogAssember blogAssember;

    @Autowired
    private BlogQueryRepositry blogQueryRepositry;

    public void createBlog(WriteBlogCommand blogCommand){
        blogService.addBlog(blogAssember.addBlogCommandToDomain(blogCommand));
    }

    public void updateBlog(String id,WriteBlogCommand blogCommand){
        blogService.updateBlog(blogAssember.updateBlogCommandToDomain(id,blogCommand));
    }

    public BlogDto getBlog(String id){
        Blog blog=blogQueryRepositry.get(id);
        return blogAssember.toDto(blog);
    }

    public PageModel<BlogDto> pageQuery(int page,int size){
        PageModel<BlogDto> pageModel=new PageModel();
        PageModel<Blog> blogPageModel=blogQueryRepositry.queryByTime(page,size);
        pageModel.setHasNext(blogPageModel.isHasNext());
        pageModel.setPage(blogPageModel.getPage());
        pageModel.setSize(blogPageModel.getSize());
        if(!CollectionUtils.isEmpty(blogPageModel.getList())) {
            List<BlogDto> blogDtos = new ArrayList<>();
            for (Blog blog : blogPageModel.getList()) {
                blogDtos.add(blogAssember.toDto(blog));
            }
            pageModel.setList(blogDtos);
        }
        return pageModel;
    }

}
