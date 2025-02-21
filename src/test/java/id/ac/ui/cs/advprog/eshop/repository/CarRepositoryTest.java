package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarRepositoryTest {
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreate() {
        Car car = new Car("Toyota", "Red", 10);
        Car createdCar = carRepository.create(car);

        assertThat(createdCar).isEqualTo(car);
        assertThat(carRepository.findAll()).containsExactly(car);
    }

    @Test
    void testFindAll() {
        Car car1 = new Car("Toyota", "Red", 10);
        Car car2 = new Car("Honda", "Blue", 5);
        carRepository.create(car1);
        carRepository.create(car2);

        List<Car> cars = carRepository.findAll();
        assertThat(cars).containsExactly(car1, car2);
    }

    @Test
    void testFindById() {
        Car car = new Car("Toyota", "Red", 10);
        carRepository.create(car);
        String carId = car.getCarId();

        Car foundCar = carRepository.findById(carId);
        assertThat(foundCar).isEqualTo(car);

        Car notFoundCar = carRepository.findById("invalid-id");
        assertThat(notFoundCar).isNull();
    }

    @Test
    void testUpdate() {
        Car car = new Car("Toyota", "Red", 10);
        carRepository.create(car);
        String carId = car.getCarId();

        Car updatedCar = new Car("Honda", "Blue", 5);
        Car result = carRepository.update(carId, updatedCar);

        assertThat(result).isNotNull();
        assertThat(result.getCarId()).isEqualTo(carId);
        assertThat(result.getCarName()).isEqualTo("Honda");
        assertThat(result.getCarColor()).isEqualTo("Blue");
        assertThat(result.getCarQuantity()).isEqualTo(5);
    }

    @Test
    void testUpdateWithSameValues() {
        Car car = new Car("Toyota", "Red", 10);
        carRepository.create(car);
        String carId = car.getCarId();

        Car updatedCar = new Car("Toyota", "Red", 10);
        Car result = carRepository.update(carId, updatedCar);

        assertThat(result).isNotNull();
        assertThat(result.getCarId()).isEqualTo(carId);
        assertThat(result.getCarName()).isEqualTo("Toyota");
        assertThat(result.getCarColor()).isEqualTo("Red");
        assertThat(result.getCarQuantity()).isEqualTo(10);
    }

    @Test
    void testUpdateNonExistentCar() {
        Car updatedCar = new Car("Honda", "Blue", 5);
        Car result = carRepository.update("invalid-id", updatedCar);

        assertThat(result).isNull();
    }

    @Test
    void testDelete() {
        Car car = new Car("Toyota", "Red", 10);
        carRepository.create(car);
        String carId = car.getCarId();

        carRepository.delete(carId);
        assertThat(carRepository.findAll()).isEmpty();
    }

    @Test
    void testDeleteNonExistentCar() {
        carRepository.delete("invalid-id");
        assertThat(carRepository.findAll()).isEmpty();
    }
}
