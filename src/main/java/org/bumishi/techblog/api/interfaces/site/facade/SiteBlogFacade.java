package org.bumishi.techblog.api.interfaces.site.facade;

import org.bumishi.techblog.api.application.BlogService;
import org.bumishi.techblog.api.application.CatalogService;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogViewsRepostiry;
import org.bumishi.techblog.api.interfaces.site.facade.dto.SiteBlogDto;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BlogViewsRepostiry blogViewsRepostiry;

    @Autowired
    private CatalogService catalogService;


    public SiteBlogDto getBlog(String id) {
        Blog blog = blogService.getBlog(id);
        if (blog == null) {
            return null;
        }
        return toDto(blog);
    }

    private SiteBlogDto toDto(Blog blog) {
        SiteBlogDto dto = new SiteBlogDto();
        BeanUtils.copyProperties(blog, dto);
        dto.setCatalogDisplay(catalogService.getCatalog(blog.getCatalog()).getLabel());
        dto.setLink("/blog/" + blog.getId());
        dto.setSummary("");//todo
        dto.setViews(blogViewsRepostiry.getViews(blog.getId()));
        return dto;
    }

    public PageModel<SiteBlogDto> pageQuery(int page, int size) {
        PageModel<Blog> blogPageModel = blogService.queryByTime(page, size);
        return getSiteBlogDtoPageModel(blogPageModel);
    }


    public PageModel<SiteBlogDto> queryByCatalog(int page, int size, String catalog) {
        PageModel<Blog> blogPageModel = blogService.queryByCatalog(page, size, catalog);
        return getSiteBlogDtoPageModel(blogPageModel);
    }

    public PageModel<SiteBlogDto> search(int page, int size, String keywords) {
        PageModel<Blog> blogPageModel = blogService.search(page, size, keywords);
        return getSiteBlogDtoPageModel(blogPageModel);
    }

    private PageModel<SiteBlogDto> getSiteBlogDtoPageModel(PageModel<Blog> blogPageModel) {
        if (blogPageModel == null) {
            return null;
        }
        PageModel<SiteBlogDto> pageModel = new PageModel();
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
        blogViewsRepostiry.addViews(blogId);
    }

}
