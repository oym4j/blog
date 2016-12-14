package org.bumishi.techblog.api.web.controller.site;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    private final int PAGE_SIZE=10;

    @Autowired
    protected BlogQueryRepositry blogQueryRepositry;

    @GetMapping(value = {"/", "/index.html"})
    public String index(Model model) {
        model.addAttribute("list", blogQueryRepositry.queryByTime(1, PAGE_SIZE));
        return "blog/list";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id")String id, Model model){
        Blog blog=blogQueryRepositry.get(id);
        if(blog==null){
            return "404";
        }
        model.addAttribute("blog",blog);

        return "blog";
    }

    @GetMapping("/catalog/{catalog}")
    public String catalog(@PathVariable("catalog")String catalog, Model model){
        model.addAttribute("list",blogQueryRepositry.queryByCatalog(1,PAGE_SIZE,catalog));
        return "catalog";
    }

    @GetMapping({"/latest"})
    public String latest(Model model){
        model.addAttribute("list",blogQueryRepositry.queryByTime(1,PAGE_SIZE));
        return "catalog";
    }


}
