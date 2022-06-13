package project.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.service.DataService;

import java.util.List;

@Controller
public class ResourceController {

    @Autowired
    DataService dataService;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<Product> ret = dataService.getAllProducts();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity getProductById(@PathVariable("productId") Long productId){
        return ResponseEntity.ok(dataService.getByProductId(productId));
    }

    @PostMapping("/products")
    ResponseEntity saveAProduct(@RequestBody Product product){
        dataService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/aproduct/")
    public ResponseEntity getProduct(Long productId){
        Product ret = new Product();
        ret.setState("IN_STOCK");
        ret.setDescription("IN_STOCK");
        ret.setTitle("IN_STOCK");
        return ResponseEntity.ok(ret);
    }
    
    @PostMapping("/product/{productId}")
    public ResponseEntity updateProduct(Long productId, Product product){
        Product ret = dataService.updateProduct(productId, product);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/orders")
    public ResponseEntity getAllOrders(){
        List<Orders> ret = dataService.getAllOrders();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity getOrderById(@PathVariable("orderId") Long orderId){
        return ResponseEntity.ok(dataService.getByOrderId(orderId));
    }

    @PostMapping("/orders")
    ResponseEntity saveAOrder(@RequestBody Orders order){
        dataService.createOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/aorder/")
    public ResponseEntity getOrder(Long orderId){
        Orders ret = new Orders();
        ret.setDescription("IN_STOCK");
        ret.setTitle("IN_STOCK");
        ret.setOrderState("IN_PROGRESS.");
        return ResponseEntity.ok(ret);
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity updateOrder(Long orderId, Orders order){
        Orders ret = dataService.updateOrder(orderId, order);
        return ResponseEntity.ok(ret);
    }
}
