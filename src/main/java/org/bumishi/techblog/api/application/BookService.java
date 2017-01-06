package org.bumishi.techblog.api.application;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.repository.BookCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BookIndexRepositry;
import org.bumishi.techblog.api.domain.repository.BookQueryRepositry;
import org.bumishi.toolbox.model.PageModel;
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
    private BookQueryRepositry bookQueryRepositry;

    @Autowired
    protected BookIndexRepositry bookIndexRepositry;

    public void delete(String bookId) {
        bookCommandRepositry.remove(bookId);
        bookIndexRepositry.removeByBookId(bookId);
    }

    public List<BookIndex> listIndexsByBookId(String bookId) {
        List<BookIndex> list = bookIndexRepositry.getByBook(bookId);
        TreeModel.sortByTree(list);
        return list;
    }


    public Book getBook(String id){
        return bookQueryRepositry.get(id);
    }

    public BookIndex getBookIndex(String indexId){
        return bookIndexRepositry.get(indexId);
    }

    public PageModel<Book> queryByTime(int page,int size){
        return bookQueryRepositry.queryByTime(page, size);
    }

    public PageModel<Book> queryByCatalog(int page,int size,String catalog){
        return bookQueryRepositry.queryByCatalog(page, size,catalog);
    }

}
