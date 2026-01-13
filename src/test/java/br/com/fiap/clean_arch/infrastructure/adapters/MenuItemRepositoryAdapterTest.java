package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.MenuItemJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MenuItemRepositoryAdapterTest {

    @Mock
    private MenuItemJpaRepository menuItemJpaRepository;

    private MenuItemRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new MenuItemRepositoryAdapter(menuItemJpaRepository);
    }

    @Test
    void shouldSaveMenuItem() {
        MenuItem menuItem = MenuItem.create(1L, 10L, "Pizza", "Delicious", BigDecimal.valueOf(25.99), true, "http://photo.jpg");
        
        MenuItemEntity savedEntity = new MenuItemEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Pizza");
        savedEntity.setDescription("Delicious");
        savedEntity.setPrice(BigDecimal.valueOf(25.99));
        savedEntity.setDeliveryItem(true);
        savedEntity.setPhotoUrl("http://photo.jpg");
        savedEntity.setRestaurantId(10L);
        savedEntity.setLastUpdate(ZonedDateTime.now());
        
        when(menuItemJpaRepository.save(any(MenuItemEntity.class))).thenReturn(savedEntity);
        
        MenuItem result = adapter.save(menuItem);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Pizza", result.getName());
        verify(menuItemJpaRepository).save(any(MenuItemEntity.class));
    }
}
