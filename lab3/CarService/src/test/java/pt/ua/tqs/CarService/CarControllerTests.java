package pt.ua.tqs.CarService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;


import pt.ua.tqs.CarService.Controller.CarController;
import pt.ua.tqs.CarService.DAO.Car;
import pt.ua.tqs.CarService.Service.CarManagerService;

@WebMvcTest(CarController.class)
public class CarControllerTests {
    
    
    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    @MockBean
    private CarManagerService service;



    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car volvo = new Car("volvo", "850TI");

        when( service.save(Mockito.any()) ).thenReturn(volvo);

        mvc.perform(
                post("/api/car").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(volvo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("850TI")))
                .andExpect(jsonPath("$.maker",is("volvo")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    void whenGetAllCars_thenReturnMultiple() throws Exception{
        Car bmw = new Car("bmw", "e36");
        Car volvo = new Car("volvo", "850 T-5R");
        Car merc = new Car("mercedes", "e320");

        List<Car> allCars = Arrays.asList(bmw, volvo, merc);

        when( service.getAllCars()).thenReturn(allCars);



        mvc.perform(
                get("/api/car").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(bmw.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(volvo.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(merc.getMaker())));
    }

    @Test
    void whenGetCar_returnCar() throws Exception{
        Car volvo = new Car("volvo", "850 T-5R");


        when( service.getCarDetails(volvo.getCardId())).thenReturn(volvo);

        mvc.perform(
            get("/api/car/"+volvo.getCardId()+"").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(volvo.getMaker())))
            .andExpect(jsonPath("$.model", is(volvo.getModel())));

    }

}
