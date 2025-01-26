package com.getyourguide.demo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.getyourguide.demo.model.Supplier;
import com.getyourguide.demo.service.SupplierService;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SchemaMapping
class SupplierResolver {
    private final SupplierService supplierService;

    public SupplierResolver(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @QueryMapping
    public Supplier supplier(@Argument Integer id) {
        return this.supplierService.getSupplierById(id);
    }

    @QueryMapping
    public List<Supplier> suppliers() {
        return this.supplierService.getSuppliers();
    }
}
