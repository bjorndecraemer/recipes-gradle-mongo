package bjorn.petprojects.recipes.repositories.reactive;

import bjorn.petprojects.recipes.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe,String> {
}
