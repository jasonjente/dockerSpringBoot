package project.persistence.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.persistence.dto.ProductDTO;
import project.persistence.dao.OrderRepository;
import project.persistence.dao.ProductRepository;
import project.persistence.model.OrderItem;
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
class DataServiceImplTest {

    @InjectMocks
    DataServiceImpl dataService;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderRepository orderRepository;

    private ProductDTO productDTO;

    private Product product;

    private ProductDTO product2;

    private Orders order;

    private OrderItem orderItem1;

    private OrderItem orderItem2;

    private OrderItem orderItem3;

    @BeforeEach
    public void setup(){
        productDTO = new ProductDTO();
        productDTO.setProductId(1L);
        productDTO.setState("IN_STOCK");
        productDTO.setDescription("A product description.");
        productDTO.setProductName("A product name.");

        product = productDTO.productFromDTO();
        orderItem1.setId(1L);
        orderItem1.setProduct(product);
        orderItem1.setQuantity(2);

        order = new Orders();
        Set<OrderItem> set = new HashSet<>();
        set.add(orderItem1);
        order.setOrderItems(set);
        order.setRecipientAddress("Address 123");
        order.setRecipientEmail("Email@email.com");
        order.setRecipientFirstName("FirstName");
        order.setRecipientLastName("LastName");
        order.setOrderId(1L);
        order.setOrderState("IN_PROGRESS");
    }

    @Test
    void saveAProduct(){
        given(productRepository.save(product)).willReturn(product);

        // when -  action or the behaviour that we are going test
        Product savedProduct = dataService.createProduct(productDTO.productFromDTO());

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

    @Test
    void saveAProductWithInvalidState(){

        // when -  action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(DataServiceException.class, () -> {
            dataService.createProduct(product2.productFromDTO());
        } );

        // then - verify the output
        verify(productRepository, never()).save(any(Product.class));
    }


    @Test
    void testGetProductId(){
        given(productRepository.findById(product.getProductId())).willReturn(Optional.ofNullable(product));

        // when -  action or the behaviour that we are going test
        Product savedProduct = dataService.getByProductId(productDTO.getProductId());

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

}
