package org.bumishi.techblog.api.interfaces.site.facade;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.techblog.api.interfaces.site.facade.dto.SiteBlogDto;
import org.bumishi.toolbox.model.PageModel;
import org.bumishi.toolbox.model.repositry.NavigationNodeRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Service
public class SiteBlogFacade {
    @Autowired
    private BlogService blogService;


    @Autowired
    private BlogQueryRepositry blogQueryRepositry;

    @Autowired
    private BlogCommandRepositry blogCommandRepositry;

    @Autowired
    @Qualifier("catalogJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;


    public SiteBlogDto getBlog(String id) {
        Blog blog = blogQueryRepositry.get(id);
        if (blog == null) {
            return null;
        }
        return toDto(blog);
    }

    private SiteBlogDto toDto(Blog blog) {
        SiteBlogDto dto = new SiteBlogDto();
        BeanUtils.copyProperties(blog, dto);
        dto.setCatalogDisplay(navigationNodeRepositry.get(blog.getCatalog()).getLabel());
        dto.setLink("/blog/" + blog.getId());
        dto.setSummary("");//todo
        dto.setViews(blogQueryRepositry.getViews(blog.getId()));
        return dto;
    }

    public PageModel<SiteBlogDto> pageQuery(int page, int size) {
        PageModel<SiteBlogDto> pageModel = new PageModel();
        PageModel<Blog> blogPageModel = blogQueryRepositry.queryByTime(page, size);
        pageModel.setHasNext(blogPageModel.isHasNext());
        pageModel.setPage(blogPageModel.getPage());
        pageModel.setSize(blogPageModel.getSize());
        if (!CollectionUtils.isEmpty(blogPageModel.getList())) {
            List<SiteBlogDto> blogDtos = blogPageModel.getList().stream().map(blog -> toDto(blog)).collect(Collectors.toList());
            pageModel.setList(blogDtos);
        }
        return pageModel;
    }

    public PageModel<SiteBlogDto> queryByCatalog(int page, int size, String catalog) {
        PageModel<SiteBlogDto> pageModel = new PageModel();
        PageModel<Blog> blogPageModel = blogQueryRepositry.queryByCatalog(page, size, catalog);
        pageModel.setHasNext(blogPageModel.isHasNext());
        pageModel.setPage(blogPageModel.getPage());
        pageModel.setSize(blogPageModel.getSize());
        if (!CollectionUtils.isEmpty(blogPageModel.getList())) {
            List<SiteBlogDto> blogDtos = blogPageModel.getList().stream().map(blog -> toDto(blog)).collect(Collectors.toList());
            pageModel.setList(blogDtos);
        }
        return pageModel;
    }

    public void addViews(String blogId) {
        blogCommandRepositry.addViews(blogId);
    }

}
