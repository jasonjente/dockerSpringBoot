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
        List<Product> products = (List<Product>) productRepository.findAll();
        if(products.isEmpty()){
            String errorMessage = "No products found.";
            String causeOfError = "Either Empty database or miss-configured";
            boolean isUserError = false;

            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        return products;
    }

    @Override
    public Product getByProductId(Long productId) {
        Optional<Product> ret = productRepository.findById(productId);
        if(ret.isPresent()){
            return ret.get();
        }else {
            String errorMessage = "No products found for id :" + productId+".";
            String causeOfError = "Unknown product id.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        Optional<Product> ret = productRepository.findById(productId);
        if(ret.isPresent()){
            productRepository.delete(ret.get());
        }else {
            String errorMessage = "No products found for id :" + productId+".";
            String causeOfError = "Unknown product id.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public Product createProduct(Product product) {
        if(product.getDescription().isEmpty()){
            String errorMessage = "The field description cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(product.getTitle().isEmpty()){
            String errorMessage = "The field title cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(product.getState().isEmpty()||!("IN_STOCK".equals(product.getState())||"OUT_OF_STOCK".equals(product.getState()))){
            String errorMessage = "The product state cannot be empty.\nOnly 2 conditions are allowed;\n->'OUT_OF_STOCK'\n->'IN_STOCK'";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productID, Product newProduct) {
        Optional<Product> optInDB = productRepository.findById(productID);
        if(optInDB.isPresent()){
            Product inDb = optInDB.get();
            if(newProduct.getDescription().isEmpty()){
                String errorMessage = "The field description cannot be empty.";
                String causeOfError = "Empty description.";
                boolean isUserError = true;
                throw new DataServiceException(errorMessage, causeOfError, isUserError);
            }

            if(newProduct.getTitle().isEmpty()){
                String errorMessage = "The field title cannot be empty.";
                String causeOfError = "Empty description.";
                boolean isUserError = true;
                throw new DataServiceException(errorMessage, causeOfError, isUserError);
            }

            newProduct.setState("IN_PROGRESS.");

            return inDb;
        }else {
            return createProduct(newProduct);
        }
    }


    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = (List<Orders>) orderRepository.findAll();
        if(orders.isEmpty()){
            String errorMessage = "No orders found.";
            String causeOfError = "Either Empty database or miss-configured";
            boolean isUserError = false;

            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
        return orders;
    }

    @Override
    public Orders getByOrderId(Long orderId) {
        Optional<Orders> ret = orderRepository.findById(orderId);
        if(ret.isPresent()){
            return ret.get();
        }else {
            String errorMessage = "No orders found for id :" + orderId+".";
            String causeOfError = "Unknown order id.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public void deleteOrderById(Long orderId) {
        Optional<Orders> ret = orderRepository.findById(orderId);
        if(ret.isPresent()){
            orderRepository.delete(ret.get());
        }else {
            String errorMessage = "No orders found for id :" + orderId+".";
            String causeOfError = "Unknown order id.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }
    }

    @Override
    public Orders createOrder(Orders orders) {
        if(orders.getDescription().isEmpty()){
            String errorMessage = "The field description cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        if(orders.getTitle().isEmpty()){
            String errorMessage = "The field title cannot be empty.";
            String causeOfError = "Empty description.";
            boolean isUserError = true;
            throw new DataServiceException(errorMessage, causeOfError, isUserError);
        }

        return orderRepository.save(orders);
    }

    @Override
    public Orders updateOrder(Long orderID, Orders newOrders) {
        Optional<Orders> optInDB = orderRepository.findById(orderID);
        if(optInDB.isPresent()){
            Orders inDb = optInDB.get();
            if(newOrders.getDescription().isEmpty()){
                String errorMessage = "The field description cannot be empty.";
                String causeOfError = "Empty description.";
                boolean isUserError = true;
                throw new DataServiceException(errorMessage, causeOfError, isUserError);
            }

            if(newOrders.getTitle().isEmpty()){
                String errorMessage = "The field title cannot be empty.";
                String causeOfError = "Empty description.";
                boolean isUserError = true;
                throw new DataServiceException(errorMessage, causeOfError, isUserError);
            }

            return inDb;
        }else {
            return createOrder(newOrders);
        }
    }
}

