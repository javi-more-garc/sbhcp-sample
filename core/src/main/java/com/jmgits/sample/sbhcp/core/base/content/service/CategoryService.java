package com.jmgits.sample.sbhcp.core.base.content.service;

import com.jmgits.sample.sbhcp.model.domain.content.Category;

import java.util.List;

/**
 * Created by javier on 08/01/17.
 */
public interface CategoryService {

    List<Category> findAll();
}
