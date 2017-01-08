package com.jmgits.sample.sbhcp.core.base.content.repository;

import com.jmgits.sample.sbhcp.model.domain.content.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by javier on 08/01/17.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
