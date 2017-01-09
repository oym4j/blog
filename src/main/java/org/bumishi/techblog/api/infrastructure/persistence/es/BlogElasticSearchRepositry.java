package org.bumishi.techblog.api.infrastructure.persistence.es;

import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.toolbox.model.PageModel;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

/**
 * Created by xieqiang on 2017/1/9.
 */
@Repository
public class BlogElasticSearchRepositry implements BlogCommandRepositry, BlogQueryRepositry {

    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void save(Blog blog) {
        elasticsearchTemplate.index(new IndexQueryBuilder().withIndexName("blog").withType("blog").withId(blog.getId()).withObject(blog).build());
    }

    @Override
    public void update(Blog blog) {
        elasticsearchTemplate.index(new IndexQueryBuilder().withIndexName("blog").withType("blog").withId(blog.getId()).withObject(blog).build());

    }

    @Override
    public void remove(String id) {
            elasticsearchTemplate.delete("blog","blog",id);
    }

    @Override
    public Blog get(String id) {
        GetQuery getQuery=new GetQuery();
        getQuery.setId(id);
        return elasticsearchTemplate.queryForObject(getQuery,Blog.class);
    }

    @Override
    public PageModel<Blog> queryByKeyword(int page, int size, String keyword) {
        new NativeSearchQueryBuilder().withIndices("blog").withTypes("blog").withQuery().build();
        return null;
    }

    @Override
    public PageModel<Blog> queryByCatalog(int page, int size, String catalog) {
        return null;
    }

    @Override
    public PageModel<Blog> queryByTime(int page, int size) {
        return null;
    }

    @Override
    public int getCount() {

        return Long.valueOf(elasticsearchTemplate.count(new NativeSearchQueryBuilder().withFields("id").withIndices("blog").withTypes("blog").build())).intValue();
    }
}
