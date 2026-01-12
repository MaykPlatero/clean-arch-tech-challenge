package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.CreateMenuItemUseCase;
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

@RestController
@RequestMapping("/api/menu-items")
@Tag(name = "Menu Items", description = "Menu item management API")
public class MenuItemController {
    private final CreateMenuItemUseCase createMenuItemUseCase;

    public MenuItemController(CreateMenuItemUseCase createMenuItemUseCase) {
        this.createMenuItemUseCase = createMenuItemUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new menu item")
    public ResponseEntity<MenuItemResponse> create(@Valid @RequestBody CreateMenuItemRequest request) {
        MenuItem menuItem = createMenuItemUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(MenuItemMapper.toResponse(menuItem));
    }
}

