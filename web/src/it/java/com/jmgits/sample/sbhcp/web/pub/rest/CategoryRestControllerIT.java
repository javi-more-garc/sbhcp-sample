package com.jmgits.sample.sbhcp.web.pub.rest;


import com.jmgits.sample.sbhcp.core.base.content.view.CategoryCreate;
import com.jmgits.sample.sbhcp.core.base.content.view.CategoryUpdate;
import com.jmgits.sample.sbhcp.model.domain.content.Category;
import com.jmgits.sample.sbhcp.web.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

/**
 * Created by javi.more.garc on 08/01/17.
 * <p>
 * Testing of {@link CategoryRestController}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {TestApplication.class})
@Sql(value = {"/test-data-cleanup.sql", "/test-data-category.sql"})
public class CategoryRestControllerIT {

    @Inject
    private TestRestTemplate restTemplate;

    @Test
    public void testFindAll() throws Exception {

        // business
        ResponseEntity<Category[]> responseEntity =
                this.restTemplate.exchange("/api/v1/public/categories",
                        GET, null, Category[].class);

        assertThat(responseEntity.getStatusCode(), is(OK));

        List<Category> results = asList(responseEntity.getBody());

        assertThat(results, hasSize(2));
    }

    @Test
    public void testFindById() throws Exception {

        // business
        ResponseEntity<Category> responseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/1",
                        GET, null, Category.class);

        assertThat(responseEntity.getStatusCode(), is(OK));

        Category result = responseEntity.getBody();

        assertThat(result, allOf(
                hasProperty("id", is(1L)),
                hasProperty("title", is("Cat 1"))
        ));
    }

    @Test
    public void testFindByIdKoNotFound() throws Exception {

        // business
        ResponseEntity<Category> responseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/12345",
                        GET, null, Category.class);

        assertThat(responseEntity.getStatusCode(), is(NOT_FOUND));
    }

    @Test
    public void testCreate() throws Exception {

        CategoryCreate criteria = new CategoryCreate();
        criteria.setTitle("myTitle");

        // business
        ResponseEntity<Category> createResponseEntity =
                this.restTemplate.exchange("/api/v1/public/categories",
                        POST, new HttpEntity<>(criteria), Category.class);

        assertThat(createResponseEntity.getStatusCode(), is(CREATED));

        Long id = createResponseEntity.getBody().getId();

        ResponseEntity<Category> responseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/" + id,
                        GET, null, Category.class);

        Category result = responseEntity.getBody();

        assertThat(result, allOf(
                hasProperty("id", is(id)),
                hasProperty("title", is("myTitle"))
        ));
       
    }

    @Test
    public void testUpdate() throws Exception {

        CategoryUpdate criteria = new CategoryUpdate();
        criteria.setTitle("myTitle");

        // business
        ResponseEntity<Category> createResponseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/1",
                        PATCH, new HttpEntity<>(criteria), Category.class);

        assertThat(createResponseEntity.getStatusCode(), is(OK));

        ResponseEntity<Category> responseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/1",
                        GET, null, Category.class);

        Category result = responseEntity.getBody();

        assertThat(result, allOf(
                hasProperty("id", is(1L)),
                hasProperty("title", is("myTitle"))
        ));

    }

    @Test
    public void testDelete() throws Exception {

        // business
        ResponseEntity<?> deleteResponseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/1",
                        DELETE, null, String.class);

        assertThat(deleteResponseEntity.getStatusCode(), is(NO_CONTENT));

        ResponseEntity<Category> responseEntity =
                this.restTemplate.exchange("/api/v1/public/categories/1",
                        GET, null, Category.class);

        assertThat(responseEntity.getStatusCode(), is(NOT_FOUND));


    }

}
