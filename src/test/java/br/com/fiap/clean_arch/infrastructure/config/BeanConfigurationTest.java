package br.com.fiap.clean_arch.infrastructure.config;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.application.usecases.*;
import br.com.fiap.clean_arch.infrastructure.adapters.MenuItemRepositoryAdapter;
import br.com.fiap.clean_arch.infrastructure.adapters.RestaurantRepositoryAdapter;
import br.com.fiap.clean_arch.infrastructure.adapters.UserRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:testdb")
class BeanConfigurationTest {

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
        assertTrue(true);
    }
}
