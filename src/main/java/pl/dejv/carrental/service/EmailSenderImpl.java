package pl.dejv.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.dejv.carrental.entity.Reservation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void reservationConfirmation(Reservation reservation) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        String message = "Rezerwacja nr: " + reservation.getId() +
                " została zapisana.\n\n Zapraszamy po odbiór samochodu dnia " + reservation.getPickupDate().toString() +
                " w lokalizacji " + reservation.getPickupLocation().getLocation() + "\n\n Pozdrawiamy, \nzespół CarRental";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(reservation.getClient().getEmail());
            helper.setReplyTo("dominicz.eq@wp.pl");
            helper.setFrom("dominicz.eq@wp.pl");
            helper.setSubject("Potwierdzenie rezerwacji");
            helper.setText(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);

    }

}
