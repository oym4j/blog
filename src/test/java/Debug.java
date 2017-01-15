import com.alibaba.fastjson.JSON;
import org.bumishi.techblog.api.BlogApplication;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.infrastructure.persistence.es.BlogElasticSearchRepositry;
import org.bumishi.techblog.api.infrastructure.persistence.jdbc.BlogQueryJdbcRepositry;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author qiang.xie
 * @date 2017/1/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class Debug {

    @Autowired
    protected BlogElasticSearchRepositry blogElasticSearchRepositry;

    @Autowired
    protected BlogQueryJdbcRepositry blogQueryJdbcRepositry;

    @org.junit.Test
    public void save() {
        List<Blog> blogs=blogQueryJdbcRepositry.queryByTime(1,100).getList();
        for(Blog blog : blogs){
            blogElasticSearchRepositry.save(blog);
        }

        System.out.println(JSON.toJSONString(blogElasticSearchRepositry.queryByKeyword(1, 1,"管理")));

    }

}
