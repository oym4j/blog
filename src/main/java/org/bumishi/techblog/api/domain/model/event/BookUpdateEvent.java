package org.bumishi.techblog.api.domain.model.event;

import org.bumishi.techblog.api.domain.model.Book;

/**
 * book新增或修改事件，用于通知缓存更新
 *
 * @author qiang.xie
 * @date 2017/1/7
 */
public class BookUpdateEvent {

    private Book book;

    public BookUpdateEvent(Book blog) {
        this.book = blog;
    }

    public Book getBook() {
        return book;
    }
}
