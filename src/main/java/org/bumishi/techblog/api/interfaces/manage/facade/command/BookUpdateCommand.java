package org.bumishi.techblog.api.interfaces.manage.facade.command;

/**
 * Created by xieqiang on 2016/12/18.
 */
public class BookUpdateCommand {

    private String name;

    private String catalog;

    private String description;

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
}
