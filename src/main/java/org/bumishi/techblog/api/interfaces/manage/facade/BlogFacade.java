package org.bumishi.techblog.api.interfaces.manage.facade;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BlogAssember;
import org.bumishi.techblog.api.interfaces.manage.facade.command.WriteBlogCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.dto.BlogDto;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Service
public class BlogFacade {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogAssember blogAssember;


    public String createBlog(WriteBlogCommand blogCommand){
        return blogService.addBlog(blogAssember.addBlogCommandToDomain(blogCommand));
    }

    public void updateBlog(String id,WriteBlogCommand blogCommand){
        blogService.updateBlog(blogAssember.updateBlogCommandToDomain(id,blogCommand));
    }


    public void delete(String id) {
        blogService.delete(id);
    }


    public BlogDto getBlog(String id){
        Blog blog=blogService.getBlog(id);
        if (blog == null) {
            return null;
        }
        return blogAssember.toDto(blog);
    }

    public PageModel<BlogDto> pageQuery(int page, int size) {
        PageModel<BlogDto> pageModel=new PageModel();
        PageModel<Blog> blogPageModel=blogService.queryByTime(page,size);
        pageModel.setHasNext(blogPageModel.isHasNext());
        pageModel.setPage(blogPageModel.getPage());
        pageModel.setSize(blogPageModel.getSize());
        if(!CollectionUtils.isEmpty(blogPageModel.getList())) {
            List<BlogDto> blogDtos = blogPageModel.getList().stream().map(blog -> blogAssember.toDto(blog)).collect(Collectors.toList());
            pageModel.setList(blogDtos);
        }
        return pageModel;
    }

    public void updateIndex(){
        blogService.updateIndex();
    }
}
