package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car("Toyota", "Red", 10);
    }

    @Test
    void testCarConstructorWithParameters() {
        assertThat(car.getCarName()).isEqualTo("Toyota");
        assertThat(car.getCarColor()).isEqualTo("Red");
        assertThat(car.getCarQuantity()).isEqualTo(10);
        assertThat(car.getCarId()).isNotNull();
    }

    @Test
    void testCarDefaultConstructor() {
        Car newCar = new Car();
        assertThat(newCar.getCarId()).isNotNull();
    }

    @Test
    void testSetCarName() {
        car.setCarName("Honda");
        assertThat(car.getCarName()).isEqualTo("Honda");
    }

    @Test
    void testSetCarColor() {
        car.setCarColor("Blue");
        assertThat(car.getCarColor()).isEqualTo("Blue");
    }

    @Test
    void testSetCarQuantity() {
        car.setCarQuantity(20);
        assertThat(car.getCarQuantity()).isEqualTo(20);
    }
}
