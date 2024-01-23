package com.example.antrasdarbas.controllers;

import com.example.antrasdarbas.repos.WarehouseRep;
import com.example.antrasdarbas.exceptions.WarehouseNotFound;
import com.example.antrasdarbas.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WarehouseRest {

    @Autowired
    private WarehouseRep warehouseRepository;

    // Get all warehouses
    @GetMapping(value = "/getAllWarehouses")
    public @ResponseBody Iterable<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Add a new warehouse
    @PostMapping(value = "/addWarehouse")
    public @ResponseBody Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseRepository.saveAndFlush(warehouse);
    }

    // Update a warehouse by ID
    @PutMapping(value = "/updateWarehouseById/{id}")
    public @ResponseBody Warehouse updateWarehouse(@PathVariable int id, @RequestBody Warehouse warehouseDetails) {
        Warehouse warehouseToUpdate = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFound(id));

        warehouseToUpdate.setTitle(warehouseDetails.getTitle());
        warehouseToUpdate.setAddress(warehouseDetails.getAddress());

        return warehouseRepository.saveAndFlush(warehouseToUpdate);
    }

    // Delete a warehouse by ID
    @DeleteMapping(value = "/deleteWarehouseById/{id}")
    public @ResponseBody void deleteWarehouse(@PathVariable int id) {
        warehouseRepository.deleteById(id);
    }

    // Get a single warehouse by ID
    @GetMapping(value = "/getWarehouseById/{id}")
    public EntityModel<Warehouse> one(@PathVariable int id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFound(id));
        return EntityModel.of(warehouse,
                linkTo(methodOn(WarehouseRest.class).one(id)).withSelfRel(),
                linkTo(methodOn(WarehouseRest.class).getAllWarehouses()).withRel("warehouses"));
    }
}

