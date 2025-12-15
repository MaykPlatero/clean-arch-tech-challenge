//package br.com.fiap.clean_arch.application.usecases;
//
//import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
//import br.com.fiap.clean_arch.domain.entities.Restaurant;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CreateRestaurantUseCase {
//    private final RestaurantRepository repository;
//
//    public CreateRestaurantUseCase(RestaurantRepository repository) {
//        this.repository = repository;
//    }
//
//    public Restaurant execute(String name, String address, String cuisineType) {
//        Restaurant restaurant = new Restaurant(name, address, cuisineType);
//        return repository.save(restaurant);
//    }
//}
