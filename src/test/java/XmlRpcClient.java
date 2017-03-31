import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author qiang.xie
 * @date 2017/3/31
 */
public class XmlRpcClient {

    public static void main(String[] arg) throws Exception{

        Properties prop = System.getProperties();
        // 设置http访问要使用的代理服务器的地址
        prop.setProperty("http.proxyHost", "127.0.0.1");
        // 设置http访问要使用的代理服务器的端口
        prop.setProperty("http.proxyPort", "8888");

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost.:8686/metaweblog/api"));

        org.apache.xmlrpc.client.XmlRpcClient client = new org.apache.xmlrpc.client.XmlRpcClient();
        client.setConfig(config);

        Object[] params = new Object[]{"id","test","test"};
        System.out.println(client.execute("metaWeblog.getCategories", params));


        Map<String, String> post = new HashMap<>();
        post.put("title", "rpc");// 标题
        post.put("categories", "java");// 分类
       post.put("description", "xxxxx");// 内容
        Object[] params2 = new Object[]{"id","test","test",post,true};
        System.out.println(client.execute("blogger.getUsersBlogs", params));

    }
}
