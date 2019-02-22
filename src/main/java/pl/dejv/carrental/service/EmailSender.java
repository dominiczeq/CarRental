package pl.dejv.carrental.service;

import pl.dejv.carrental.entity.Reservation;

public interface EmailSender {
    void reservationConfirmation(Reservation reservation);



}
