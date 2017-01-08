package com.jmgits.sample.sbhcp.core.base.content.service.impl;

import com.jmgits.sample.sbhcp.core.base.content.repository.CategoryRepository;
import com.jmgits.sample.sbhcp.core.base.content.service.CategoryService;
import com.jmgits.sample.sbhcp.model.domain.content.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * Created by javier on 08/01/17.
 */
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        notNull(categoryRepository);

        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
