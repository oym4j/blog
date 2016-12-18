package org.bumishi.techblog.api.interfaces.manage.facade.command;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by xieqiang on 2016/12/18.
 */
public class BookIndexCreateCommand extends NavigationCreateCommand {

    @NotBlank
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
