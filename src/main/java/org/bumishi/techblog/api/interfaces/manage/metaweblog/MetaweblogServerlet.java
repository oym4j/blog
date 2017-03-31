package org.bumishi.techblog.api.interfaces.manage.metaweblog;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.common.XmlRpcHttpRequestConfig;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiang.xie
 * @date 2017/3/31
 */
@Component
public class MetaweblogServerlet extends XmlRpcServlet{

    @Autowired
    private SpringRequestProcessorFactoryFactory springRequestProcessorFactoryFactory;

    private boolean isAuthenticated(String pUserName, String pPassword) {
        return "test".equals(pUserName) && "test".equals(pPassword);
    }
    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
        PropertyHandlerMapping mapping
                = new PropertyHandlerMapping();
        AbstractReflectiveHandlerMapping.AuthenticationHandler handler =
                pRequest -> {
                    XmlRpcHttpRequestConfig config1 =
                            (XmlRpcHttpRequestConfig) pRequest.getConfig();
                    return isAuthenticated(config1.getBasicUserName(),
                            config1.getBasicPassword());
                };
        //mapping.setAuthenticationHandler(handler);
        mapping.setRequestProcessorFactoryFactory(springRequestProcessorFactoryFactory);
        mapping.addHandler("metaWeblog", MetaweblogApi.class);
        mapping.addHandler("blogger",MetaweblogApi.class);
        return mapping;
    }
}
