package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Car car = new Car("Toyota", "Red", 10);
        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);

        assertThat(createdCar).isEqualTo(car);
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAll() {
        Car car1 = new Car("Toyota", "Red", 10);
        Car car2 = new Car("Honda", "Blue", 5);
        when(carRepository.findAll()).thenReturn(List.of(car1, car2));

        List<Car> cars = carService.findAll();

        assertThat(cars).containsExactly(car1, car2);
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Car car = new Car("Toyota", "Red", 10);
        String carId = car.getCarId();
        when(carRepository.findById(carId)).thenReturn(car);

        Car foundCar = carService.findById(carId);

        assertThat(foundCar).isEqualTo(car);
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    void testUpdate() {
        Car car = new Car("Toyota", "Red", 10);
        String carId = car.getCarId();
        Car updatedCar = new Car("Honda", "Blue", 5);
        when(carRepository.update(carId, updatedCar)).thenReturn(updatedCar);

        Car result = carService.update(carId, updatedCar);

        assertThat(result).isEqualTo(updatedCar);
        verify(carRepository, times(1)).update(carId, updatedCar);
    }

    @Test
    void testUpdateNonExistentCar() {
        Car updatedCar = new Car("Honda", "Blue", 5);
        when(carRepository.update("invalid-id", updatedCar)).thenReturn(null);

        Car result = carService.update("invalid-id", updatedCar);

        assertThat(result).isNull();
        verify(carRepository, times(1)).update("invalid-id", updatedCar);
    }

    @Test
    void testDeleteCarById() {
        String carId = "valid-id";
        doNothing().when(carRepository).delete(carId);

        carService.deleteCarById(carId);

        verify(carRepository, times(1)).delete(carId);
    }
}

