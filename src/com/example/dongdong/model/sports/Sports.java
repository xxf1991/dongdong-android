package com.example.dongdong.model.sports;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class Sports {

    private final int id;
    private final int name;
    private final int iconUrl;

    public Sports(int id, int name, int iconUrl) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getIconUrl() {
        return iconUrl;
    }
}
