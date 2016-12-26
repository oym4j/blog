package org.bumishi.techblog.api.application;

import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.repository.BookCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
import org.bumishi.toolbox.model.TreeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiang.xie
 * @date 2016/12/26
 */
@Service
public class BookService {

    @Autowired
    private BookCommandRepositry bookCommandRepositry;


    @Autowired
    protected BookIndexRepositry bookIndexRepositry;

    public void delete(String bookId) {
        bookCommandRepositry.remove(bookId);
        bookIndexRepositry.removeByBookId(bookId);
    }

    public List<BookIndex> listByBookId(String bookId) {
        List<BookIndex> list = bookIndexRepositry.getByBook(bookId);
        TreeModel.sortByTree(list);
        return list;
    }

    public List<BookIndex> listByTree(String bookId) {
        return (List<BookIndex>) new TreeModel(bookIndexRepositry.getByBook(bookId)).buildTree(false);

    }


}
