package org.bumishi.techblog.api.domain.repository;

import org.bumishi.toolbox.model.NavigationNode;

import java.util.List;

/**
 * 可导航的节点仓储，菜单，分类都是可导航的对象
 * Created by xieqiang on 2016/11/26.
 */
public interface NavigationNodeRepositry {

    /***
     * 查出所有节点
     * @return
     */
    List<NavigationNode> list();

    void add(NavigationNode node);

    void update(NavigationNode node);

    void delete(String id);

    /***
     * 查出根节点列表，每个根节点包含下面所有层级的子节点
     * @return
     */
    List<NavigationNode> rootList();
}
