package org.bumishi.techblog.api.interfaces.manage.facade.assembler;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookUpdateCommand;
import org.springframework.beans.BeanUtils;

/**
 * Created by xieqiang on 2016/12/18.
 */
public class BookAssembler {

    public Book createComandToDomain(BookUpdateCommand bookUpdateCommand){
        Book book=new Book();
        BeanUtils.copyProperties(bookUpdateCommand,book);
        return book;
    }

    public Book updateCommandToDomain(String id,BookUpdateCommand bookUpdateCommand){
        Book book=new Book();
        BeanUtils.copyProperties(bookUpdateCommand,book);
        book.setId(id);
        return book;
    }
}
