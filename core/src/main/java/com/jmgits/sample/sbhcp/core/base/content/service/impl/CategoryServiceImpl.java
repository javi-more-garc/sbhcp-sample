package com.jmgits.sample.sbhcp.core.base.content.service.impl;

import com.jmgits.sample.sbhcp.common.exception.ErrorCodeException;
import com.jmgits.sample.sbhcp.core.base.content.repository.CategoryRepository;
import com.jmgits.sample.sbhcp.core.base.content.service.CategoryService;
import com.jmgits.sample.sbhcp.core.base.content.view.CategoryCreate;
import com.jmgits.sample.sbhcp.core.base.content.view.CategoryUpdate;
import com.jmgits.sample.sbhcp.model.domain.content.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.jmgits.sample.sbhcp.common.exception.ErrorCode.CATEGORY_NOT_FOUND;
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

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ErrorCodeException(CATEGORY_NOT_FOUND,
                        String.format("Category with id '%d' not found", id))
        );
    }

    @Override
    @Transactional(readOnly = false)
    public Category create(CategoryCreate criteria) {

        Category category = new Category();

        category.setTitle(criteria.getTitle());

        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = false)
    public Category update(Long id, CategoryUpdate criteria) {

        Category existing = findById(id);

        Optional.ofNullable(criteria.getTitle()).ifPresent(existing::setTitle);

        return categoryRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {

        Category existing = findById(id);

        categoryRepository.delete(existing);
    }
}
