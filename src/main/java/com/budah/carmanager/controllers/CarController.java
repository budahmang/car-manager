package com.budah.carmanager.controllers;


import com.budah.carmanager.models.Car;
import com.budah.carmanager.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping("/")
    public String showHome(Model model, @Param("keyword") String keyword) {
        List<Car> listCars = carService.listAll(keyword);
        model.addAttribute("listCars", listCars);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewCar(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "new_car";
    }

    @RequestMapping("/save")
    public String saveCar(@ModelAttribute("Car") Car car) {
        carService.create(car);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCar(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_car");
        Car car = carService.get(id);
        mav.addObject("car", car);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCar(@PathVariable(name = "id") long id) {
        carService.delete(id);
        return "redirect:/";
    }

}
