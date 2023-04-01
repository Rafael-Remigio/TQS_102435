package pt.ua.tqs.openair.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.ua.tqs.openair.data.model.Location;


@Repository
public interface CacheRepository extends CrudRepository<Location, String> {}
