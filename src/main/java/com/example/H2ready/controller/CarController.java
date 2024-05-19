package com.example.H2ready.controller;

import com.example.H2ready.entity.Car;

import com.example.H2ready.repository.CarRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cars/")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Car getCarById(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            return carRepository.findById(id).get();
        }
        return new Car();
    }

    @PutMapping(path = "/{id}")
    public Car upodateTypeCarById(@PathVariable Long id, @RequestParam String type) {
        if (carRepository.existsById(id)) {
            carRepository.findById(id).get().setType(type);
            return carRepository.findById(id).get();
        }
        return new Car();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCarByID(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping
    public ResponseEntity deleteAllCars() {
        if (carRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        carRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
