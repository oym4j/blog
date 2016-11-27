package org.bumishi.techblog.api.domain.repository;

import org.bumishi.techblog.api.domain.model.Book;
import org.bumishi.toolbox.model.PageModel;

/**
 * Created by xieqiang on 2016/11/27.
 */
public interface BookQueryRepositry {

    Book get(String id);

    PageModel<Book> queryByCatalog(int page,int size,String catalog);

    PageModel<Book> queryByTime(int page,int size);
}
