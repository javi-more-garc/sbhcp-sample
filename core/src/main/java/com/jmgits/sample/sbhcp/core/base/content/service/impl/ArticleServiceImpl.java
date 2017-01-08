package com.jmgits.sample.sbhcp.core.base.content.service.impl;

import com.jmgits.sample.sbhcp.core.base.content.repository.ArticleRepository;
import com.jmgits.sample.sbhcp.core.base.content.service.ArticleService;
import com.jmgits.sample.sbhcp.model.domain.content.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * Created by javier on 08/01/17.
 */
@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        notNull(articleRepository);

        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
