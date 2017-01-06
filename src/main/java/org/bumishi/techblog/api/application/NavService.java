package org.bumishi.techblog.api.application;

import org.bumishi.toolbox.model.NavigationNode;
import org.bumishi.toolbox.model.TreeModel;
import org.bumishi.toolbox.model.repositry.NavigationNodeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiang.xie
 * @date 2016/12/26
 */
@Service
public class NavService {

    @Autowired
    @Qualifier("menuJdbcRepositry")
    protected NavigationNodeRepositry navigationNodeRepositry;


    public List<NavigationNode> listWithTree(boolean includeDisable) {
        return (List<NavigationNode>) new TreeModel(navigationNodeRepositry.list()).buildTree(includeDisable);
    }

    public List<NavigationNode> listByOrder() {
        List<NavigationNode> list = navigationNodeRepositry.list();
        TreeModel.sortByTree(list);
        return list;
    }

    public NavigationNode getNav(String id){
        return navigationNodeRepositry.get(id);
    }
}
