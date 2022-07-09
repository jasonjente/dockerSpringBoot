package project.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import project.persistence.dao.ProductRepository;
import project.persistence.model.Product;
import project.rest.controller.ResourceController;
import project.service.impl.DataServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ResourceController.class)
@WebAppConfiguration
public class ResourceControllerTest {
    private MockMvc mvc;

    @Autowired
    ResourceController resourceController;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    DataServiceImpl dataService;

    @MockBean
    ProductRepository productRepository;

    private Product product;
    private Product product2;
    private Product product3;
    private List<Product> productList;

    @Before
    public void init(){
        product = new Product();
        product.setKey(1L);
        product.setState("IN_STOCK");
        product.setDescription("A product 1 description.");
        product.setTitle("A product 1 title.");

        product2 = new Product();
        product2.setKey(2L);
        product2.setState("IN_STOCK");
        product2.setDescription("A product 2 description.");
        product2.setTitle("A product 2 title.");

        product3 = new Product();
        product3.setKey(3L);
        product3.setState("IN_STOCK");
        product3.setDescription("A product 3 description.");
        product3.setTitle("A product 3 title.");
        productList = new ArrayList<>(Arrays.asList(product, product2, product3));

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void contextLoads() {
        assertThat(resourceController).isNotNull();
    }

 /*   @Test
    public void testGetAllProducts() throws Exception {
        String uri = "/api/v1/rest/products";
        Product product = new Product();
        product.setKey(3L);
        product.setDescription("description");
        product.setTitle("title");
        product.setState("IN_STOCK");

        String inputJson = mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }*/



    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
