package pt.ua.tqs.CarService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.tqs.CarService.DAO.Car;
import pt.ua.tqs.CarService.DAO.CarDTO;
import pt.ua.tqs.CarService.Service.CarManagerService;

@RestController
@RequestMapping("/api")
public class CarController {
    

    private final CarManagerService carManagerService;

    /**
     * Using constructor Injection instead of @autowired
     * when using a constructor to set injected properties, you do not have to provide the autowire annotation
     * @param carManagerService
     */
    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }


    @GetMapping("/car")
    public List<Car> getAllCars() {
    return carManagerService.getAllCars();
    }

    @PostMapping("/car")
    public ResponseEntity<Car> CreateCar(@RequestBody CarDTO carDTO) {
        Car car = new Car(carDTO);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(carManagerService.save(car), status);
        }

    
    @GetMapping("/car/{id}")
    public ResponseEntity<Car>  getCardById(@PathVariable long id) {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(carManagerService.getCarDetails(id), status);
    }
    

}
