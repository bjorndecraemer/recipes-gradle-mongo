package bjorn.petprojects.recipes.controllers;

import bjorn.petprojects.recipes.domain.Recipe;
import bjorn.petprojects.recipes.services.ImageService;
import bjorn.petprojects.recipes.services.IngredientService;
import bjorn.petprojects.recipes.services.RecipeService;
import bjorn.petprojects.recipes.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import(IndexController.class)
@WebAppConfiguration
public class IndexControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ApplicationContext applicationContext;

    @MockBean
    RecipeService recipeService;

    @MockBean
    ImageService imageService;

    @MockBean
    IngredientService ingredientService;

    @MockBean
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    Model model;

    @Autowired
    IndexController controller;


    @Before
    public void setUp() throws Exception {
        //webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
        //webTestClient = WebTestClient.bindToController(new IndexController(recipeService)).configureClient().baseUrl("/").build();
        WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void testMockMVC() throws Exception {

        when(recipeService.getRecipes()).thenReturn(Flux.empty());

        webTestClient.get().uri("/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
                //.expectBody(Void.class);
    }

    @Test
    public void getIndexPage() throws Exception {

        //given
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId("1");

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(Flux.fromIterable(recipes));

        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

        //when
        String viewName = controller.getIndexPage(model);


        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        List<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}