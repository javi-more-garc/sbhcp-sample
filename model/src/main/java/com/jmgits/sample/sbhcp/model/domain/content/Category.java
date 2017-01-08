package com.jmgits.sample.sbhcp.model.domain.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmgits.sample.sbhcp.model.domain.base.domain.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * Created by javier on 08/01/17.
 */
@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "generator", sequenceName = "SEC_CATEGORY", initialValue = 1000)
public class Category extends AbstractEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @JsonIgnore
    @OneToMany(fetch = LAZY, cascade = ALL, orphanRemoval = true, mappedBy = "category")
    private List<Article> articles = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
