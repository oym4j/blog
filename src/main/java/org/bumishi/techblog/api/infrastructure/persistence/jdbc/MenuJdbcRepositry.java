package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Repository("menuJdbcRepositry")
public class MenuJdbcRepositry extends AbstractNavigationNodeJdbcRepositry {

    @Override
    protected String getTable() {
        return "`nav`";
    }
}
