package com.budah.carmanager.services;


import com.budah.carmanager.models.Car;
import com.budah.carmanager.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService {

    @Autowired
    private CarRepository carRepository;

    /*
    * return all items as List<Items>
    */
    public List<Car> listAll(String keyword) {
        if (keyword != null && keyword.length() > 1) {
            return carRepository.findByKeyword(keyword);
            }
        return carRepository.findAll();
    }

    /*
     * create one Item.
     */
    public void create(Car car) {
        carRepository.saveAndFlush(car);
    }

    /*
     * get one Item by id. Returns empty item if id doesn't exist.
     */
    public Car get(long id) {
        if (carRepository.findById(id).isPresent()) {
            return carRepository.findById(id).get();
        } else {
            return new Car();
        }
    }

    /*
     * delete on Item by id.
     */
    public void delete(long id) {
        carRepository.deleteById(id);
    }

}
