package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.MenuItemEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.MenuItemJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuItemRepositoryAdapterTest {

    @Mock
    private MenuItemJpaRepository menuItemJpaRepository;

    @InjectMocks
    private MenuItemRepositoryAdapter menuItemRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindMenuItemById() {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(1L);
        entity.setRestaurantId(1L);
        entity.setName("Pizza");
        entity.setDescription("Delicious");
        entity.setPrice(BigDecimal.valueOf(30.0));
        entity.setDeliveryItem(true);
        entity.setPhotoUrl("http://photo.jpg");
        entity.setLastUpdate(ZonedDateTime.now());

        when(menuItemJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        MenuItem result = menuItemRepositoryAdapter.findById(1L);

        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(menuItemJpaRepository, times(1)).findById(1L);
    }

    @Test
    void shouldFindMenuItemsByRestaurantId() {
        MenuItemEntity entity1 = new MenuItemEntity();
        entity1.setId(1L);
        entity1.setRestaurantId(1L);
        entity1.setName("Pizza");
        entity1.setDescription("Delicious");
        entity1.setPrice(BigDecimal.valueOf(30.0));
        entity1.setDeliveryItem(true);
        entity1.setPhotoUrl("http://photo1.jpg");
        entity1.setLastUpdate(ZonedDateTime.now());

        when(menuItemJpaRepository.findByRestaurantId(1L)).thenReturn(Arrays.asList(entity1));

        List<MenuItem> result = menuItemRepositoryAdapter.findByRestaurantId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(menuItemJpaRepository, times(1)).findByRestaurantId(1L);
    }

    @Test
    void shouldDeleteMenuItemById() {
        menuItemRepositoryAdapter.deleteById(1L);

        verify(menuItemJpaRepository, times(1)).deleteById(1L);
    }
}
