package bjorn.petprojects.recipes.repositories.reactive;

import bjorn.petprojects.recipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp(){
        unitOfMeasureReactiveRepository.deleteAll().block();

    }

    @Test
    public void testSave(){
        UnitOfMeasure uOM1 = new UnitOfMeasure();
        UnitOfMeasure uOM2 = new UnitOfMeasure();
        uOM1.setDescription("Spoon");
        uOM2.setDescription("Cup");
        unitOfMeasureReactiveRepository.save(uOM1).block();
        unitOfMeasureReactiveRepository.save(uOM2).block();
        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(2L),count);
    }
}