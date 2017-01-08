package com.jmgits.sample.sbhcp.web.pub.rest;


import com.jmgits.sample.sbhcp.model.domain.content.Category;
import com.jmgits.sample.sbhcp.web.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by javi.more.garc on 08/01/17.
 * <p>
 * Testing of {@link CategoryRestController}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {TestApplication.class})
@Sql(value = {"/test-data-category.sql"})
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

        assertThat(results, contains(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("Cat 1"))
                        ),
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("title", is("Cat 2"))
                        )
        ));
    }


}
