package project.service;

import project.exception.DataServiceException;
import project.persistence.model.OrderItem;
import project.persistence.model.Orders;
import project.persistence.model.Product;

import java.util.List;

public interface DataService {

    List<Product> getAllProducts() throws DataServiceException;

    Product getByProductId(Long productId) throws DataServiceException;

    void deleteProductById(Long productId) throws DataServiceException;

    Product createProduct(Product product) throws DataServiceException;

    Product updateProduct(Long productID, Product newProduct) throws DataServiceException;

    Orders updateOrder(Long orderID, Orders newOrders) throws DataServiceException;

    List<Orders> getAllOrders() throws DataServiceException;

    Orders getByOrderId(Long orderId) throws DataServiceException;

    void deleteOrderById(Long orderId) throws DataServiceException;

    Orders createOrder(Orders orders) throws DataServiceException;

    OrderItem createOrderItem(OrderItem orderItem) throws DataServiceException;

    OrderItem getOrderItemById(Long id) throws DataServiceException;

    List<OrderItem> getAllOrderItemsForOrderId(Long orderId) throws DataServiceException;

    OrderItem updateOrderItem(Long itemId, OrderItem newOrderItem) throws DataServiceException;
}
