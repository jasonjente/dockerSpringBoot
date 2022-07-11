package project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.persistence.dto.CustomerDTO;
import project.persistence.dto.ProductDTO;
import project.persistence.model.Customer;
import project.persistence.model.OrderItem;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.exception.DataServiceException;
import project.service.CustomerService;
import project.service.DataService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "api/v1/rest/",
        produces = MediaType.ALL_VALUE,
        consumes = MediaType.ALL_VALUE)
public class CustomerResource {
    private static final String IN_STOCK = "IN_STOCK";
    Logger logger = LoggerFactory.getLogger(CustomerResource.class);


    private DataService dataService;

    private CustomerService customerService;

    @GetMapping(path = "/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerOrders(@PathVariable Long customerId){
        logger.info("getCustomerOrders - Enter: ");
        try{
            Customer customer = customerService.getCustomerById(customerId);
            logger.info("getCustomerOrders - Leave: ");
            return ResponseEntity.ok(customer);
        }catch (DataServiceException dataServiceException){
            return responseStatusBasedOnException(dataServiceException);
        }

    }

    @PostMapping(path = "/customers/")
    public ResponseEntity<Customer> createCustomerAccount(@RequestBody CustomerDTO customer){
        logger.info("createCustomerAccount - Enter: ");
        Customer customerEntity;
        try{
            customerEntity = customer.getCustomerFromDTO();
            customerService.createCustomer(customerEntity);
        }catch (DataServiceException dataServiceException){
            return responseStatusBasedOnException(dataServiceException);
        }

        logger.info("createCustomerAccount - Leave: ");
        return ResponseEntity.ok(customerEntity);

    }
    @PostMapping("/customers/{customerId}")
    public ResponseEntity<Customer>createOrderForRegisteredCustomer(@PathVariable(name = "customerId") Long customerId, @RequestBody Orders order){
        logger.info("createOrderForRegisteredCustomer - Enter: ");
        Customer customerInDb;
        try {
            customerInDb = customerService.getCustomerById(customerId);
            dataService.createOrder(order);
            customerInDb.addOrderToCustomer(order);
            logger.info("createOrderForRegisteredCustomer - Leave: ");
            customerService.updateCustomer(customerInDb);
            return ResponseEntity.ok(customerInDb);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/customers/a")
    public ResponseEntity<Customer>getACustomer(){
        try {
            Customer customer = new Customer();
            customer.setCustomerFirstName("first");
            customer.setCustomerLastName("last");
            customer.setEmail("email@email.com");
            customer.setAddress("abc 123");
            customerService.createCustomer(customer);
            //product 1
            ProductDTO productDTO = new ProductDTO();
            productDTO.setState(IN_STOCK);
            productDTO.setDescription("8GB V-RAM \n 1500 Cuda cores \n 120 RT cores.");
            productDTO.setProductName("Nvidia 3070TI");
            productDTO.setProductCode("286864");
            productDTO.setPrice(700D);
            //productDTO 2
            ProductDTO productDTO2 = new ProductDTO();
            productDTO2.setState(IN_STOCK);
            productDTO2.setDescription("12 core CPU from AMD.");
            productDTO2.setProductName("AMD Ryzen 5900x");
            productDTO2.setProductCode("AMD54459hjff");
            productDTO2.setPrice(400d);
            //productDTO 3
            ProductDTO productDTO3 = new ProductDTO();
            productDTO3.setState(IN_STOCK);
            productDTO3.setDescription("32GB RAM.");
            productDTO3.setProductName("G.SKILL 3600MHZ CL-14 RIP-JAWS-V");
            productDTO3.setProductCode("gs28686few4");
            productDTO3.setPrice(165D);
            //productDTO 4
            ProductDTO productDTO4 = new ProductDTO();
            productDTO4.setState(IN_STOCK);
            productDTO4.setDescription("X570 chipset Motherboard");
            productDTO4.setProductName("ASUS X570 TUF");
            productDTO4.setProductCode("ASUSTUFYHFHSI55423");
            productDTO4.setPrice(300D);
            //productDTO 5
            ProductDTO productDTO5 = new ProductDTO();
            productDTO5.setState(IN_STOCK);
            productDTO5.setDescription("Corsair Power Supply");
            productDTO5.setProductName("Corsair Psu 1200W Platinum");
            productDTO5.setProductCode("CORUSHSDIGHSD21397951");
            productDTO5.setPrice(225D);
            // persist the products

            Product product = dataService.createProduct(productDTO.productFromDTO());
            Product product2 = dataService.createProduct(productDTO2.productFromDTO());
            Product product3 = dataService.createProduct(productDTO3.productFromDTO());
            Product product4 = dataService.createProduct(productDTO4.productFromDTO());
            Product product5 = dataService.createProduct(productDTO5.productFromDTO());
            // Order Items creation

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setProduct(product);
            orderItem1.setQuantity(2);
            OrderItem orderItem2 = new OrderItem();
            orderItem2.setProduct(product2);
            orderItem2.setQuantity(1);
            OrderItem orderItem3 = new OrderItem();
            orderItem3.setProduct(product3);
            orderItem3.setQuantity(2);
            OrderItem orderItem4 = new OrderItem();
            orderItem4.setProduct(product4);
            orderItem4.setQuantity(1);
            OrderItem orderItem5 = new OrderItem();
            orderItem5.setProduct(product5);
            orderItem5.setQuantity(2);


            Orders order1 = new Orders();
            Set<OrderItem> set = new HashSet<>();
            set.add(orderItem1);
            set.add(orderItem2);
            set.add(orderItem3);

            order1.setOrderItems(set);
            order1.setRecipientAddress("Address 123");
            order1.setRecipientEmail("Email@email.com");
            order1.setRecipientFirstName("FirstName");
            order1.setRecipientLastName("LastName");
            order1.setOrderState("PROCESSING");
            order1.calculateTotal();
            dataService.createOrder(order1);

            Set<Orders> orders = new HashSet<>();
            orders.add(order1);
            customer.setOrders(orders);
            customerService.updateCustomer(customer.getId(), customer);


            Orders order2 = new Orders();
            Set<OrderItem> set2 = new HashSet<>();
            set2.add(orderItem4);
            set2.add(orderItem5);
            order2.setOrderItems(set2);
            order2.setRecipientEmail("Email@email.com");
            order2.setRecipientFirstName("FirstName");
            order2.setRecipientLastName("LastName");
            order2.setOrderState("PROCESSING");
            order2.setRecipientAddress("address 12");
            order2.calculateTotal();
            dataService.createOrder(order2);


            customer.addOrderToCustomer(order2);


            customerService.updateCustomer(customer.getId(), customer);
            return ResponseEntity.ok(customer);
        }catch (DataServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    private ResponseEntity responseStatusBasedOnException(DataServiceException dataServiceException) {
        logger.error("Error message : {} .Is user error: {}. cause of error: {}" , dataServiceException.getErrorMessage(), dataServiceException.isUserError(), dataServiceException.getCauseOfError() );
        if(dataServiceException.isUserError()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @GetMapping("/customers/")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        logger.info("getAllCustomers() - Enter: ");
        List<Customer> customerList = customerService.getAllCustomers();
        logger.info("getAllCustomers() - Leave: ");
        return ResponseEntity.ok(customerList);
    }

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
