package project.persistence.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.persistence.dao.OrderRepository;
import project.persistence.dao.ProductRepository;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.rest.exception.DataServiceException;
import project.service.impl.DataServiceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class DataServiceImplTest {

    @InjectMocks
    DataServiceImpl dataService;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderRepository orderRepository;

    private Product product;

    private Product product2;

    private Orders order;

    @BeforeEach
    public void setup(){
        product = new Product();
        product.setKey(1L);
        product.setState("IN_STOCK");
        product.setDescription("A product description.");
        product.setTitle("A product title.");

        product2 = new Product();
        product2.setState("INVALID");
        product2.setDescription("A product description.");
        product2.setTitle("A product title.");

        order = new Orders();
        Set<Product> productsForOrder = new HashSet<>();
        productsForOrder.add(product);
        order.setOrderProducts(productsForOrder);
        order.setOrderState("IN_PROGRESS");
        order.setDescription("Order description");
        order.setTitle("Title for the Order");
    }

    @Test
    public void saveAProduct(){
        given(productRepository.save(product)).willReturn(product);

        // when -  action or the behaviour that we are going test
        Product savedProduct = dataService.createProduct(product);

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

    @Test
    public void saveAProductWithInvalidState(){

        // when -  action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(DataServiceException.class, () -> {
            dataService.createProduct(product2);
        } );

        // then - verify the output
        verify(productRepository, never()).save(any(Product.class));
    }


    @Test
    public void testGetProductId(){
        given(productRepository.findById(product.getProductId())).willReturn(Optional.ofNullable(product));

        // when -  action or the behaviour that we are going test
        Product savedProduct = dataService.getByProductId(product.getProductId());

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

}
