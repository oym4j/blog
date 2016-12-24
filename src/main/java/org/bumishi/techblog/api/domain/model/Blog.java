package org.bumishi.techblog.api.domain.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author xieqiang
 * @date 2016-11-24
 */

public class Blog implements Serializable{

	private static final long serialVersionUID = 5356889118843969137L;

		/***/
	 private String id;

	/**标题*/
	 private String title;

	/**副标题*/
	private String secondTitle;

	/**分类*/
	 private String catalog;

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
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

	public String getSecondTitle() {
		return secondTitle;
	}

	public void setSecondTitle(String secondTitle) {
		this.secondTitle = secondTitle;
	}

	public static String convertCatalog(String catalog){
		if(catalog.equals("java"))return "Java世界";
		if(catalog.equals("web"))return "web前端";
		if(catalog.equals("code"))return "代码脚本";
		if(catalog.equals("dosite"))return "建站运营";
		if(catalog.equals("book"))return "图书";
		if(catalog.equals("carrer"))return "职业生涯";
		if(catalog.equals("expr"))return "技术经验";
		return "其它";
	}
    
}
