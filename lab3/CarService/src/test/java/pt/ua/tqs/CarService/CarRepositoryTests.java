package pt.ua.tqs.CarService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.tqs.CarService.DAO.Car;
import pt.ua.tqs.CarService.DAO.CarRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataJpaTest limits the test scope to the data access context (no web environment loaded, for example)
 * tries to autoconfigure the database, if possible (e.g.: in memory db)
 */
@DataJpaTest
class CarRepositoryTests {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenfindByID_thenReturnCar() {
        // arrange a new employee and insert into db
        Car volvo = new Car("volvo", "850t5");
        entityManager.persistAndFlush(volvo); //ensure data is persisted at this point

        // test the query method of interest
        Car found = carRepository.findByCardId(volvo.getCardId());
        assertThat( found ).isEqualTo(volvo);
    }


    @Test
    void whenGetAllCars_thenReturnAll() {
        // arrange a new employee and insert into db
        Car volvo = new Car("volvo", "850t5");
        Car bmw = new Car("bmw", "e30");
        Car mercedes = new Car("mercedes", "lsk");

        List<Car> allCars = Arrays.asList(volvo,bmw, mercedes);

        entityManager.persistAndFlush(volvo); //ensure data is persisted at this point
        entityManager.persistAndFlush(bmw); //ensure data is persisted at this point
        entityManager.persistAndFlush(mercedes); //ensure data is persisted at this point

        List<Car> found = carRepository.findAll();
        assertThat( found ).isEqualTo(allCars);



    }



}