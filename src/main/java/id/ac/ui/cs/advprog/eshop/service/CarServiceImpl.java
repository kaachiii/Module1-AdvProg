package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarServiceImpl implements ReadOnlyCarService, CarManagementService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car){
        // TODO Auto-generated method stub
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll(){
        return carRepository.findAll(); // Tidak perlu lagi mengubah dari Iterator ke List
    }

    @Override
    public Car findById(String carId){
        return carRepository.findById(carId);
    }

    @Override
    public Car update(String carId, Car car){
        // TODO Auto-generated method stub
        return carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId){
        // TODO Auto-generated method stub
        carRepository.delete(carId);
    }
}
