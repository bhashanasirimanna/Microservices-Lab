# Microservices-Lab
CTSE lab 05

This repository contains the implementation of a microservices architecture including an API Gateway, Item Service, Order Service, and Payment Service.

## API Test Evidence

The following section demonstrates the successful testing of the various microservices endpoints using Postman.

### 1. Get All Items
**Endpoint:** `GET /items`  
**Description:** Retrieves a list of all items available in the catalog.  
**Status:** `200 OK`  

<img width="1485" height="879" alt="Screenshot 2026-02-28 173543" src="https://github.com/user-attachments/assets/e09e95c8-865e-4c1f-a561-b6c8c3b780ae" />

### 2. Get Item By ID
**Endpoint:** `GET /items/1`  
**Description:** Retrieves the details of a specific item by its ID.  
**Status:** `200 OK`  

<img width="1482" height="921" alt="Screenshot 2026-02-28 173605" src="https://github.com/user-attachments/assets/b640a53f-440f-4741-a10a-43128b2ca87e" />

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

<img width="1489" height="688" alt="Screenshot 2026-02-28 173617" src="https://github.com/user-attachments/assets/26a1b8eb-5e7a-4ff1-94c4-0c3496f0eb8a" />


### 4. Get All Orders
**Endpoint:** `GET /orders`  
**Description:** Retrieves a list of all orders.  
**Status:** `200 OK`  

<img width="1494" height="867" alt="Screenshot 2026-02-28 173626" src="https://github.com/user-attachments/assets/b9b69e61-5312-4048-bbf3-4811262d1f63" />


### 5. Get Order By ID
**Endpoint:** `GET /orders/1`  
**Description:** Retrieves the details of a specific order by its ID.  
**Status:** `200 OK`  

<img width="1492" height="726" alt="Screenshot 2026-02-28 173636" src="https://github.com/user-attachments/assets/d8f461d4-03d3-4907-9daf-5c30a0067134" />

### 6. Create Order
**Endpoint:** `POST /orders`  
**Description:** Creates a new order.  
**Status:** `201 Created`  

**Request Body:**
```json
{
  "itemId": 1,
  "quantity": 4,
  "totalPrice": 1999.98,
  "customerName": "Alice Johnson"
}
```
<img width="1496" height="803" alt="Screenshot 2026-02-28 173648" src="https://github.com/user-attachments/assets/b6f73c29-5023-4b09-8f06-59156bfd3b6b" />

### 7. Get All Payments
**Endpoint:** `GET /payments`  
**Description:** Retrieves a list of all Payments.  
**Status:** `200 OK`  


<img width="1495" height="865" alt="Screenshot 2026-02-28 173700" src="https://github.com/user-attachments/assets/dbdc9fad-6a29-4898-a2f6-f523ae1bc0c9" />


### 5. Get Payment By ID
**Endpoint:** `GET /payments/1`  
**Description:** Retrieves the details of a specific Payment by its ID.  
**Status:** `200 OK`  

<img width="1495" height="687" alt="Screenshot 2026-02-28 173710" src="https://github.com/user-attachments/assets/a0e4b750-b70e-45fb-9597-8397928ed274" />

### 3. Create Payment
**Endpoint:** `POST /Payments`  
**Description:** Creates a new Payment.  
**Status:** `201 Created`  

**Request Body:**
```json
{
  "orderId": 1,
  "amount": 1999.98,
  "paymentMethod": "CREDIT_CARD"
}
```

<img width="1488" height="683" alt="Screenshot 2026-02-28 173721" src="https://github.com/user-attachments/assets/79a344b2-3211-40ce-b16a-96693dd0f607" />

