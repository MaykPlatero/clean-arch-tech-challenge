package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.CreateRestaurantUseCase;
import br.com.fiap.clean_arch.application.usecases.FindRestaurantUseCase;
import br.com.fiap.clean_arch.application.usecases.UpdateRestaurantUseCase;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.presentation.dto.request.CreateRestaurantRequest;
import br.com.fiap.clean_arch.presentation.dto.response.RestaurantResponse;
import br.com.fiap.clean_arch.presentation.mappers.OpeningHoursMapper;
import br.com.fiap.clean_arch.presentation.mappers.RestaurantMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Restaurants", description = "Restaurant management API")
public class RestaurantController {
    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final FindRestaurantUseCase findRestaurantUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;

    public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase,
                               FindRestaurantUseCase findRestaurantUseCase,
                               UpdateRestaurantUseCase updateRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.findRestaurantUseCase = findRestaurantUseCase;
        this.updateRestaurantUseCase = updateRestaurantUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new restaurant")
    public ResponseEntity<RestaurantResponse> createRestaurant(@Valid @RequestBody CreateRestaurantRequest request) {
        Set<OpeningHours> openingHours = OpeningHoursMapper.toDomainEntitySet(request.openingHours());
        Restaurant restaurant = createRestaurantUseCase.execute(
            request.name(),
            request.address(),
            request.cuisineType(),
            new ArrayList<>(request.userIds()),
            openingHours
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantMapper.toResponse(restaurant));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by ID")
    public ResponseEntity<RestaurantResponse> findById(@PathVariable Long id) {
        Restaurant restaurant = findRestaurantUseCase.execute(id);
        return ResponseEntity.ok(RestaurantMapper.toResponse(restaurant));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update restaurant")
    public ResponseEntity<RestaurantResponse> update(@PathVariable Long id, @Valid @RequestBody CreateRestaurantRequest request) {
        Set<OpeningHours> openingHours = OpeningHoursMapper.toDomainEntitySet(request.openingHours());
        Restaurant restaurant = updateRestaurantUseCase.execute(
            id,
            request.name(),
            request.address(),
            request.cuisineType(),
            openingHours
        );
        return ResponseEntity.ok(RestaurantMapper.toResponse(restaurant));
    }
}
