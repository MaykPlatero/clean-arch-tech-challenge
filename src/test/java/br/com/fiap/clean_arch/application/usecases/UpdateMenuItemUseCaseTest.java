package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private UpdateMenuItemUseCase updateMenuItemUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateMenuItem() {
        MenuItem existingItem = MenuItem.create(1L, 1L, "Pizza", "Old description", 
            BigDecimal.valueOf(30.0), true, "http://old.jpg");
        MenuItem updatedItem = MenuItem.create(1L, 1L, "Pizza Updated", "New description", 
            BigDecimal.valueOf(35.0), false, "http://new.jpg");

        when(menuItemRepository.findById(1L)).thenReturn(existingItem);
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(updatedItem);

        MenuItem result = updateMenuItemUseCase.execute(1L, 1L, "Pizza Updated", 
            "New description", BigDecimal.valueOf(35.0), false, "http://new.jpg");

        assertNotNull(result);
        assertEquals("Pizza Updated", result.getName());
        verify(menuItemRepository, times(1)).findById(1L);
        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
    }

    @Test
    void shouldThrowExceptionWhenMenuItemNotFoundOnUpdate() {
        when(menuItemRepository.findById(999L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> updateMenuItemUseCase.execute(999L, 1L, "Pizza", "Desc", 
                BigDecimal.valueOf(30.0), true, "http://photo.jpg"));
        
        assertTrue(exception.getMessage().contains("not found"));
        verify(menuItemRepository, times(1)).findById(999L);
        verify(menuItemRepository, never()).save(any());
    }
}
