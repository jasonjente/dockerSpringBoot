Requires:
    - Docker with docker compose
    - A bash terminal 
    - (possibly) mvn or other building tools
How to run:
    Execute the bash script startApplication.sh .
    
    This script will check for the base images and extend them. 
    
    Then docker-compose will build the containers and then run them.
    
You can add your own database scripts that will run on the first initialization of the database. However, if there is an error during the ddl script execution, on the next startup these ddl's WILL NOT run again.

This Application can be a small template for Springboot applications in combination with Postgres.
Just change the jar that will be uploaded as well as the ddl and the deployment will be ready in 0 time.
ReST endpoints: 

    For the Product Entity:
        - GET localhost:8081/application/api/v1/rest/products/
        
        - GET localhost:8081/application/api/v1/rest/products/{productId}
        
        - GET localhost:8081/application/api/v1/rest/aproduct/
        
            returns:
        {
            "state": "IN_STOCK",
            "title": "IN_STOCK",
            "description": "IN_STOCK"
        }  

        - POST localhost:8081/application/api/v1/rest/products/ 
        
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }

        - POST localhost:8081/application/api/v1/rest/products/{productId}
        
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }
        
        
    For the Orders Entity:
    
        - GET localhost:8081/application/api/v1/rest/orders/
        
        - GET localhost:8081/application/api/v1/rest/orders/{orderId}
        
        - GET localhost:8081/application/api/v1/rest/aorder/
        
            returns:
        {
            "orderId": null,
            "title": "IN_STOCK",
            "description": "IN_STOCK",
            "orderState": "IN_PROGRESS."
        } 

        - POST localhost:8081/application/api/v1/rest/orders/ 
        
        {
            "title": "IN_STOCK",
            "description": "IN_STOCK",
            "orderState": "IN_PROGRESS."
        }

        - POST localhost:8081/application/api/v1/rest/orders/{orderId}
        
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }

