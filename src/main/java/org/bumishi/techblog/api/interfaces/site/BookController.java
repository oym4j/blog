package org.bumishi.techblog.api.interfaces.site;

import org.bumishi.techblog.api.application.SiteConfigService;
import org.bumishi.techblog.api.interfaces.manage.facade.BookFacade;
import org.bumishi.techblog.api.interfaces.site.facade.GitBookFacade;
import org.bumishi.techblog.api.interfaces.site.facade.dto.GitBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookFacade bookFacade;

    @Autowired
    private GitBookFacade gitBookFacade;

    @Autowired
    private SiteConfigService siteConfigService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pageModel", bookFacade.pageQuery(1, siteConfigService.pageSize()));
        model.addAttribute("catalog", "java");
        model.addAttribute("page", 1);
        return "book-catalog";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") String id, Model model) {
        GitBookDto book = gitBookFacade.getGitBook(id);
        if (book == null) {
            return "404";
        }
        model.addAttribute("book", book);

        return "book";
    }

    @GetMapping("/catalog/{catalog}")
    public String catalog(@PathVariable("catalog") String catalog, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        model.addAttribute("pageModel", bookFacade.pageQueryByCatalog(page, siteConfigService.pageSize(), catalog));
        model.addAttribute("catalog", catalog);
        model.addAttribute("page", page);
        return "book-catalog";
    }
}
