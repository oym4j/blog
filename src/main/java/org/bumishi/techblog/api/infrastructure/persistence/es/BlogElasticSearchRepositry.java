package org.bumishi.techblog.api.infrastructure.persistence.es;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.bumishi.techblog.api.domain.model.Blog;
import org.bumishi.techblog.api.domain.repository.BlogCommandRepositry;
import org.bumishi.techblog.api.domain.repository.BlogQueryRepositry;
import org.bumishi.toolbox.model.PageModel;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqiang on 2017/1/9.
 */
@Repository
public class BlogElasticSearchRepositry implements BlogCommandRepositry, BlogQueryRepositry {

    @Autowired
    private TransportClient client;

    @Override
    public void save(Blog blog) {
        client.prepareIndex("blog", "blog").setId(blog.getId())
                .setSource(JSON.toJSONString(blog))
                .get();
    }

    @Override
    public void update(Blog blog) {
        client.prepareUpdate("blog", "blog", blog.getId()).setDoc(JSON.toJSONString(blog))
                .get();
    }

    @Override
    public void remove(String id) {
        client.prepareDelete("blog", "blog", id).get();
    }

    @Override
    public Blog get(String id) {
        return JSON.parseObject(client.prepareGet("blog", "blog", "1").get().getSourceAsString(), Blog.class);
    }

    @Override
    public PageModel<Blog> queryByKeyword(int page, int size, String keyword) {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        if (StringUtils.isNotBlank(keyword)) {
            queryBuilder = QueryBuilders.multiMatchQuery(keyword, "title", "secondTitle", "md");
        }
        SearchResponse searchResponse = client.prepareSearch("blog").setTypes("blog").setFrom((page - 1) * size).setSize(size).setQuery(queryBuilder).addSort("publishTime", SortOrder.DESC).get();
        return getBlogPageModel(page, size, searchResponse);
    }


    @Override
    public PageModel<Blog> queryByCatalog(int page, int size, String catalog) {
        SearchResponse searchResponse = client.prepareSearch("blog").setTypes("blog").setFrom((page - 1) * size).setSize(size).setQuery("all".equals(catalog) ? QueryBuilders.matchAllQuery() : QueryBuilders.matchQuery("catalog", catalog)).addSort("publishTime", SortOrder.DESC).get();
        return getBlogPageModel(page, size, searchResponse);
    }

    private PageModel<Blog> getBlogPageModel(int page, int size, SearchResponse searchResponse) {
        SearchHit[] hits = searchResponse.getHits().getHits();
        if (hits == null || hits.length == 0) {
            return new PageModel<>();
        }
        PageModel<Blog> pageModel = new PageModel<>();
        pageModel.setSize(size);
        pageModel.setPage(page);
        pageModel.setHasNext(hits.length >= size);
        List<Blog> blogs = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            blogs.add(JSON.parseObject(searchHit.getSourceAsString(), Blog.class));
        }
        pageModel.setList(blogs);
        return pageModel;
    }
    @Override
    public PageModel<Blog> queryByTime(int page, int size) {
        return queryByKeyword(page, size, "");
    }

    @Override
    public int getCount() {
        return Long.valueOf(client.prepareSearch("blog").setTypes("blog").setQuery(QueryBuilders.matchAllQuery()).get().getHits().getTotalHits()).intValue();
    }

    @PostConstruct
    protected void initIndex() {
        if (!client.admin().indices().prepareExists("blog").get().isExists()) {
            client.admin().indices().prepareCreate("blog").setSettings(Settings.builder()
                    .put("index.number_of_shards", 2)
                    .put("index.number_of_replicas", 0)
            ).addMapping("blog", "{\n" +
                    "      \"properties\": { \n" +
                    "        \"title\":    { \"type\": \"text\"  }, \n" +
                    "        \"secondTitle\":    { \"type\": \"text\"  }, \n" +
                    "        \"catalog\":    { \"type\": \"keyword\"  },\n" +
                    "        \"auther\":    { \"type\": \"keyword\", \"index\":\"no\"  },  \n" +
                    "        \"img\":    { \"type\": \"text\", \"index\":\"no\"  },  \n" +
                    "        \"md\":     { \"type\": \"text\",\"index\":\"not_analyzed\"}, \n" +
                    "        \"display\":  {\n" +
                    "          \"type\":   \"text\",\n" +
                    "          \"index\":\"no\"\n" +
                    "          \n" +
                    "        },\n" +
                    "        \"publishTime\":  {\n" +
                    "          \"type\":   \"date\", \n" +
                    "          \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "}\n").get();


        }
    }

    @PreDestroy
    private void closeClient() {
        client.close();
    }
}
