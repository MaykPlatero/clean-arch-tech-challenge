package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.CreateRestaurantUseCase;
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

    public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new restaurant")
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request) {
        // Map DTO to domain
        Set<OpeningHours> openingHours = OpeningHoursMapper.toDomainEntitySet(request.openingHours());

        Restaurant restaurant = createRestaurantUseCase.execute(
            request.name(),
            request.address(),
            request.cuisineType(),
            new ArrayList<>(request.userIds()),
            openingHours
        );

        // Map domain to DTO and return response
        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantMapper.toResponse(restaurant));
    }
}
