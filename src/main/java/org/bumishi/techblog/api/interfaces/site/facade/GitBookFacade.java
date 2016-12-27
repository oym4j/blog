package org.bumishi.techblog.api.interfaces.site.facade;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
import org.bumishi.techblog.api.domain.repository.BookQueryRepositry;
import org.bumishi.techblog.api.interfaces.site.facade.dto.GitBookDto;
import org.bumishi.toolbox.model.TreeModel;
import org.bumishi.toolbox.model.repositry.NavigationNodeRepositry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Service
public class GitBookFacade {

    @Autowired
    private BookQueryRepositry bookQueryRepositry;

    @Autowired
    @Qualifier("catalogJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;

    @Autowired
    protected BookIndexRepositry bookIndexRepositry;

    public GitBookDto getGitBook(String bookId) {
        Book book = bookQueryRepositry.get(bookId);
        if (book == null) {
            return null;
        }
        GitBookDto gitBookDto = new GitBookDto();
        BeanUtils.copyProperties(book, gitBookDto);
        gitBookDto.setCatalogDisplay(navigationNodeRepositry.get(book.getCatalog()).getLabel());
        gitBookDto.setIndexs((List<BookIndex>) new TreeModel(bookIndexRepositry.getByBook(bookId)).buildTree());
        return gitBookDto;
    }

}
