package org.bumishi.techblog.api.interfaces.manage.facade;

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

    public void add(NavigationCreateCommand createCommand,String bookId){
        bookIndexRepositry.add(bookIndexAssembler.createCommandToDomain(createCommand,bookId));
    }

    public void update(String bookId,NavigationUpdateCommond updateCommond,String indexId){
        bookIndexRepositry.update(bookIndexAssembler.updateCommandToDomain(bookId, updateCommond,indexId));

    }

}
