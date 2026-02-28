package com.microservices.orderservice.controller;

import com.microservices.orderservice.model.Order;
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
@RequestMapping("/orders")
@Tag(name = "Orders", description = "API for managing customer orders")
public class OrderController {

    private final Map<Long, Order> orderStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public OrderController() {
        Order order1 = new Order(idGenerator.getAndIncrement(), 1L, 2, 1999.98, "Alice Johnson");
        Order order2 = new Order(idGenerator.getAndIncrement(), 2L, 5, 149.95, "Bob Smith");
        orderStore.put(order1.getId(), order1);
        orderStore.put(order2.getId(), order2);
    }

    @Operation(summary = "Get all orders", description = "Returns a list of all customer orders")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders")
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(new ArrayList<>(orderStore.values()));
    }

    @Operation(summary = "Create a new order", description = "Places a new customer order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order created successfully", content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> createOrder(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order to create", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))) @org.springframework.web.bind.annotation.RequestBody Order order) {
        long id = idGenerator.getAndIncrement();
        order.setId(id);
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PENDING");
        }
        orderStore.put(id, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Operation(summary = "Get order by ID", description = "Returns a single order by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order found", content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @Parameter(description = "ID of the order to retrieve", required = true) @PathVariable Long id) {
        Order order = orderStore.get(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }
}
