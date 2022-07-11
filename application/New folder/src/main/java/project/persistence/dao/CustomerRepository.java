package project.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.persistence.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
