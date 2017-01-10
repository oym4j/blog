import com.alibaba.fastjson.JSON;
import org.bumishi.techblog.api.Application;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.infrastructure.persistence.es.BlogElasticSearchRepositry;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author qiang.xie
 * @date 2017/1/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Debug {

    @Autowired
    protected BlogElasticSearchRepositry blogQueryRepositry;

    @org.junit.Test
    public void save() {
        Blog blog = new Blog();
        blog.setId("1");
        blog.setTitle("java2");
        blog.setDisplay("Java2，时间");
        blog.setPublishTime(new Date());
        blog.setCatalog("java");
        blogQueryRepositry.save(blog);

        blog.setTitle("java hellowork");
        blogQueryRepositry.update(blog);

        System.out.println(JSON.toJSONString(blogQueryRepositry.get("1")));

        System.out.println(JSON.toJSONString(blogQueryRepositry.queryByTime(1, 1)));

    }

}
