package com.jmgits.sample.sbhcp.core.base.content.repository;

import com.jmgits.sample.sbhcp.model.domain.content.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by javier on 08/01/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
