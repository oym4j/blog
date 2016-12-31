package org.bumishi.techblog.api.application;


import org.bumishi.techblog.api.domain.repository.SiteConfigRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Service
public class SiteConfigService {

    @Autowired
    private SiteConfigRepositry siteConfigRepositry;

    public Map<String,Object> siteConfig() {
        return siteConfigRepositry.getConfig();
    }

    public void update( Map<String,String> siteConfig) {
        siteConfigRepositry.update(siteConfig);
    }

    public int pageSize() {
        return Integer.valueOf(siteConfig().getOrDefault("pageSize","10").toString());
    }
}
