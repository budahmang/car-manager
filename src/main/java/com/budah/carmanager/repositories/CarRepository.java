package com.budah.carmanager.repositories;

import com.budah.carmanager.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM cars_table WHERE * LIKE %?1%", nativeQuery = true)
    public List<Car> findByKeyword(String keyword);

}