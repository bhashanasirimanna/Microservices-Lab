package com.microservices.paymentservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Represents a payment transaction")
public class Payment {

    @Schema(description = "Unique identifier of the payment", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID of the order being paid for", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;

    @Schema(description = "Payment amount in USD", example = "1999.98", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double amount;

    @Schema(description = "Current status of the payment", example = "PROCESSED", allowableValues = { "PENDING",
            "PROCESSED", "FAILED", "REFUNDED" })
    private String status;

    @Schema(description = "Payment method used", example = "CREDIT_CARD", allowableValues = { "CREDIT_CARD",
            "DEBIT_CARD", "PAYPAL", "BANK_TRANSFER" }, requiredMode = Schema.RequiredMode.REQUIRED)
    private String paymentMethod;

    @Schema(description = "Unique transaction ID assigned on processing", example = "TXN-1709117073000", accessMode = Schema.AccessMode.READ_ONLY)
    private String transactionId;

    @Schema(description = "Timestamp when the payment was processed", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime processedAt;

    public Payment() {
    }

    public Payment(Long id, Long orderId, Double amount, String paymentMethod) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "PROCESSED";
        this.transactionId = "TXN-" + System.currentTimeMillis();
        this.processedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
}
