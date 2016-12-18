package org.bumishi.techblog.api.domain.service;

import org.bumishi.techblog.api.domain.model.MarkDownToHtml;
import org.bumishi.techblog.api.interfaces.manage.facade.command.WriteBlogCommand;
import org.springframework.stereotype.Component;

/**
 * Created by xieqiang on 2016/12/4.
 */
@Component
public class DefaultMarkDownToHtml implements MarkDownToHtml {

    @Override
    public String convert(WriteBlogCommand markdown) {
        return markdown.getDisplay();
    }
}
