package org.bumishi.techblog.api.interfaces.manage.facade;

import org.bumishi.techblog.api.application.BookService;
import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.repository.BookCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BookQueryRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BookAssembler;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookUpdateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.dto.BookDto;
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
public class BookFacade {

    @Autowired
    private BookCommandRepositry bookCommandRepositry;
    
    @Autowired
    private BookQueryRepositry bookQueryRepositry;

    @Autowired
    private BookAssembler bookAssembler;

    @Autowired
    private BookService bookService;
    

    public void createBook(BookUpdateCommand bookCommand){
        bookCommandRepositry.save(bookAssembler.createComandToDomain(bookCommand));
    }

    public void updateBook(String id,BookUpdateCommand bookCommand){
        bookCommandRepositry.update(bookAssembler.updateCommandToDomain(id,bookCommand));
    }

    public BookDto getBook(String id) {
        return bookAssembler.toDto(bookQueryRepositry.get(id));
    }

    public void delete(String id) {
        bookService.delete(id);
    }

    public PageModel<BookDto> pageQuery(int page, int size) {
        PageModel<BookDto> pageModel = new PageModel();
        PageModel<Book> blogPageModel = bookQueryRepositry.queryByTime(page, size);
        pageModel.setHasNext(blogPageModel.isHasNext());
        pageModel.setPage(blogPageModel.getPage());
        pageModel.setSize(blogPageModel.getSize());
        if (!CollectionUtils.isEmpty(blogPageModel.getList())) {
            List<BookDto> blogDtos = blogPageModel.getList().stream().map(blog -> bookAssembler.toDto(blog)).collect(Collectors.toList());
            pageModel.setList(blogDtos);
        }
        return pageModel;
    }

    public PageModel<BookDto> pageQueryByCatalog(int page, int size, String catalog) {
        PageModel<BookDto> pageModel = new PageModel();
        PageModel<Book> blogPageModel = bookQueryRepositry.queryByCatalog(page, size, catalog);
        pageModel.setHasNext(blogPageModel.isHasNext());
        pageModel.setPage(blogPageModel.getPage());
        pageModel.setSize(blogPageModel.getSize());
        if (!CollectionUtils.isEmpty(blogPageModel.getList())) {
            List<BookDto> blogDtos = blogPageModel.getList().stream().map(blog -> bookAssembler.toDto(blog)).collect(Collectors.toList());
            pageModel.setList(blogDtos);
        }
        return pageModel;
    }

    public List<BookIndex> listByBookId(String bookId) {
        return bookService.listByBookId(bookId);
    }

}
