package tn.esprit.devops_project.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperatorTest {
    @InjectMocks
    OperatorServiceImpl OperatorService;

    @Mock
    OperatorRepository OperatorRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testRetrieveAllOperators() {
        when(OperatorRepository.findAll()).thenReturn(Arrays.asList(new Operator(), new Operator()));

        List<Operator> Operators = OperatorService.retrieveAllOperators();

        assertEquals(2, Operators.size());
    }

    @Test
    public void testAddOperator() {
        Operator Operator = new Operator();
        when(OperatorRepository.save(Operator)).thenReturn(Operator);

        Operator addedOperator = OperatorService.addOperator(Operator);

        assertNotNull(addedOperator);
    }

    @Test
    public void testDeleteOperator() {
        Long id = 1L;

        OperatorService.deleteOperator(id);

        verify(OperatorRepository).deleteById(id);
    }

    @Test
    public void testUpdateOperator() {
        Operator Operator = new Operator();
        when(OperatorRepository.save(Operator)).thenReturn(Operator);

        Operator updatedOperator = OperatorService.updateOperator(Operator);

        assertNotNull(updatedOperator);
    }

    @Test
    public void testRetrieveOperator() {
        Long id = 1L;
        Operator Operator = new Operator();
        when(OperatorRepository.findById(id)).thenReturn(Optional.of(Operator));
        Operator retrievedOperator = OperatorService.retrieveOperator(id);
        assertNotNull(retrievedOperator);
    }

}