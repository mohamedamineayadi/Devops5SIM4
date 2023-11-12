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
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceTest {
    // Mock repositories
    private InvoiceRepository invoiceRepository = mock(InvoiceRepository.class);
    private OperatorRepository operatorRepository = mock(OperatorRepository.class);
    private InvoiceDetailRepository invoiceDetailRepository = mock(InvoiceDetailRepository.class);
    private SupplierRepository supplierRepository = mock(SupplierRepository.class);

    // Create an instance of the service with the mock repositories
    private InvoiceServiceImpl invoiceService = new InvoiceServiceImpl(
            invoiceRepository,
            operatorRepository,
            invoiceDetailRepository,
            supplierRepository
    );

    @Test
    public void testRetrieveAllInvoices() {
        // Arrange
        List<Invoice> invoiceList = Arrays.asList(new Invoice(), new Invoice(), new Invoice());

        // Mock repository behavior
        when(invoiceRepository.findAll()).thenReturn(invoiceList);

        // Act
        List<Invoice> retrievedInvoices = invoiceService.retrieveAllInvoices();

        // Assert
        assertNotNull(retrievedInvoices);
        assertEquals(invoiceList.size(), retrievedInvoices.size());
        assertTrue(retrievedInvoices.containsAll(invoiceList));
        verify(invoiceRepository, times(1)).findAll(); // Verify that findAll was called once
    }

    @Test
    public void testCancelInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.cancelInvoice(invoiceId);

        // Assert
        assertTrue(invoice.getArchived());
        verify(invoiceRepository, times(1)).save(invoice); // Verify that save was called once with the correct parameter
        verify(invoiceRepository, times(1)).updateInvoice(invoiceId); // Verify that updateInvoice was called once with the correct parameter
    }

    @Test
    public void testRetrieveInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice expectedInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));

        // Act
        Invoice retrievedInvoice = invoiceService.retrieveInvoice(invoiceId);

        // Assert
        assertNotNull(retrievedInvoice);
        assertEquals(expectedInvoice, retrievedInvoice);
        verify(invoiceRepository, times(1)).findById(invoiceId); // Verify that findById was called once with the correct parameter
    }

    @Test
    public void testGetInvoicesBySupplier() {
        // Arrange
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        Set<Invoice> invoices = new HashSet<>(Arrays.asList(new Invoice(), new Invoice()));
        supplier.setInvoices(invoices);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        // Act
        List<Invoice> result = invoiceService.getInvoicesBySupplier(supplierId);

        // Assert
        assertNotNull(result);
        assertEquals(invoices.size(), result.size());
        assertTrue(result.containsAll(invoices));
        verify(supplierRepository, times(1)).findById(supplierId);
    }

    @Test
    public void testAssignOperatorToInvoice() {
        // Arrange
        Long operatorId = 1L;
        Long invoiceId = 2L;
        Operator operator = new Operator();
        Invoice invoice = new Invoice();
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

    }

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedTotalAmount = 100.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedTotalAmount);

        // Act
        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertEquals(expectedTotalAmount, result);
        verify(invoiceRepository, times(1)).getTotalAmountInvoiceBetweenDates(startDate, endDate); // Verify that getTotalAmountInvoiceBetweenDates was called once with the correct parameters
    }

}