package com.jmgits.sample.sbhcp.core.base.content.view;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by javier on 08/01/17.
 */
public class CategoryCreate {

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
