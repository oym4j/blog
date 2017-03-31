package org.bumishi.techblog.api.config;

import org.bumishi.techblog.api.interfaces.manage.metaweblog.MetaweblogServerlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiang.xie
 * @date 2017/3/31
 */
@Configuration
public class XmlRpcConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean(MetaweblogServerlet metaweblogServerlet){
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean();
        servletRegistrationBean.setServlet(metaweblogServerlet);
        servletRegistrationBean.addUrlMappings("/metaweblog/api");
        servletRegistrationBean.setEnabled(true);
        return servletRegistrationBean;
    }
}
