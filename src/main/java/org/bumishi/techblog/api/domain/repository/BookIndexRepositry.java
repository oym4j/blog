package org.bumishi.techblog.api.domain.repository;

import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.toolbox.model.TreeNodeCommandRepositry;
import org.bumishi.toolbox.model.TreeNodeQueryRepositry;

/**
 * 可导航的节点仓储，菜单，分类都是可导航的对象
 * Created by xieqiang on 2016/11/26.
 */
public interface BookIndexRepositry extends TreeNodeQueryRepositry<BookIndex>,TreeNodeCommandRepositry<BookIndex> {

}
