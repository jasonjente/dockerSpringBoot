package project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.persistence.dto.ProductDTO;
import project.persistence.model.Customer;
import project.persistence.model.OrderItem;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.rest.exception.DataServiceException;
import project.service.CustomerService;
import project.service.DataService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "api/v1/rest/",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResourceController {
    Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    DataService dataService;

    @Autowired
    CustomerService customerService;
    private String baseErrorMessage = "Error message : {} .Is user error: {}. cause of error: ";

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            List<Product> ret = dataService.getAllProducts();
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }


    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
        try{
            Product ret = dataService.getByProductId(productId);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }

    }

    @PostMapping("/products")
    ResponseEntity<Product> saveAProduct(@RequestBody ProductDTO productDTO){
       try{
           Product product = productDTO.productFromDTO();
           dataService.createProduct(product);
           return ResponseEntity.ok(product);
       }catch (DataServiceException dataServiceException){
           logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
           if(dataServiceException.isUserError()) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }else {
               return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
           }
       }
    }

    @GetMapping("/aproduct/")
    public ResponseEntity<Product> getProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setState("IN_STOCK");
        productDTO.setDescription("IN_STOCK");
        productDTO.setProductName("IN_STOCK");
        productDTO.setProductCode("kjjdhv");
        productDTO.setPrice(23D);
        Product ret = null;
        try{
            ret = dataService.createProduct(productDTO.productFromDTO());
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
        return ResponseEntity.ok(ret);
    }
    
    @PostMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody ProductDTO productDTO){
        try {
            Product ret = dataService.updateProduct(productId, productDTO.productFromDTO());
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        try {
            List<Orders> ret = dataService.getAllOrders();
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("orderId") Long orderId){
        try {
            Orders ret = dataService.getByOrderId(orderId);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @PostMapping("/orders")
    ResponseEntity<Orders> saveAnOrder(@RequestBody Orders order){
        try {
            dataService.createOrder(order);
            return ResponseEntity.ok(order);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @GetMapping("/aorder/")
    public ResponseEntity<Orders> getOrder(){
        ProductDTO product = new ProductDTO();
        product.setState("IN_STOCK");
        product.setDescription("8GB V-RAM \n 1500 Cuda cores \n 120 RT cores.");
        product.setProductName("Nvidia 3070TI");
        product.setProductCode("286864");
        product.setPrice(700D);
        ProductDTO product2 = new ProductDTO();
        product2.setState("IN_STOCK");
        product2.setDescription("16GB V-RAM.");
        product2.setProductName("AMD RX6800XT");
        product2.setProductCode("AMD54459hjff");
        product2.setPrice(800D);
        Product saveProd1 = product.productFromDTO();
        Product saveProd2 = product2.productFromDTO();
        dataService.createProduct(saveProd1);
        dataService.createProduct(saveProd2);
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(saveProd1);
        orderItem1.setQuantity(2);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(saveProd2);
        orderItem2.setQuantity(1);

        Orders order = new Orders();
        Set<OrderItem> set = new HashSet<>();
        set.add(orderItem1);
        set.add(orderItem2);
        order.setOrderItems(set);
        order.setRecipientAddress("Address 123");
        order.setRecipientEmail("Email@email.com");
        order.setRecipientFirstName("FirstName");
        order.setRecipientLastName("LastName");
        order.setOrderState("PROCESSING");
        order.calculateTotal();
        dataService.createOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Customer> getUserById(@PathVariable("userId") Long userId){
        try {
            Customer ret = customerService.getCustomerById(userId);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }
    @PostMapping("/orders/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("orderId") Long orderId,@RequestBody Orders order){
        try {
            Orders ret = dataService.updateOrder(orderId, order);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error(baseErrorMessage ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }
}
