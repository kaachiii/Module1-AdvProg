package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarManagementService;
import id.ac.ui.cs.advprog.eshop.service.ReadOnlyCarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private ReadOnlyCarService readOnlyCarService;

    @Mock
    private CarManagementService carManagementService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        assertThat(viewName).isEqualTo("createCar");
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car("Toyota", "Red", 10);
        String viewName = carController.createCarPost(car);

        assertThat(viewName).isEqualTo("redirect:listCar");
        verify(carManagementService, times(1)).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> cars = List.of(new Car("Toyota", "Red", 10), new Car("Honda", "Blue", 5));
        when(readOnlyCarService.findAll()).thenReturn(cars);

        String viewName = carController.carListPage(model);

        assertThat(viewName).isEqualTo("carList");
        verify(model, times(1)).addAttribute("cars", cars);
    }

    @Test
    void testEditCarPage() {
        Car car = new Car("Toyota", "Red", 10);
        when(readOnlyCarService.findById(car.getCarId())).thenReturn(car);

        String viewName = carController.editCarPage(car.getCarId(), model);

        assertThat(viewName).isEqualTo("editCar");
        verify(model, times(1)).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car("Toyota", "Red", 10);
        String viewName = carController.editCarPost(car);

        assertThat(viewName).isEqualTo("redirect:listCar");
        verify(carManagementService, times(1)).update(car.getCarId(), car);
    }

    @Test
    void testDeleteCar() {
        String carId = "test-id";
        String viewName = carController.deleteCar(carId);

        assertThat(viewName).isEqualTo("redirect:listCar");
        verify(carManagementService, times(1)).deleteCarById(carId);
    }
}