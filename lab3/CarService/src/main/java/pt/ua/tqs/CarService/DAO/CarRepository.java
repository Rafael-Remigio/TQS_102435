package pt.ua.tqs.CarService.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository  extends JpaRepository<Car, Integer>{

    public Car findByCardId(Long cardId);

    public List<Car> findAll();
    
}
