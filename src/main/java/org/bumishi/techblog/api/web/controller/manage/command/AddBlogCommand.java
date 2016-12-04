package org.bumishi.techblog.api.web.controller.manage.command;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by xieqiang on 2016/12/4.
 */
public class AddBlogCommand {

    /**主标题*/
    @NotBlank
    @Length(min = 5,max = 50)
    private String title;

    /**副标题*/
    @Length(min = 5,max = 30)
    private String sencondTitle;

    /**分类*/
    @Length(max = 20)
    private String catalog;

    /**markdownd原内容*/
    @NotBlank
    private String md;

    @NotBlank
    /**封面图片，主要迎合微信公众号*/
    private String img;

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

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
