package org.bumishi.techblog.api.web.controller.site;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.techblog.api.domain.repository.NavigationNodeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Controller
public class BlogController {

    private final int PAGE_SIZE=10;

    @Autowired
    protected BlogQueryRepositry blogQueryRepositry;

    @Autowired
    @Qualifier("catalogJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;

    @GetMapping(value = {"/", "/blog", "/index.html"})
    public String index(Model model) {
        model.addAttribute("catalogs", navigationNodeRepositry.list());
        model.addAttribute("pageModel", blogQueryRepositry.queryByTime(1, PAGE_SIZE));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String get(@PathVariable("id")String id, Model model){
        Blog blog=blogQueryRepositry.get(id);
        if(blog==null){
            return "404";
        }
        model.addAttribute("blog",blog);

        return "blog";
    }

    @GetMapping("/blog/catalog/{catalog}")
    public String catalog(@PathVariable("catalog") String catalog, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        model.addAttribute("pageModel", blogQueryRepositry.queryByCatalog(page, PAGE_SIZE, catalog));
        model.addAttribute("catalogs", navigationNodeRepositry.list());
        model.addAttribute("catalog", catalog);
        model.addAttribute("page", page);
        return "catalog";
    }



}
