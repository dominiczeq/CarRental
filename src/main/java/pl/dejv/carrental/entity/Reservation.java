package pl.dejv.carrental.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickupDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "pickupLocation_id")
    private Office pickupLocation;

    @ManyToOne
    @JoinColumn(name = "returnLocation_id")
    private Office returnLocation;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "client_id")
    private Client client;

    private String email;

    private boolean orderedCar;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOrderedCar() {
        return orderedCar;
    }

    public void setOrderedCar(boolean orderedCar) {
        this.orderedCar = orderedCar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Office getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Office pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Office getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(Office returnLocation) {
        this.returnLocation = returnLocation;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
