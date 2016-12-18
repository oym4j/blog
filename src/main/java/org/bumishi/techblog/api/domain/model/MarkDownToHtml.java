package org.bumishi.techblog.api.domain.model;

import org.bumishi.techblog.api.interfaces.manage.facade.command.WriteBlogCommand;

/**
 * markdown转html
 * Created by xieqiang on 2016/12/4.
 */
public interface MarkDownToHtml {

    String convert(WriteBlogCommand addBlogCommand);
}
