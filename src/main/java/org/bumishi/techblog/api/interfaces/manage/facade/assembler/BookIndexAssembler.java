package org.bumishi.techblog.api.interfaces.manage.facade.assembler;

import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookIndexCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookIndexUpdateCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Component
public class BookIndexAssembler {

    public BookIndex createCommandToDomain(BookIndexCreateCommand bookIndexCreateCommand){
        BookIndex bookIndex=new BookIndex();
        BeanUtils.copyProperties(bookIndexCreateCommand,bookIndex);
        return bookIndex;
    }

    public BookIndex updateCommandToDomain(String id,BookIndexUpdateCommand bookIndexCreateCommand){
        BookIndex bookIndex=new BookIndex();
        BeanUtils.copyProperties(bookIndexCreateCommand,bookIndex);
        bookIndex.setId(id);
        return bookIndex;
    }
}
