package com.jmgits.sample.sbhcp.web.user.rest;

import com.jmgits.sample.sbhcp.core.base.content.service.CategoryService;
import com.jmgits.sample.sbhcp.model.domain.content.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.Assert.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by javier on 08/01/17.
 */
@RestController
@RequestMapping("/api/v1/user/articles")
public class ArticleRestController {

    private final CategoryService categoryService;

    public ArticleRestController(CategoryService categoryService) {

        notNull(categoryService);

        this.categoryService = categoryService;
    }

    @RequestMapping(method = GET)
    public List<Category> findAll(){
        return categoryService.findAll();
    }
}
