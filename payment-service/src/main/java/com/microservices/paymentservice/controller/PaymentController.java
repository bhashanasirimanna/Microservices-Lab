package com.microservices.paymentservice.controller;

import com.microservices.paymentservice.model.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payments", description = "API for processing and managing payments")
public class PaymentController {

    private final Map<Long, Payment> paymentStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public PaymentController() {
        Payment payment1 = new Payment(idGenerator.getAndIncrement(), 1L, 1999.98, "CREDIT_CARD");
        Payment payment2 = new Payment(idGenerator.getAndIncrement(), 2L, 149.95, "PAYPAL");
        paymentStore.put(payment1.getId(), payment1);
        paymentStore.put(payment2.getId(), payment2);
    }

    @Operation(summary = "Get all payments", description = "Returns a list of all payment records")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of payments")
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(new ArrayList<>(paymentStore.values()));
    }

    @Operation(summary = "Process a payment", description = "Processes a new payment for an order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Payment processed successfully", content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid payment data")
    })
    @PostMapping(value = "/process", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Payment> processPayment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Payment to process", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))) @org.springframework.web.bind.annotation.RequestBody Payment payment) {
        long id = idGenerator.getAndIncrement();
        payment.setId(id);
        payment.setStatus("PROCESSED");
        payment.setTransactionId("TXN-" + System.currentTimeMillis());
        paymentStore.put(id, payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @Operation(summary = "Get payment by ID", description = "Returns a single payment record by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment found", content = @Content(schema = @Schema(implementation = Payment.class))),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(
            @Parameter(description = "ID of the payment to retrieve", required = true) @PathVariable Long id) {
        Payment payment = paymentStore.get(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }
}
