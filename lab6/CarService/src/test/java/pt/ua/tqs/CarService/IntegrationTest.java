package pt.ua.tqs.CarService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import pt.ua.tqs.CarService.DAO.Car;
import pt.ua.tqs.CarService.DAO.CarDTO;
import pt.ua.tqs.CarService.DAO.CarRepository;
import pt.ua.tqs.CarService.Service.CarManagerService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@TestPropertySource( locations = "application-integrationtest.properties")
class IntegrationTest {

    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;




    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
     void whenValidInput_thenCreateCar() {
        CarDTO volvo = new CarDTO("volvo", "850T5");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/car", volvo, Car.class);

        assertThat(entity.getBody()).isEqualTo(new Car(volvo));

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("volvo");
    }


    @Test
     void whenGetById() {

        CarDTO volvoDTO = new CarDTO("volvo", "850T5");
        CarDTO bmwDTO = new CarDTO("bmw", "e30");

        Car volvo = new Car(volvoDTO.getCardId(),"volvo", "850T5");
        Car bmw = new Car(bmwDTO.getCardId(),"bmw", "e30");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/car", volvoDTO, Car.class);
        ResponseEntity<Car> entity2 = restTemplate.postForEntity("/api/car", bmwDTO, Car.class);

        assertThat(entity.getBody()).isEqualTo(volvo);
        assertThat(entity2.getBody()).isEqualTo(bmw);

        ResponseEntity<Car> getEntity = restTemplate.getForEntity("/api/car/"+volvo.getCardId(), Car.class);
        ResponseEntity<Car> getEntity2 = restTemplate.getForEntity("/api/car/"+bmw.getCardId(), Car.class);

        assertThat(entity.getBody()).isEqualTo(getEntity.getBody());
        assertThat(entity2.getBody()).isEqualTo(getEntity2.getBody());

    }


    @Test
     void whenGetAll() {

        CarDTO volvoDto = new CarDTO("volvo", "850T5");
        CarDTO bmwDto = new CarDTO("bmw", "e30");

        Car volvo = new Car(volvoDto.getCardId(),"volvo", "850T5");
        Car bmw = new Car(bmwDto.getCardId(),"bmw", "e30");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/car", volvoDto, Car.class);
        ResponseEntity<Car> entity2 = restTemplate.postForEntity("/api/car", bmwDto, Car.class);

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/car", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        List<Car> allCars = Arrays.asList(volvo,bmw);


        assertThat(response.getBody()).isEqualTo(allCars);

    }





}

