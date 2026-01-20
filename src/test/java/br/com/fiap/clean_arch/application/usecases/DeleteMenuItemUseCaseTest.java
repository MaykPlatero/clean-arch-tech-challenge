package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private DeleteMenuItemUseCase deleteMenuItemUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteMenuItem() {
        Long menuItemId = 1L;
        MenuItem menuItem = MenuItem.create(menuItemId, 1L, "Pizza", "Delicious", 
            java.math.BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        when(menuItemRepository.findById(menuItemId)).thenReturn(menuItem);

        deleteMenuItemUseCase.execute(menuItemId);

        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, times(1)).deleteById(menuItemId);
    }

    @Test
    void shouldThrowExceptionWhenMenuItemNotFoundOnDelete() {
        when(menuItemRepository.findById(999L)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> deleteMenuItemUseCase.execute(999L));
        
        assertTrue(exception.getMessage().contains("not found"));
        verify(menuItemRepository, times(1)).findById(999L);
        verify(menuItemRepository, never()).deleteById(any());
    }
}
