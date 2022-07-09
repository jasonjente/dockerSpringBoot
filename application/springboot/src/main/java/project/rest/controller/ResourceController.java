package project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.rest.exception.DataServiceException;
import project.service.DataService;

import java.util.List;

@Controller
@RequestMapping(path = "api/v1/rest/",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResourceController {
    Logger logger = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    DataService dataService;
    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        try{
            List<Product> ret = dataService.getAllProducts();
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }


    }

    @GetMapping("/products/{productId}")
    public ResponseEntity getProductById(@PathVariable("productId") Long productId){
        try{
            Product ret = dataService.getByProductId(productId);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }

    }

    @PostMapping("/products")
    ResponseEntity saveAProduct(@RequestBody Product product){
       try{
           dataService.createProduct(product);
           return ResponseEntity.ok(product);
       }catch (DataServiceException dataServiceException){
           logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
           if(dataServiceException.isUserError()) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }else {
               return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
           }
       }
    }

    @GetMapping("/aproduct/")
    public ResponseEntity getProduct(Long productId){
        Product ret = new Product();
        ret.setState("IN_STOCK");
        ret.setDescription("IN_STOCK");
        ret.setTitle("IN_STOCK");
        try{
            dataService.createProduct(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
        return ResponseEntity.ok(ret);
    }
    
    @PostMapping("/products/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId,@RequestBody Product product){
        try {
            Product ret = dataService.updateProduct(productId, product);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @GetMapping("/orders")
    public ResponseEntity getAllOrders(){
        try {
            List<Orders> ret = dataService.getAllOrders();
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity getOrderById(@PathVariable("orderId") Long orderId){
        try {
            Orders ret = dataService.getByOrderId(orderId);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @PostMapping("/orders")
    ResponseEntity saveAnOrder(@RequestBody Orders order){
        try {
            dataService.createOrder(order);
            return ResponseEntity.ok(order);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }

    @GetMapping("/aorder/")
    public ResponseEntity getOrder(){
        Orders ret = new Orders();
        ret.setDescription("IN_STOCK");
        ret.setTitle("IN_STOCK");
        ret.setOrderState("IN_PROGRESS.");
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity updateOrder(@PathVariable("orderId") Long orderId,@RequestBody Orders order){
        try {
            Orders ret = dataService.updateOrder(orderId, order);
            return ResponseEntity.ok(ret);
        }catch (DataServiceException dataServiceException){
            logger.error("Error message : {} .Is user error: {}. cause of error: " ,dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
            if(dataServiceException.isUserError()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
        }
    }
}
