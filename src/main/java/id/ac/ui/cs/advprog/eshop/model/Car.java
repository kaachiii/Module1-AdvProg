package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Car {
    private String carId;
    private String carName;
    private String carColor;
    private int carQuantity;

    // Konstruktor dengan ID otomatis
    public Car() {
        this.carId = UUID.randomUUID().toString();
    }

    public Car(String carName, String carColor, int carQuantity) {
        this.carId = UUID.randomUUID().toString();
        this.setCarName(carName);
        this.setCarColor(carColor);
        this.setCarQuantity(carQuantity);
    }
}
