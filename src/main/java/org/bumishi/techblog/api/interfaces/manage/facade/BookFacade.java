package org.bumishi.techblog.api.interfaces.manage.facade;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.repository.BookCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BookQueryRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BookAssembler;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookUpdateCommand;
import org.bumishi.toolbox.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    

    public void createBook(BookUpdateCommand bookCommand){
        bookCommandRepositry.save(bookAssembler.createComandToDomain(bookCommand));
    }

    public void updateBook(String id,BookUpdateCommand bookCommand){
        bookCommandRepositry.update(bookAssembler.updateCommandToDomain(id,bookCommand));
    }

    public Book getBook(String id){
       return bookQueryRepositry.get(id);
    }

    public PageModel<Book> pageQuery(int page,int size){
     return bookQueryRepositry.queryByTime(page,size);
    }

}
