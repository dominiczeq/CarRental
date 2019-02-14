package pl.dejv.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dejv.carrental.entity.Car;
import pl.dejv.carrental.repository.CarRepository;

import java.util.List;

@RequestMapping("/car")
@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;


    @GetMapping("/add")
    public String addCar(Model model) {
        model.addAttribute("newCar", new Car());
        return "addCar";
    }

    @PostMapping("/add")
    public String addCar(Car car) {
        carRepository.save(car);
        return "home";

    }

    @GetMapping("/list")
    public String carList(Model m) {
        List<Car> cars = carRepository.findAll();
        m.addAttribute("allCars", cars);
        return "carList";
    }
}
