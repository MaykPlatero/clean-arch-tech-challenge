//package br.com.fiap.clean_arch.presentation.controllers;
//
//import br.com.fiap.clean_arch.application.usecases.CreateRestaurantUseCase;
//import br.com.fiap.clean_arch.domain.entities.Restaurant;
//import br.com.fiap.clean_arch.presentation.dto.CreateRestaurantRequest;
//import br.com.fiap.clean_arch.presentation.dto.RestaurantResponse;
//import br.com.fiap.clean_arch.presentation.mappers.RestaurantMapper;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/restaurants")
//@Tag(name = "Restaurants", description = "Restaurant management API")
//public class RestaurantController {
//    private final CreateRestaurantUseCase createRestaurantUseCase;
//    private final RestaurantMapper mapper;
//
//    public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase, RestaurantMapper mapper) {
//        this.createRestaurantUseCase = createRestaurantUseCase;
//        this.mapper = mapper;
//    }
//
//    @PostMapping
//    @Operation(summary = "Create a new restaurant")
//    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request) {
//        Restaurant restaurant = createRestaurantUseCase.execute(
//            request.name(),
//            request.address(),
//            request.cuisineType()
//        );
//        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(restaurant));
//    }
//}
