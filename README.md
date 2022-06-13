Requires:
    - Docker with docker compose
    - A bash terminal 
How to run:
    Execute the bash script startApplication.sh .
    This script will check for the base images and extend them. 
    Then docker-compose will build the containers and then run them.

ReST endpoints: 
    For the Product Entity:
        - GET localhost:8081/application/products/
        - GET localhost:8081/application/products/{productId}
        - GET localhost:8081/application/aproduct/
            returns:
        {
            "state": "IN_STOCK",
            "title": "IN_STOCK",
            "description": "IN_STOCK"
        }  

        - POST localhost:8081/application/products/ 
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }

        - POST localhost:8081/application/products/{productId}
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }
    For the Orders Entity:
        - GET localhost:8081/application/orders/
        - GET localhost:8081/application/orders/{orderId}
        - GET localhost:8081/application/aorder/
            returns:
        {
            "orderId": null,
            "title": "IN_STOCK",
            "description": "IN_STOCK",
            "orderState": "IN_PROGRESS."
        } 

        - POST localhost:8081/application/orders/ 
        {
            "title": "IN_STOCK",
            "description": "IN_STOCK",
            "orderState": "IN_PROGRESS."
        }

        - POST localhost:8081/application/orders/{orderId}
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }

