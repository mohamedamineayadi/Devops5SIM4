package tn.esprit.devops_project.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierTest {
    @InjectMocks
    private SupplierServiceImpl SupplierService;

    @Mock
    private SupplierRepository SupplierRepository;


    @Test
    public void testretrieveAllSuppliers() {
        // Create a list of Suppliers for testing
        List<Supplier> Suppliers = new ArrayList<>();
        Suppliers.add(new Supplier(/* Initialize with required data */));
        Suppliers.add(new Supplier(/* Initialize with required data */));

        // Define the behavior of the mock repository
        when(SupplierRepository.findAll()).thenReturn(Suppliers);

        // Call the service method to test
        List<Supplier> result = SupplierService.retrieveAllSuppliers();

        // Add your assertions here to verify the result
    }

    @Test
    public void testaddSupplier() {
        // Create a Supplier object for testing
        Supplier Supplier = new Supplier(/* Initialize with required data */);

        // Define the behavior of the mock repository
        when(SupplierRepository.save(Supplier)).thenReturn(Supplier);

        // Call the service method to test
        Supplier result = SupplierService.addSupplier(Supplier);

        // Add your assertions here to verify the result
    }

    @Test
    public void testupdateSupplier() {
        // Create a Supplier object for testing
        Supplier Supplier = new Supplier(/* Initialize with required data */);

        // Define the behavior of the mock repository
        when(SupplierRepository.save(Supplier)).thenReturn(Supplier);

        // Call the service method to test
        Supplier result = SupplierService.updateSupplier(Supplier);

        // Add your assertions here to verify the result
    }

    @Test
    public void testdeleteSupplier() {
        Long SupplierId = 1L; // Replace with an actual ID
        // Define the behavior of the mock repository (if needed)

        // Call the service method to test
        SupplierService.deleteSupplier(SupplierId);

        // Add your assertions here to verify the result
    }

    @Test
    public void testRetrieveSupplier() {
        Long id = 1L;
        Supplier supplier = new Supplier();
        when(SupplierRepository.findById(id)).thenReturn(Optional.of(supplier));
        Supplier retrievesupp = SupplierService.retrieveSupplier(id);
        assertNotNull(retrievesupp);
    }


}