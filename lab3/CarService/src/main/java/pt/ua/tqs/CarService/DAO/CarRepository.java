package pt.ua.tqs.CarService.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository  extends JpaRepository<Car, Integer>{

    public Car findById(Long cardId);

    public List<Car> findAll();
    
}
