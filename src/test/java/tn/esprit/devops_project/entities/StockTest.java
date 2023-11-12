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
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StockTest {
    private StockRepository stockRepository = mock(StockRepository.class);

    // Create an instance of the service with the mock repository
    private StockServiceImpl stockService = new StockServiceImpl(stockRepository);

    @Test
    public void testAddStock() {
        // Arrange
        Stock stockToAdd = new Stock(); // create a stock instance as needed

        // Mock repository behavior
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        // Act
        Stock addedStock = stockService.addStock(stockToAdd);

        // Assert
        assertNotNull(addedStock);
        assertEquals(stockToAdd, addedStock);
        verify(stockRepository, times(1)).save(stockToAdd); // Verify that save was called once with the correct parameter
    }

    @Test
    public void testRetrieveStock() {
        // Arrange
        Long stockId = 1L; // Replace with an actual ID
        Stock existingStock = new Stock(); // create an existing stock instance

        // Mock repository behavior
        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.of(existingStock));

        // Act
        Stock retrievedStock = stockService.retrieveStock(stockId);

        // Assert
        assertNotNull(retrievedStock);
        assertEquals(existingStock, retrievedStock);
        verify(stockRepository, times(1)).findById(stockId); // Verify that findById was called once with the correct parameter
    }

    @Test
    public void testRetrieveAllStock() {
        // Arrange
        List<Stock> stockList = Arrays.asList(new Stock(), new Stock(), new Stock()); // create a list of stocks

        // Mock repository behavior
        when(stockRepository.findAll()).thenReturn(stockList);

        // Act
        List<Stock> retrievedStockList = stockService.retrieveAllStock();

        // Assert
        assertNotNull(retrievedStockList);
        assertEquals(stockList.size(), retrievedStockList.size());
        assertTrue(retrievedStockList.containsAll(stockList));
        verify(stockRepository, times(1)).findAll(); // Verify that findAll was called once
    }
}