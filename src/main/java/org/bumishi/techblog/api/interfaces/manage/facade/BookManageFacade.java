package org.bumishi.techblog.api.interfaces.manage.facade;

import org.bumishi.techblog.api.application.BookService;
import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.repository.BookCommandRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BookAssembler;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookUpdateCommand;
import org.bumishi.techblog.api.interfaces.shard.BookDto;
import org.bumishi.techblog.api.interfaces.shard.BookDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Service
public class BookManageFacade {

    @Autowired
    private BookCommandRepositry bookCommandRepositry;

    @Autowired
    private BookAssembler bookAssembler;

    @Autowired
    private BookDtoAssembler bookDtoAssembler;

    @Autowired
    private BookService bookService;
    

    public void createBook(BookUpdateCommand bookCommand){
        bookCommandRepositry.save(bookAssembler.createComandToDomain(bookCommand));
    }

    public void updateBook(String id,BookUpdateCommand bookCommand){
        bookCommandRepositry.update(bookAssembler.updateCommandToDomain(id,bookCommand));
    }

    public void delete(String id) {
        bookService.delete(id);
    }

    public BookDto getBook(String id) {
        return bookDtoAssembler.toDto(bookService.getBook(id));
    }

    public List<BookIndex> listByBookId(String bookId) {
        return bookService.listIndexsByBookId(bookId);
    }



}
