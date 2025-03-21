package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private List<Car> carData = new ArrayList<>();

    public Car create(Car car){
        // Repository tidak seharusnya bertanggung jawab atas pembuatan ID
        carData.add(car);
        return car;
    }

    public List<Car> findAll(){
        return new ArrayList<>(carData); // Mengembalikan salinan untuk menghindari manipulasi langsung
    }

    public Car findById(String id){
        for (Car car : carData) {
            if (car.getCarId().equals(id)){
                return car;
            }
        }
        return null;
    }

    public Car update(String id, Car updatedCar){
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.getCarId().equals(id)){
                // Update the existing car with the new information
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        return null; // handle the case where the car is not found
    }

    public void delete(String id){
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
