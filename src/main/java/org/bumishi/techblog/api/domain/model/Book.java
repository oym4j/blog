package org.bumishi.techblog.api.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xieqiang on 2016/11/27.
 */
public class Book implements Serializable{

    private String id;

    private String name;

    private String catalog;

    private String description;

    private Date publishTime;

    private List<BookIndex> indexTree;//目录数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public List<BookIndex> getIndexTree() {
        return indexTree;
    }

    public void setIndexTree(List<BookIndex> indexTree) {
        this.indexTree = indexTree;
    }
}
