package project.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.persistence.model.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {

}
