package com.totallysound.entities;

/**
 * Created by bahr2772 on 3/7/17.
 */


public class WebContent {

    private int id;

    private String property;
    private String value;

    public WebContent () {
    }

    public WebContent (int id, String property, String value) {
        this.id = id;
        this.property = property;
        this.value = value;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
