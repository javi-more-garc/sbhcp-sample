package com.jmgits.sample.sbhcp.model.domain.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmgits.sample.sbhcp.model.domain.base.domain.AbstractEntity;

import javax.persistence.*;

/**
 * Created by javier on 08/01/17.
 */
@Entity
@Table(name = "ARTICLE")
@SequenceGenerator(name = "generator", sequenceName = "SEC_ARTICLE", initialValue = 1000)
public class Article extends AbstractEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Category category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
