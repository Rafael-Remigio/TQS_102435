package pt.ua.tqs.openair;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.tqs.openair.data.CacheRepository;
import pt.ua.tqs.openair.data.model.Location;

import static org.assertj.core.api.Assertions.assertThat;


//@DataJpaTest
class CacheRepositoryTests {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CacheRepository cacheRepository;

    //@Test
    void whenfindByID_thenReturnCar() {
        // arrange a new employee and insert into db
        Location paris = new Location("Paris", null, null);
        entityManager.persistAndFlush(paris); //ensure data is persisted at this point

        // test the query method of interest
        Optional<Location> found = cacheRepository.findById(paris.getLocation());
        assertThat( found.get() ).isEqualTo(paris);
    }


}


