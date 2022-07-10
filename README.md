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
            "orderId": 1,
            "customerFirstName": "FirstName",
            "customerLastName": "LastName",
            "customerAddress": "Address 123",
            "customerEmail": "Email@email.com",
            "orderItems": [
            {
                "id": 2,
                "quantity": 2,
                "order": null,
                "product": {
                    "productId": 1,
                    "state": "IN_STOCK",
                    "productName": "Nvidia 3070TI",
                    "description": "8GB V-RAM \n 1500 Cuda cores \n 120 RT cores.",
                    "productCode": "286864"
            }
        }
    ],
    "orderState": "IN_PROGRESS"
}

        - POST localhost:8081/application/api/v1/rest/orders/ 
        
        {
            "recipientFirstName": "FirstName",
            "recipientLastName": "LastName",
            "recipientAddress": "Address 123",
            "recipientEmail": "Email@email.com",
            "orderItems": [
        {
            "quantity": 2,
            "product": {
                "productId": 1,
                "state": "IN_STOCK",
                "productName": "Nvidia 3070TI",
                "description": "8GB V-RAM \n 1500 Cuda cores \n 120 RT cores.",
                "productCode": "286864",
                "price": 700.0
            }
        },
        {
            "quantity": 1,
            "product": {
                "productId": 2,
                "state": "IN_STOCK",
                "productName": "AMD RX6800XT",
                "description": "16GB V-RAM.",
                "productCode": "AMD54459hjff",
                "price": 800.0
            }
        }
    ],
    "orderState": "PROCESSING"
}

        - POST localhost:8081/application/api/v1/rest/orders/{orderId}
        
        {
            "state": "IN_STOCK",
            "title": "A TITLE",
            "description": "A DESCRIPTION"
        }


For the customer entity:
    
    get localhost:8081/application/api/v1/rest/customers/a
    
    {
    
    "id": 34,
    "customerFirstName": "first",
    "customerLastName": "last",
    "email": "email@email.com",
    "address": "abc 123",
    "orders": [
        {
            "orderId": 8,
            "customerFirstName": "FirstName",
            "customerLastName": "LastName",
            "customerAddress": "Address 123",
            "customerEmail": "Email@email.com",
            "orderItems": [
                {
                    "id": 43,
                    "quantity": 2,
                    "product": {
                        "productId": 39,
                        "state": "IN_STOCK",
                        "productName": "Corsair Psu 1200W Platinum",
                        "description": "Corsair Power Supply",
                        "productCode": "CORUSHSDIGHSD21397951",
                        "price": 225.0
                    }
                },
                {
                    "id": 44,
                    "quantity": 1,
                    "product": {
                        "productId": 38,
                        "state": "IN_STOCK",
                        "productName": "ASUS X570 TUF",
                        "description": "X570 chipset Motherboard",
                        "productCode": "ASUSTUFYHFHSI55423",
                        "price": 300.0
                    }
                }
            ],
            "orderState": "PROCESSING",
            "total": 750.0
        },
        {
            "orderId": 7,
            "customerFirstName": "FirstName",
            "customerLastName": "LastName",
            "customerAddress": "Address 123",
            "customerEmail": "Email@email.com",
            "orderItems": [
                {
                    "id": 40,
                    "quantity": 2,
                    "product": {
                        "productId": 37,
                        "state": "IN_STOCK",
                        "productName": "G.SKILL 3600MHZ CL-14 RIP-JAWS-V",
                        "description": "32GB RAM.",
                        "productCode": "gs28686few4",
                        "price": 165.0
                    }
                },
                {
                    "id": 41,
                    "quantity": 1,
                    "product": {
                        "productId": 36,
                        "state": "IN_STOCK",
                        "productName": "AMD Ryzen 5900x",
                        "description": "12 core CPU from AMD.",
                        "productCode": "AMD54459hjff",
                        "price": 400.0
                    }
                },
                {
                    "id": 42,
                    "quantity": 2,
                    "product": {
                        "productId": 35,
                        "state": "IN_STOCK",
                        "productName": "Nvidia 3070TI",
                        "description": "8GB V-RAM \n 1500 Cuda cores \n 120 RT cores.",
                        "productCode": "286864",
                        "price": 700.0
                    }
                }
            ],
            "orderState": "PROCESSING",
            "total": 2130.0
        }
    ]
}
