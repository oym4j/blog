package org.bumishi.techblog.api.interfaces.manage.facade;

import com.google.common.eventbus.EventBus;
import org.bumishi.techblog.api.application.BookService;
import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.model.event.BookIndexDeleteEvent;
import org.bumishi.techblog.api.domain.model.event.BookIndexUpdateEvent;
import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BookIndexAssembler;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Service
public class BookIndexFacade {

    @Autowired
    protected BookIndexRepositry bookIndexRepositry;
    
    @Autowired
    protected BookIndexAssembler bookIndexAssembler;

    @Autowired
    protected BookService bookService;

    @Autowired
    protected EventBus eventBus;


    public void add(NavigationCreateCommand createCommand,String bookId){
        BookIndex bookIndex = bookIndexAssembler.createCommandToDomain(createCommand, bookId);
        bookIndexRepositry.add(bookIndex);
        eventBus.post(new BookIndexUpdateEvent(bookIndex));
    }

    public void update(NavigationUpdateCommond updateCommond, String indexId) {
        BookIndex bookIndex = bookIndexAssembler.updateCommandToDomain(updateCommond, indexId);
        bookIndexRepositry.update(bookIndex);
        eventBus.post(new BookIndexUpdateEvent(bookIndex));
    }

    public void switchStatus(String id, boolean disable) {
        if (disable) {
            bookIndexRepositry.disable(id);
        } else {
            bookIndexRepositry.enable(id);
        }
        eventBus.post(new BookIndexDeleteEvent(id));
    }

    public void delete(String id) {
        bookIndexRepositry.remove(id);
        eventBus.post(new BookIndexDeleteEvent(id));
    }

    public BookIndex get(String id) {
        return bookService.getBookIndex(id);
    }

}
