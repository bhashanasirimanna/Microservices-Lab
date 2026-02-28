package com.microservices.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Represents a customer order")
public class Order {

    @Schema(description = "Unique identifier of the order", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID of the item being ordered", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long itemId;

    @Schema(description = "Quantity of items ordered", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

    @Schema(description = "Total price of the order in USD", example = "1999.98")
    private Double totalPrice;

    @Schema(description = "Current status of the order", example = "PENDING", allowableValues = { "PENDING",
            "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED" })
    private String status;

    @Schema(description = "Name of the customer placing the order", example = "Alice Johnson", requiredMode = Schema.RequiredMode.REQUIRED)
    private String customerName;

    @Schema(description = "Timestamp when the order was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Long id, Long itemId, Integer quantity, Double totalPrice, String customerName) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
