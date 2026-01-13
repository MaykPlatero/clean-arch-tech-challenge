package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.CreateMenuItemUseCase;
import br.com.fiap.clean_arch.application.usecases.FindMenuItemsByRestaurantUseCase;
import br.com.fiap.clean_arch.application.usecases.FindMenuItemUseCase;
import br.com.fiap.clean_arch.application.usecases.UpdateMenuItemUseCase;
import br.com.fiap.clean_arch.application.usecases.DeleteMenuItemUseCase;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.presentation.dto.request.CreateMenuItemRequest;
import br.com.fiap.clean_arch.presentation.dto.response.MenuItemResponse;
import br.com.fiap.clean_arch.presentation.mappers.MenuItemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@Tag(name = "Menu Items", description = "Menu item management API")
public class MenuItemController {
    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final FindMenuItemsByRestaurantUseCase findMenuItemsByRestaurantUseCase;
    private final FindMenuItemUseCase findMenuItemUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;
    private final DeleteMenuItemUseCase deleteMenuItemUseCase;

    public MenuItemController(CreateMenuItemUseCase createMenuItemUseCase, 
                             FindMenuItemsByRestaurantUseCase findMenuItemsByRestaurantUseCase,
                             FindMenuItemUseCase findMenuItemUseCase,
                             UpdateMenuItemUseCase updateMenuItemUseCase,
                             DeleteMenuItemUseCase deleteMenuItemUseCase) {
        this.createMenuItemUseCase = createMenuItemUseCase;
        this.findMenuItemsByRestaurantUseCase = findMenuItemsByRestaurantUseCase;
        this.findMenuItemUseCase = findMenuItemUseCase;
        this.updateMenuItemUseCase = updateMenuItemUseCase;
        this.deleteMenuItemUseCase = deleteMenuItemUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new menu item")
    public ResponseEntity<MenuItemResponse> create(@Valid @RequestBody CreateMenuItemRequest request) {
        MenuItem menuItem = createMenuItemUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(MenuItemMapper.toResponse(menuItem));
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get all menu items from a restaurant")
    public ResponseEntity<List<MenuItemResponse>> getByRestaurant(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = findMenuItemsByRestaurantUseCase.execute(restaurantId);
        List<MenuItemResponse> responses = menuItems.stream()
                .map(MenuItemMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get menu item by ID")
    public ResponseEntity<MenuItemResponse> getById(@PathVariable Long id) {
        MenuItem menuItem = findMenuItemUseCase.execute(id);
        return ResponseEntity.ok(MenuItemMapper.toResponse(menuItem));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update menu item")
    public ResponseEntity<MenuItemResponse> update(@PathVariable Long id, @Valid @RequestBody CreateMenuItemRequest request) {
        MenuItem menuItem = updateMenuItemUseCase.execute(id, request);
        return ResponseEntity.ok(MenuItemMapper.toResponse(menuItem));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete menu item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteMenuItemUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
