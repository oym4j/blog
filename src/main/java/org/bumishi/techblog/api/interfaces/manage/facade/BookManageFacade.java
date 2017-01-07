package org.bumishi.techblog.api.interfaces.manage.facade;

import com.google.common.eventbus.EventBus;
import org.bumishi.techblog.api.application.BookService;
import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.model.event.BookUpdateEvent;
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

    @Autowired
    private EventBus eventBus;
    

    public void createBook(BookUpdateCommand bookCommand){
        Book book = bookAssembler.createComandToDomain(bookCommand);
        bookCommandRepositry.save(book);
        eventBus.post(new BookUpdateEvent(book));
    }

    public void updateBook(String id,BookUpdateCommand bookCommand){
        Book book = bookAssembler.updateCommandToDomain(id, bookCommand);
        bookCommandRepositry.update(book);
        eventBus.post(new BookUpdateEvent(book));
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
