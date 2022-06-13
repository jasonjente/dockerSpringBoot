## SkyRoof Spring Application
A client wants to monitor this construction company projects and the tasks that are needed for the orders to be completed. 
1) each product can have a state (not started, in progress, completed). 												
2) Each orders will have the attributes: Id, Title, Description, Creation Date 									
3) Each product will have the attributes: Title, Description, State, Creation Date, Start Date, Completed Date.		
4) A orders can have many tasks, a product can only be assinged to a single orders.									
5) A product cannot be assigned to a different orders upon creation.													
6) The system should be able to calculate a orders state:
- NOT STARTED when all tasks are in NOT STARTED state
- IN PROGRESS when at least one product is in status IN PROGRESS
- COMPLETED when all tasks are in status COMPLETED

7) A orders can be deleted when there are no tasks assigned on it.								
8) A product can be deleted only when it is in state NOT STARTED.													


### Requirements 

1. Implement the product JPA entity and add the 1-* constraint between orders-product										
2. Enchance the REST api to perform all crud operations on orders and product entities.				
3. Implement the business validations and state transition on entities. A user friendly message must be returned.
4. Currently, the system is connected to an on-memory database. Connect with an sql database of your choice 		
(postgres, mysql, oracle etc) and provide the DDL script that creates the schema									
5. Add logging on important functionality (new orders/product, status change etc)										
6. Perform unit tests on the business functionality and integration tests on the REST api.							
 
### Bonus
1. Containerize the application (Docker file jar + sql db)
2. Apply user access security per client with roles (Read, Write). Use OAth2, JWT implementation, Spring security or any other security framework of your choice.











