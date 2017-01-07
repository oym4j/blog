package org.bumishi.techblog.api.domain.model.event;

/**
 * 博客删除事件，用于通知缓存更新
 *
 * @author qiang.xie
 * @date 2017/1/7
 */
public class BlogDeleteEvent {

    private String blogId;

    public BlogDeleteEvent(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogId() {
        return blogId;
    }
}
