package project.service;

import project.persistence.model.OrderItem;
import project.persistence.model.Orders;
import project.persistence.model.Product;

import java.util.List;

public interface DataService {

    List<Product> getAllProducts();

    Product getByProductId(Long productId);

    void deleteProductById(Long productId);

    Product createProduct(Product product);

    Product updateProduct(Long productID, Product newProduct);

    Orders updateOrder(Long orderID, Orders newOrders);

    List<Orders> getAllOrders();

    Orders getByOrderId(Long orderId);

    void deleteOrderById(Long orderId);

    Orders createOrder(Orders orders);

    OrderItem createOrderItem(OrderItem orderItem);

    OrderItem getOrderItemById(Long id);

    List<OrderItem> getAllOrderItemsForOrderId(Long orderId);

    OrderItem updateOrderItem(Long itemId, OrderItem newOrderItem);
}
