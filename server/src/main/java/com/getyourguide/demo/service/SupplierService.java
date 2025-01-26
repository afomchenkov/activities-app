package com.getyourguide.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getyourguide.demo.model.Supplier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class SupplierService {

    private List<Supplier> suppliers = null;

    public SupplierService() {
        this.suppliers = loadSuppliers();
    }

    private List<Supplier> loadSuppliers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            var fileInputStream = new ClassPathResource("static/suppliers.json").getInputStream();
            return objectMapper.readValue(fileInputStream, new TypeReference<List<Supplier>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load suppliers data from JSON", e);
        }
    }

    public Supplier getSupplierById(Integer id) {
        return suppliers.stream().filter(supplier -> supplier.id().equals(id)).findFirst().orElse(null);
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
}
