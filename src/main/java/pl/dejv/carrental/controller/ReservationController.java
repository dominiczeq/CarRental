package pl.dejv.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dejv.carrental.configuration.SessionManager;
import pl.dejv.carrental.entity.Car;
import pl.dejv.carrental.entity.Client;
import pl.dejv.carrental.entity.Office;
import pl.dejv.carrental.entity.Reservation;
import pl.dejv.carrental.repository.CarRepository;
import pl.dejv.carrental.repository.OfficeRepository;
import pl.dejv.carrental.repository.ReservationRepository;
import pl.dejv.carrental.service.EmailSender;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/reservation")
@Controller
public class ReservationController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmailSender emailSender;

    @GetMapping("/add")
    public String reservation(Model m) {
        Reservation reservation = new Reservation();
        m.addAttribute("newReservation", reservation);
        return "addReservation";
    }

    @PostMapping("/add")
    public String reservation(@ModelAttribute("newReservation") Reservation reservation, Model m) {
        LocalDate pickupDate = reservation.getPickupDate();
        LocalDate returnDate = reservation.getReturnDate();
        Office pickupLocation = reservation.getPickupLocation();
        Long locationId = pickupLocation.getId();

        List<Reservation> listReservationFromForm = reservationRepository.findAllCarsReservationByDate(pickupDate, returnDate);
        List<Car> carFromReservation = new ArrayList<>();

        for (Reservation r : listReservationFromForm) {
            carFromReservation.add(r.getCar());
        }

        List<Car> availableCarsList = carRepository.findCarByOfficeId(locationId);
        availableCarsList.removeAll(carFromReservation);

        m.addAttribute("newReservation", reservation);
        m.addAttribute("cars", availableCarsList);

        HttpSession s = SessionManager.session();
        s.setAttribute("completeReservation", reservation);
        return "addReservation2";
    }

    @PostMapping("/add2")
    public String reservation2(@ModelAttribute("newReservation") Reservation reservation, Model m) {

        HttpSession s = SessionManager.session();
        Reservation result = (Reservation) s.getAttribute("completeReservation");
        result.setReturnLocation(reservation.getReturnLocation());
        result.setCar(reservation.getCar());
        result.setOrderedCar(true);

        Client client = new Client();
        m.addAttribute("client", client);
        m.addAttribute("newReservation", result);

        return "addReservation3";
    }

    @PostMapping("/noCars")
    public String noCars(@ModelAttribute("newReservation") Reservation reservation) {
        HttpSession s = SessionManager.session();
        Reservation result = (Reservation) s.getAttribute("completeReservation");
        result.setEmail(reservation.getEmail());
        result.setOrderedCar(false);
        s.invalidate();

        this.reservationRepository.save(result);
        return "redirect:/home";
    }

    @PostMapping("/add3")
    public String reservation3 (@ModelAttribute("newReservaiton") Reservation reservation, Client client) {
        HttpSession s = SessionManager.session();
        Reservation result = (Reservation) s.getAttribute("completeReservation");
        result.setClient(client);
        s.invalidate();

        this.reservationRepository.save(result);
        emailSender.reservationConfirmation(result);
        return "redirect:/home";
    }

    @ModelAttribute("allOffices")
    public List<Office> getAllOffices() {
        return this.officeRepository.findAll();
    }

    @ModelAttribute("allCars")
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @GetMapping("/reservationList")
    public String reservationList(Model m) {

        m.addAttribute("allReservations", this.reservationRepository.findByOrderedCarOrderByPickupDate(true));
        return "reservationList";
    }

    @GetMapping("/noOrderedList")
    public String noOrderedList(Model m) {

        m.addAttribute("noOrdered", this.reservationRepository.findByOrderedCarOrderByPickupDate(false));
       return "noOrderedList";
    }

}
