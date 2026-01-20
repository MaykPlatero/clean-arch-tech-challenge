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
import static org.mockito.Mockito.*;

class FindMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private FindMenuItemUseCase findMenuItemUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindMenuItemById() {
        MenuItem menuItem = MenuItem.create(1L, 1L, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        when(menuItemRepository.findById(1L)).thenReturn(menuItem);

        MenuItem result = findMenuItemUseCase.execute(1L);

        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(menuItemRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenMenuItemNotFound() {
        when(menuItemRepository.findById(999L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> findMenuItemUseCase.execute(999L));
        
        assertTrue(exception.getMessage().contains("not found"));
        verify(menuItemRepository, times(1)).findById(999L);
    }
}
