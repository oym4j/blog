package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/11/27.
 */
@Repository
public class MenuJdbcRepositry extends AbstractNavigationNodeJdbcRepositry {

    @Override
    protected String getTable() {
        return "`menu`";
    }
}
