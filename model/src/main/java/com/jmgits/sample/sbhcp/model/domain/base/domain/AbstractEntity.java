package com.jmgits.sample.sbhcp.model.domain.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.persistence.GenerationType.SEQUENCE;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Created by javi.more.garc on 08/01/17.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final List<Class<? extends Annotation>> ANNOTATIONS_TO_EXCLUDE =
            asList(OneToOne.class, OneToMany.class, ManyToOne.class, ManyToMany.class);

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID", nullable = false, updatable = false)
    protected Long id;

    @JsonIgnore
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @JsonIgnore
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {

        List<String> excluded = new ArrayList<>();

        ANNOTATIONS_TO_EXCLUDE.forEach(annotationClass ->
                FieldUtils.getFieldsListWithAnnotation(this.getClass(), annotationClass)
                        .forEach(field -> excluded.add(field.getName()))
        );

        return new ReflectionToStringBuilder(this, SHORT_PREFIX_STYLE)
                .setExcludeFieldNames(excluded.stream().toArray(String[]::new))
                .toString();
    }
}
