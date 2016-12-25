package org.bumishi.techblog.api.interfaces.manage.facade.assembler;

import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.NavigationUpdateCommond;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Component
public class BookIndexAssembler {

    public BookIndex createCommandToDomain(NavigationCreateCommand bookIndexCreateCommand,String bookId){
        BookIndex bookIndex=new BookIndex();
        BeanUtils.copyProperties(bookIndexCreateCommand,bookIndex);
        bookIndex.setBookId(bookId);
        return bookIndex;
    }

    public BookIndex updateCommandToDomain(String bookId,NavigationUpdateCommond bookIndexCreateCommand,String id){
        BookIndex bookIndex=new BookIndex();
        BeanUtils.copyProperties(bookIndexCreateCommand,bookIndex);
        bookIndex.setBookId(bookId);
        bookIndex.setId(id);
        return bookIndex;
    }
}
