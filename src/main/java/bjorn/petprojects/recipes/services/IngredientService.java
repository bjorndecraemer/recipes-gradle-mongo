package bjorn.petprojects.recipes.services;

import bjorn.petprojects.recipes.commands.IngredientCommand;
import reactor.core.publisher.Mono;


public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

    Mono<Void> deleteById(String recipeId, String idToDelete);
}
