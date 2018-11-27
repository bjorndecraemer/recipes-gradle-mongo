package bjorn.petprojects.recipes.repositories.reactive;

import bjorn.petprojects.recipes.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave(){
        Category cat1 = new Category();
        cat1.setDescription("cat1desc");
        Category cat2 = new Category();
        cat2.setDescription("cat2desc");
        Category cat3 = new Category();
        cat3.setDescription("cat3desc");
        categoryReactiveRepository.save(cat1).block();
        categoryReactiveRepository.save(cat2).block();
        categoryReactiveRepository.save(cat3).block();
        Long count = categoryReactiveRepository.count().block();
        assertEquals(Long.valueOf(3L),count);
    }
}