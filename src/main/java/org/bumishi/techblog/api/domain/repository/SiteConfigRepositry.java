package org.bumishi.techblog.api.domain.repository;


import java.util.Map;

/**
 * Created by xieqiang on 2016/12/18.
 */
public interface SiteConfigRepositry {

    void update(Map<String, String> config);

    Map<String,Object> getConfig();
}
