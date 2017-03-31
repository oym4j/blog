package org.bumishi.techblog.api.interfaces.manage.metaweblog;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bumishi.techblog.api.interfaces.manage.facade.BlogFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.CatalogFacade;
import org.bumishi.techblog.api.interfaces.manage.facade.command.WriteBlogCommand;
import org.bumishi.techblog.api.interfaces.manage.facade.dto.BlogDto;
import org.bumishi.toolbox.model.NavigationNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author qiang.xie
 * @date 2017/3/31
 */
@Component
public class MetaweblogApi {

    @Autowired
    private CatalogFacade catalogFacade;

    @Autowired
    private BlogFacade blogFacade;

    @Value("${metaweblog.user}")
    private String user;

    @Value("${metaweblog.pwd}")
    private String pwd;



    //this method only for validate
    public List<Object> getUsersBlogs(String key,String username, String password){
        List<BlogDto> blogs=blogFacade.pageQuery(1,1).getList();
        JSONArray jsonArray=new JSONArray(blogs.size());
        blogs.stream().forEach(item->{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("blogid",item.getId());
            jsonObject.put("url","http://172.1710.159:8686/blog/"+item.getId());
            jsonObject.put("blogName","不迷失");
            jsonArray.add(jsonObject);
        });
        return jsonArray;

    }

    public Object getPost(String key,String username, String password){
        BlogDto item=blogFacade.getBlog(key);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("postid",item.getId());
            jsonObject.put("userid","bumishi");
            jsonObject.put("datetime",item.getPublishTime());
            jsonObject.put("content",item.getMd());
        return jsonObject;

    }
    public String newPost(String blogid, String username, String password, Map<String,Object> struct, boolean publish){
        validateAccount(username,password);
        WriteBlogCommand writeBlogCommand=new WriteBlogCommand();
        writeBlogCommand.setCatalog(((Object[])struct.get("categories"))[0].toString());
        writeBlogCommand.setTitle(struct.get("title").toString());
        writeBlogCommand.setDisplay(struct.get("description").toString());
        writeBlogCommand.setMd("==");
        writeBlogCommand.setImg("==");
        return blogFacade.createBlog(writeBlogCommand);

    }

    public String editPost(String blogid, String username, String password, Map<String,Object> struct, boolean publish){
        validateAccount(username,password);

        WriteBlogCommand writeBlogCommand=new WriteBlogCommand();
        writeBlogCommand.setCatalog(((Object[])struct.get("categories"))[0].toString());
        writeBlogCommand.setTitle(struct.get("title").toString());
        writeBlogCommand.setDisplay(struct.get("description").toString());
        writeBlogCommand.setMd("==");
        writeBlogCommand.setImg("==");
        blogFacade.updateBlog(blogid,writeBlogCommand);
        return blogid;
    }
    public List getCategories (String blogid, String username, String password){
        validateAccount(username,password);
        List<NavigationNode> list=catalogFacade.listByOrder();
        JSONArray jsonArray=new JSONArray(list.size());
        list.stream().forEach(item->{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("description",item.getId());
            jsonObject.put("htmlUrl",item.getUrl());
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    public void validateAccount(String user,String pwd){
        if(!(this.user.equals(user) && this.pwd.equals(pwd))){
            throw new IllegalAccessError("非法访问");
        }
    }
}
