package org.bumishi.techblog.api.domain.model;

import org.bumishi.techblog.api.web.controller.manage.command.AddBlogCommand;

/**
 * markdown转html
 * Created by xieqiang on 2016/12/4.
 */
public interface MarkDownToHtml {

    String convert(AddBlogCommand addBlogCommand);
}
