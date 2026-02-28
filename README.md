# Microservices-Lab
CTSE lab 05

This repository contains the implementation of a microservices architecture including an API Gateway, Item Service, Order Service, and Payment Service.

## API Test Evidence

The following section demonstrates the successful testing of the various microservices endpoints using Postman.

### 1. Get All Items
**Endpoint:** `GET /items`  
**Description:** Retrieves a list of all items available in the catalog.  
**Status:** `200 OK`  

![Get All Items](./images/1-get-all-items.png)

### 2. Get Item By ID
**Endpoint:** `GET /items/1`  
**Description:** Retrieves the details of a specific item by its ID.  
**Status:** `200 OK`  

![Get Item By ID](./images/2-get-item-by-id.png)

### 3. Create Item
**Endpoint:** `POST /items`  
**Description:** Creates a new item in the catalog.  
**Status:** `201 Created`  

**Request Body:**
```json
{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "quantity": 50
}
```

![Create Item](./images/3-create-item.png)

### 4. Get All Orders
**Endpoint:** `GET /orders`  
**Description:** Retrieves a list of all orders.  
**Status:** `200 OK`  

![Get All Orders](./images/4-get-all-orders.png)

### 5. Get Order By ID
**Endpoint:** `GET /orders/1`  
**Description:** Retrieves the details of a specific order by its ID.  
**Status:** `200 OK`  

![Get Order By ID](./images/5-get-order-by-id.png)
