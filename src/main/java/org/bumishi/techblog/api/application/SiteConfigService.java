package org.bumishi.techblog.api.application;

import org.bumishi.toolbox.model.SiteConfig;
import org.bumishi.toolbox.model.repositry.SiteConfigRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Service
public class SiteConfigService {

    @Autowired
    private SiteConfigRepositry siteConfigRepositry;

    public SiteConfig siteConfig() {
        return siteConfigRepositry.get("blog");
    }

    public void update(SiteConfig siteConfig) {
        siteConfig.setId("blog");
        siteConfigRepositry.update(siteConfig);
    }

    public int pageSize() {
        return siteConfig().getPageSize();
    }
}
