package org.bumishi.techblog.api.interfaces.manage.facade.dto;

import java.util.Date;

/**
 * Created by xieqiang on 2016/12/18.
 */
public class BlogDto {

    /***/
    private String id;

    /**标题*/
    private String title;

    /**副标题*/
    private String sencondTitle;

    /**分类标识*/
    private String catalog;

    /**分类展示文本*/
    private String catalogDisplay;

    /**展示内容*/
    private String display;

    /**markdownd原内容*/
    private String md;

    /**作者*/
    private String auther;

    /**发布时间*/
    private Date publishTime;

    /**封面图片，主要迎合微信公众号*/
    private String img;

    /**文章概要*/
    private String summary;

    /**文章链接*/
    private String link;

    /**阅读量*/
    private long views;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSencondTitle() {
        return sencondTitle;
    }

    public void setSencondTitle(String sencondTitle) {
        this.sencondTitle = sencondTitle;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCatalogDisplay() {
        return catalogDisplay;
    }

    public void setCatalogDisplay(String catalogDisplay) {
        this.catalogDisplay = catalogDisplay;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
