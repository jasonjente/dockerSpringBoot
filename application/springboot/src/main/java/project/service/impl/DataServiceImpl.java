package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.dao.OrderRepository;
import project.persistence.dao.ProductRepository;
import project.persistence.model.Orders;
import project.persistence.model.Product;
import project.service.DataService;
import project.rest.exception.DataServiceException;

import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {
    final static Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

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

        if(product.getTitle() == null){
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

        Optional<Product> optInDB = productRepository.findById(productID);
        if(optInDB.isPresent()){
            Product inDb = optInDB.get();
            validateProduct(newProduct);
            newProduct.setKey(inDb.getProductId());
            productRepository.save(newProduct);
            logger.info("updateProduct() - Leave ");
            return inDb;
        }else {
            logger.warn("updateProduct() - product didn't exist - Leave ");
            return createProduct(newProduct);
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
        return orderRepository.save(orders);
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
        if(newOrders.getDescription().isEmpty()){
            String errorMessage = "The field description cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(newOrders.getTitle().isEmpty()){
            String errorMessage = "The field title cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            logger.error(errorMessage + " " + causeOfError + ". Is user-error: " + isUserError);
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }
}

