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
	 
	 private String title;

	/**分类*/
	 private String catalog;

	/**html content*/
	 private String content;

	/**markdownd原内容*/
	private String md;

	/**作者*/
	 private String auther;

	/**发布时间*/
	 private Date publishTime;
	 
	 /**浏览次数*/
	 private int views=0;

	
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
