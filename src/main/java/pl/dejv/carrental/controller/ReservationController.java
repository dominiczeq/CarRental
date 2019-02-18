package pl.dejv.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dejv.carrental.entity.Reservation;

@RequestMapping("/reservation")
@Controller
public class ReservationController {

    @GetMapping("/add")
    public String reservation(Model m) {
        Reservation reservation = new Reservation();
        m.addAttribute("newReservation", reservation);
        return "addReservation";
    }

}
