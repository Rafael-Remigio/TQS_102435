package pt.ua.tqs.OpenAir.Data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ua.tqs.OpenAir.Data.Model.Location;


@Repository
public interface CacheRepository extends CrudRepository<Location, String> {}
