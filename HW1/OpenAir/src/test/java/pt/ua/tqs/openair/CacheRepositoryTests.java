package pt.ua.tqs.openair;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;

import pt.ua.tqs.openair.data.CacheRepository;
import pt.ua.tqs.openair.data.model.Location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@DataJpaTest
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class CacheRepositoryTests {

    // get a test-friendly Entity Manager
	@Autowired 
    RedisOperations<Object, Object> operations;
    
    @Autowired
    private CacheRepository cacheRepository;


    @BeforeEach
	void setUp() {

		operations.execute((RedisConnection connection) -> {
			cacheRepository.deleteAll();
            return "OK";
		});
	}

    @Test
    @DisplayName("Test findById and test TTL")
    void whenfindByID_thenReturnLocation() {
        // arrange a new location and insert into db
        Location paris = new Location("Paris", null, null);
        cacheRepository.save(paris); //ensure data is persisted at this point

        // test the query method of interest
        Optional<Location> found = cacheRepository.findById(paris.getLocation());
        assertThat( found.get() ).isEqualTo(paris);

        try {
            Thread.sleep(7000);
        } catch (Exception e) {
        }
        
        found = cacheRepository.findById(paris.getLocation());
        assertFalse((found.isPresent()));


    }

    


}


