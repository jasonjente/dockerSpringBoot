package project.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.persistence.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
