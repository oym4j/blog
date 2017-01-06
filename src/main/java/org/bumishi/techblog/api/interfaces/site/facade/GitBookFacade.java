package org.bumishi.techblog.api.interfaces.site.facade;

import org.bumishi.techblog.api.application.BookService;
import org.bumishi.techblog.api.application.CatalogService;
import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.interfaces.site.facade.dto.GitBookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Service
public class GitBookFacade {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private BookService bookService;

    public GitBookDto getGitBook(String bookId) {
        Book book = bookService.getBook(bookId);
        if (book == null) {
            return null;
        }
        GitBookDto gitBookDto = new GitBookDto();
        BeanUtils.copyProperties(book, gitBookDto);
        gitBookDto.setCatalogDisplay(catalogService.getCatalog(book.getCatalog()).getLabel());
        gitBookDto.setIndexs(bookService.listIndexsByBookId(bookId));
        return gitBookDto;
    }
}
