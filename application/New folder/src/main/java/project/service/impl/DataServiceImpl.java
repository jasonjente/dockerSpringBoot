package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.dao.CustomerRepository;
import project.persistence.dao.OrderItemRepository;
import project.persistence.dao.OrderRepository;
import project.persistence.dao.ProductRepository;
import project.persistence.model.OrderItem;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.service.DataService;
import project.rest.exception.DataServiceException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {
    static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Product> getAllProducts() {
        logger.info("getAllProducts() - Enter");
        List<Product> products = (List<Product>) productRepository.findAll();
        if(products.isEmpty()){
            String errorMessage = "No products found.";
            String causeOfError = "Either Empty database or miss-configured";
            boolean isUserError = false;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        logger.info("getAllProducts() - Leave");
        return products;
    }

    @Override
    public Product getByProductId(Long productId) {
        logger.info("getByProductId() - Enter ,productID: " + productId);
        Optional<Product> ret = productRepository.findById(productId);
        if(ret.isPresent()){

            logger.info("getByProductId() - Leave ");
            return ret.get();
        }else {
            String errorMessage = "No products found for id :" + productId+".";
            String causeOfError = "Unknown product id.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        logger.info("deleteProductById() - Enter ,productID: " + productId);
        Optional<Product> ret = productRepository.findById(productId);
        if(ret.isPresent()){
            logger.info("deleteProductById() - Leave ");
            productRepository.delete(ret.get());
        }else {
            String errorMessage = "No products found for id :" + productId+".";
            String causeOfError = "Unknown product id.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("createProduct() - Enter ");
        validateProduct(product);
        logger.info("createProduct() - Leave ");
        return productRepository.save(product);
    }

    private void validateProduct(Product product) {
        if(product.getState().equals("")||!("IN_STOCK".equals(product.getState())||"OUT_OF_STOCK".equals(product.getState()))){
            String errorMessage = "The product state cannot be empty.\nOnly 2 conditions are allowed;\n->'OUT_OF_STOCK'\n->'IN_STOCK'";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(product.getDescription() == null){
            String errorMessage = "The field description cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(product.getProductName() == null){
            String errorMessage = "The field title cannot be empty.";
            String causeOfError = "Empty title.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public Product updateProduct(Long productID, Product newProduct) {
        logger.info("updateProduct() - Enter ");
        validateProduct(newProduct);
        Optional<Product> productInDB = productRepository.findById(productID);
        if(productInDB.isPresent()){
            Product pr = new Product();
            pr.setPrice(newProduct.getPrice());
            pr.setProductCode(newProduct.getProductCode());
            pr.setState(newProduct.getState());
            pr.setDescription(newProduct.getDescription());
            pr.setProductName(newProduct.getProductName());
            productRepository.save(pr);
            logger.info("updateProduct() - Leave ");
            return pr;
        }else {
            productRepository.save(newProduct);
            return newProduct;
        }
    }


    @Override
    public List<Orders> getAllOrders() {
        logger.info("getAllOrders() - Enter ");
        List<Orders> orders = (List<Orders>) orderRepository.findAll();
        if(orders.isEmpty()){
            String errorMessage = "No orders found.";
            String causeOfError = "Either Empty database or miss-configured";
            boolean isUserError = false;

            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        logger.info("getAllOrders() - Leave ");
        return orders;
    }

    @Override
    public Orders getByOrderId(Long orderId) {
        logger.info("getByOrderId() - Enter ");
        Optional<Orders> ret = orderRepository.findById(orderId);
        if(ret.isPresent()){
            logger.info("getByOrderId() - Leave ");
            return ret.get();
        }else {
            String errorMessage = "No orders found for id :" + orderId+".";
            String causeOfError = "Unknown order id.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public void deleteOrderById(Long orderId) {
        logger.info("deleteOrderById() - Enter ");
        Optional<Orders> ret = orderRepository.findById(orderId);
        if(ret.isPresent()){
            logger.info("deleteOrderById() - Leave ");
            orderRepository.delete(ret.get());
        }else {
            String errorMessage = "No orders found for id :" + orderId+".";
            String causeOfError = "Unknown order id.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public Orders createOrder(Orders orders) {
        logger.info("createOrder() - Enter ");
        validateOrder(orders);
        logger.info("createOrder() - Leave ");
        //todo: temporary for products will use FK in the future for retrieval of items
        for(OrderItem orderItem: orders.getOrderItems()) {
            createProduct(orderItem.getProduct());
            createOrderItem(orderItem);
        }
        return orderRepository.save(orders);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        logger.info("createOrderItem() - Enter ");
        validateOrderItem(orderItem);
        logger.info("createOrderItem() - Leave ");
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        logger.info("getOrderItemById() - Enter ");
        Optional<OrderItem> ret = orderItemRepository.findById(id);
        if(ret.isPresent()){
            logger.info("getOrderItemById() - Leave ");
            return ret.get();
        }else {
            String errorMessage = "No order item found for id :" + id+".";
            String causeOfError = "Unknown order id.";
            boolean isUserError = true;
            logger.error("getOrderItemById() - ERROR :" + errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public List<OrderItem> getAllOrderItemsForOrderId(Long orderId) {
        logger.info("getAllOrderItemsForOrderId() - Enter ");
        Orders orders = getByOrderId(orderId);
        if(orders==null){
            String errorMessage = "No orders found for this id.";
            String causeOfError = "Either Empty database or miss-configured";
            boolean isUserError = false;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        List<OrderItem> orderItems = new ArrayList<>(orders.getOrderItems());
        logger.info("getAllOrderItemsForOrderId() - Leave ");
        return orderItems;
    }

    @Override
    public OrderItem updateOrderItem(Long itemId, OrderItem newOrderItem) {
        logger.info("updateOrderItem() - Enter ");
        Optional<OrderItem> optInDB = orderItemRepository.findById(itemId);
        if(optInDB.isPresent()){
            OrderItem inDb = optInDB.get();
            validateOrderItem(newOrderItem);
            newOrderItem.setId(inDb.getId());
            orderItemRepository.save(newOrderItem);
            logger.info("updateOrderItem() - Leave ");
            return inDb;
        }else {
            String errorMessage = "No order item found for this id.";
            String causeOfError = "Invalid key.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }
    @Override
    public Orders updateOrder(Long orderID, Orders newOrders) {
        logger.info("updateOrder() - Enter ");
        Optional<Orders> optInDB = orderRepository.findById(orderID);
        if(optInDB.isPresent()){
            Orders inDb = optInDB.get();
            validateOrder(newOrders);
            newOrders.setOrderId(inDb.getOrderId());
            orderRepository.save(newOrders);
            logger.info("updateOrder() - Leave ");
            return inDb;
        }else {
            logger.warn("updateOrder() - Order didn't exist - Leave ");
            return createOrder(newOrders);
        }
    }

    private void validateOrder(Orders newOrders) {
        validateCustomerData(newOrders);

        if(newOrders.getOrderItems().isEmpty()){
            String errorMessage = "No order items detected.";
            String causeOfError = "Empty order item set.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    private void validateCustomerData(Orders newOrders) {
        if(newOrders.getRecipientAddress().isEmpty()){
            String errorMessage = "The field address cannot be empty.";
            String causeOfError = "Empty address.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(newOrders.getRecipientEmail().isEmpty()){
            String errorMessage = "The field email cannot be empty.";
            String causeOfError = "Empty email.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }//else{
        //checkIfEmailIsValid(email);
        //}
        if(newOrders.getRecipientFirstName().isEmpty()|| newOrders.getRecipientLastName().isEmpty()){
            String errorMessage = "The first name or last name cannot be empty.";
            String causeOfError = "Empty first name or last name.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    private void validateOrderItem(OrderItem orderItem) {
        if(orderItem == null){
            String errorMessage = "User inserted a Null order item.";
            String causeOfError = "Null order item.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        if(orderItem.getQuantity()<=0){
            String errorMessage = "The quantity cannot be 0 or less.";
            String causeOfError = "Added 0 items.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(orderItem.getProduct()==null){
            String errorMessage = "No product was added.";
            String causeOfError = "Added no products.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }



}

