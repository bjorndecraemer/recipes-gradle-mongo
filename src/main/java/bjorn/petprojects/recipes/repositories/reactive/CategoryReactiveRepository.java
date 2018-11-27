package bjorn.petprojects.recipes.repositories.reactive;

import bjorn.petprojects.recipes.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category,String> {
    Mono<Category> findBydescription(String description);
}
