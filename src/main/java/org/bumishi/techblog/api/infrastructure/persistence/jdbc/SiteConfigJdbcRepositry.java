package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.techblog.api.domain.repository.SiteConfigRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Repository
public class SiteConfigJdbcRepositry implements SiteConfigRepositry {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void update(Map<String,String> config) {
        if(CollectionUtils.isEmpty(config))return;
       jdbcTemplate.update("DELETE FROM site_config");
        jdbcTemplate.update("INSERT site_config (`k`,`v`) VALUES (?,?)",config);
    }

    @Override
    public Map<String,Object> getConfig() {
        try {
            return jdbcTemplate.queryForMap("select * from site_config");
        }catch (Exception e){
            return Collections.emptyMap();
        }
    }
}
