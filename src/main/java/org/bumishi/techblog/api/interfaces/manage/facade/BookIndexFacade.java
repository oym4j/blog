package org.bumishi.techblog.api.interfaces.manage.facade;

import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
import org.bumishi.techblog.api.interfaces.manage.facade.assembler.BookIndexAssembler;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookIndexCreateCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.command.BookIndexUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Service
public class BookIndexFacade {

    @Autowired
    protected BookIndexRepositry bookIndexRepositry;
    
    @Autowired
    protected BookIndexAssembler bookIndexAssembler;

    public void add(BookIndexCreateCommand createCommand){
        bookIndexRepositry.add(bookIndexAssembler.createCommandToDomain(createCommand));
    }

    public void update(String id,BookIndexUpdateCommand updateCommond){
        bookIndexRepositry.update(bookIndexAssembler.updateCommandToDomain(id, updateCommond));

    }

    public void switchStatus(String id,boolean disable){
        if(disable){
            bookIndexRepositry.disable(id);
        }else {
            bookIndexRepositry.enable(id);
        }
    }

    public void delete(String id){
        bookIndexRepositry.remove(id);
    }

    public List<BookIndex> list(){
        return bookIndexRepositry.list();
    }

}
