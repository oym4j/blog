package org.bumishi.techblog.api.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xieqiang on 2017/1/6.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public EventBus eventBusForCache(){
        return new EventBus("cache-update-eventbus");
    }
}
