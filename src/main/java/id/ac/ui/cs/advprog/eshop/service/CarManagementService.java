package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarManagementService {
    Car create(Car car);
    Car update(String carId, Car car); // Mengembalikan mobil yang diperbarui
    void deleteCarById(String carId);
}
