package com.jmgits.sample.sbhcp.core.base.content.repository;

import com.jmgits.sample.sbhcp.model.domain.content.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by javier on 08/01/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}
