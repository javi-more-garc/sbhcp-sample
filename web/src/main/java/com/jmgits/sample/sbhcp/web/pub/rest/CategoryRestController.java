package com.jmgits.sample.sbhcp.web.pub.rest;

import com.jmgits.sample.sbhcp.core.base.content.service.CategoryService;
import com.jmgits.sample.sbhcp.core.base.content.view.CategoryCreate;
import com.jmgits.sample.sbhcp.core.base.content.view.CategoryUpdate;
import com.jmgits.sample.sbhcp.model.domain.content.Category;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.util.Assert.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by javier on 08/01/17.
 */
@RestController
@RequestMapping("/api/v1/public/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {

        notNull(categoryService);

        this.categoryService = categoryService;
    }

    @RequestMapping(method = GET)
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Category create(@Validated @RequestBody CategoryCreate criteria) {
        return categoryService.create(criteria);
    }

    @RequestMapping(value = "/{id}", method = PATCH)
    public Category update(@PathVariable Long id, @Validated @RequestBody CategoryUpdate criteria) {
        return categoryService.update(id, criteria);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
