package org.bumishi.techblog.api.domain.model.event;

import org.bumishi.techblog.api.domain.model.BookIndex;

/**
 * bookindex新增或修改事件，用于通知缓存更新
 *
 * @author qiang.xie
 * @date 2017/1/7
 */
public class BookIndexUpdateEvent {

    private BookIndex bookIndex;

    public BookIndexUpdateEvent(BookIndex blog) {
        this.bookIndex = blog;
    }

    public BookIndex getBookIndex() {
        return bookIndex;
    }
}
