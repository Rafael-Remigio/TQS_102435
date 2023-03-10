package pt.ua.tqs.CarService;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import pt.ua.tqs.CarService.DAO.Car;
import pt.ua.tqs.CarService.DAO.CarRepository;
import pt.ua.tqs.CarService.Service.CarManagerService;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;


    Car bmw = new Car("bmw", "e30");
    long bmwId = 100;


    Car volvo = new Car("volvo", "850T5");
    long volvoId = 200;


    Car mercedes = new Car("mercedes", "slk 200");
    long mercedesId = 300;
    long wrongId = 420;

    List<Car> allCars = Arrays.asList(bmw, volvo, mercedes);

    @BeforeEach
    public void setUp() {
        bmw.setCardId(bmwId);

        volvo.setCardId(volvoId);

        mercedes.setCardId(mercedesId);



        Mockito.when(carRepository.findByCardId(bmw.getCardId())).thenReturn(bmw);
        Mockito.when(carRepository.findByCardId(volvo.getCardId())).thenReturn(volvo);
        Mockito.when(carRepository.findByCardId(wrongId)).thenReturn(null);
        Mockito.when(carRepository.findByCardId(mercedes.getCardId())).thenReturn(mercedes);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    


    }

    @Test
    public void whenGetCarDetails(){
        
        assertThat(carService.getCarDetails(bmwId)).isEqualTo(bmw);
        assertThat(carService.getCarDetails(mercedesId)).isEqualTo(mercedes);


        assertThat(carService.getCarDetails(wrongId)).isEqualTo(null);

    }

    @Test
    public void whenGetAllCars(){

        assertThat(carService.getAllCars()).isEqualTo(allCars);

    }


    @Test
    public void whenSaveCar(){


        Car novo = new Car("mazda","3");
        Mockito.when(carRepository.save(novo)).thenReturn(novo);


        assertThat(carService.save(novo)).isEqualTo(novo);


    }

}

