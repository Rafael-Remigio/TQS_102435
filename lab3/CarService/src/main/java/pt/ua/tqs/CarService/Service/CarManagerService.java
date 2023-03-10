package pt.ua.tqs.CarService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pt.ua.tqs.CarService.DAO.Car;
import pt.ua.tqs.CarService.DAO.CarRepository;

public class CarManagerService {


    @Autowired
    CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car getCarDetails(long id) {
        return carRepository.findByCardId(id);
    }
    
}
