package org.bumishi.techblog.api.web.controller.manage.assembler;

import org.bumishi.techblog.api.web.controller.manage.command.NavigationCreateCommand;
import org.bumishi.techblog.api.web.controller.manage.command.NavigationUpdateCommond;
import org.bumishi.toolbox.model.NavigationNode;
import org.springframework.beans.BeanUtils;

/**
 * Created by xieqiang on 2016/10/30.
 */
public class NavigationAssembler {

    public static NavigationNode updateCommendToDomain(String id, NavigationUpdateCommond updateCommond) {
        NavigationNode menu = new NavigationNode();
        BeanUtils.copyProperties(updateCommond, menu);
        menu.setId(id);
        return menu;
    }

    public static NavigationNode createCommendToDomain(NavigationCreateCommand creteCommand) {
        NavigationNode menu = new NavigationNode();
        BeanUtils.copyProperties(creteCommand, menu);
        return menu;
    }
}
