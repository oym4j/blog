package org.bumishi.techblog.api.infrastructure.persistence.jdbc;

import org.bumishi.toolbox.model.SiteConfig;
import org.bumishi.toolbox.model.repositry.SiteConfigRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2016/12/18.
 */
@Repository
public class SiteConfigJdbcRepositry implements SiteConfigRepositry {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void update(SiteConfig siteConfig) {
        if(siteConfig.getId()==null) {
            String sql = "insert site_config (name,keywords,logo,theme,pageSize,footer) values (?,?,?,?,?,?)";
            jdbcTemplate.update(sql,siteConfig.getName(),siteConfig.getKeywords(),siteConfig.getLogo(),siteConfig.getTheme(),siteConfig.getPageSize(),siteConfig.getFooter());
        }else{
            String sql = "update site_config SET `name`=?,keywords=?,logo=?,theme=?,pageSize=?,footer=? WHERE id=?";
            jdbcTemplate.update(sql,siteConfig.getName(),siteConfig.getKeywords(),siteConfig.getLogo(),siteConfig.getTheme(),siteConfig.getPageSize(),siteConfig.getFooter(),siteConfig.getId());

        }
    }

    @Override
    public SiteConfig get(String id) {
        return jdbcTemplate.queryForObject("select * from site_config where id=?", BeanPropertyRowMapper.newInstance(SiteConfig.class),id);
    }
}
