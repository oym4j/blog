package org.bumishi.techblog.api.application;


import org.bumishi.techblog.api.domain.repository.SiteConfigRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author qiang.xie
 * @date 2016/12/27
 */
@Service
@CacheConfig(cacheNames = "config")
public class SiteConfigService {

    @Autowired
    private SiteConfigRepositry siteConfigRepositry;

    @Cacheable(key = "'siteconfig'")
    public Map<String,Object> siteConfig() {
        return siteConfigRepositry.getConfig();
    }

    @CachePut(key = "'siteconfig'")
    public void update(Map<String, String> siteConfig) {
        siteConfigRepositry.update(siteConfig);
    }

    public int pageSize() {
        return Integer.valueOf(siteConfig().getOrDefault("pageSize","10").toString());
    }
}
