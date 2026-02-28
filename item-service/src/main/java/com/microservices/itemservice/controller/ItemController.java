package com.microservices.itemservice.controller;

import com.microservices.itemservice.model.Item;
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
@RequestMapping("/items")
@Tag(name = "Items", description = "API for managing items in the catalog")
public class ItemController {

    private final Map<Long, Item> itemStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ItemController() {
        Item item1 = new Item(idGenerator.getAndIncrement(), "Laptop", "High-performance laptop", 999.99, 50);
        Item item2 = new Item(idGenerator.getAndIncrement(), "Wireless Mouse", "Ergonomic wireless mouse", 29.99, 200);
        Item item3 = new Item(idGenerator.getAndIncrement(), "Mechanical Keyboard", "RGB mechanical keyboard", 79.99,
                100);
        itemStore.put(item1.getId(), item1);
        itemStore.put(item2.getId(), item2);
        itemStore.put(item3.getId(), item3);
    }

    @Operation(summary = "Get all items", description = "Returns a list of all available items in the catalog")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of items")
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(new ArrayList<>(itemStore.values()));
    }

    @Operation(summary = "Create a new item", description = "Adds a new item to the catalog")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Item created successfully", content = @Content(schema = @Schema(implementation = Item.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Item> createItem(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Item to create", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))) @org.springframework.web.bind.annotation.RequestBody Item item) {
        long id = idGenerator.getAndIncrement();
        item.setId(id);
        itemStore.put(id, item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @Operation(summary = "Get item by ID", description = "Returns a single item by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item found", content = @Content(schema = @Schema(implementation = Item.class))),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(
            @Parameter(description = "ID of the item to retrieve", required = true) @PathVariable Long id) {
        Item item = itemStore.get(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }
}
