package org.bumishi.techblog.api.domain.model;

import org.bumishi.toolbox.model.NavigationNode;

/**
 * 书的目录索引
 * Created by xieqiang on 2016/11/27.
 */
public class BookIndex extends NavigationNode{

    private String bookId;//书籍id

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
