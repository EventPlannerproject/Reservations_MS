package com.eventplanner.reservationms.services.servicesImpl;

import com.eventplanner.reservationms.clients.EventClient;
import com.eventplanner.reservationms.clients.rabbitmq.ReservationProducer;
import com.eventplanner.reservationms.clients.responses.EventResponse;
import com.eventplanner.reservationms.exceptions.BadRequestException;
import com.eventplanner.reservationms.models.Reservation;
import com.eventplanner.reservationms.models.Status;
import com.eventplanner.reservationms.payload.ApiError;
import com.eventplanner.reservationms.payload.ErrorResponse;
import com.eventplanner.reservationms.payload.emailsenderconfig.EmailService;
import com.eventplanner.reservationms.payload.emailsenderconfig.EmailsDetails;
import com.eventplanner.reservationms.repositories.ReservationRepository;
import com.eventplanner.reservationms.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    private final EventClient eventClient;
    private final ReservationProducer reservationProducer;

    private EmailService emailService;


    /** createReservationMethod
     *  contains call eventMS through feign client to get available Capacity of an event
     *  And a sending message for event MS to inform about new reservation saved and transfer
     *  numbers of guests so eventMS can update numbersOfAvailable Guests of the event
     */
    @Override
    public Reservation createReservation(Reservation reservation) {

        //var availableCapacity = eventClient.findAvailableCapacity(reservation.getIdEvent());
        EventResponse eventResponse = eventClient.retrieveEvent(reservation.getIdEvent()).get();
        if(eventResponse==null){
            //throw exception
            ErrorResponse errorResponse = new ErrorResponse(ApiError.EVENT_NOT_FOUND.getErrorCode(),ApiError.EVENT_NOT_FOUND.getMessage(), ApiError.EVENT_NOT_FOUND.getDescription());
            throw  new BadRequestException(errorResponse);
        }
        Integer availableCapacity = eventResponse.getNumberOfAttendees()-eventResponse.getNumbersOfParticipants();
        if(availableCapacity<=0 || availableCapacity==null){
            ErrorResponse errorResponse = new ErrorResponse(ApiError.EVENT_FULL.getErrorCode(),ApiError.EVENT_FULL.getMessage(), ApiError.EVENT_FULL.getDescription());
            throw  new BadRequestException(errorResponse);
        }
        reservation.setCreationDate(LocalDate.now());
        Reservation reservationSaved= reservationRepository.save(reservation);
        reservationProducer.sendMessage(reservation.getGuestsNumbers(),reservation.getIdEvent());
        //Send email for user to inform that
        if(reservation.getEmailUserNotif()!=null){
            emailService.sendEmail(EmailsDetails.builder()
                    .messageBody("We are thrilled to inform you that your reservation for the upcoming event has been successfully processed!" +
                            "* Reservation Date :  "+reservation.getReservationDate()+"/n"+
                            "Event : "+eventResponse.getTitle()+"/n"+
                            "See you Soon ")
                    .recipient(reservation.getEmailUserNotif())
                    .subject("Reservation for "+eventResponse.getTitle())
                    .build());
        }
        return  reservationSaved;
    }

    @Override
    public Reservation getReservationbyID(Long idReservation) {
        Reservation reservation=reservationRepository.findById(idReservation).orElseThrow(  ()->
           new BadRequestException(new ErrorResponse(ApiError.RESSOUCE_NOT_FOUND.getErrorCode(),
                   ApiError.RESSOUCE_NOT_FOUND.getMessage(), ApiError.RESSOUCE_NOT_FOUND.getDescription()))
        );
        return reservation;
    }

    @Override
    public Reservation updateReservationDetails(Long idReservation,Reservation requestRes) {
        Reservation existingRes=reservationRepository.findById(idReservation).orElseThrow(  ()->
                new BadRequestException(new ErrorResponse(ApiError.RESSOUCE_NOT_FOUND.getErrorCode(),
                        ApiError.RESSOUCE_NOT_FOUND.getMessage(), ApiError.RESSOUCE_NOT_FOUND.getDescription()))
        );
        if(requestRes.getPaymentInformation()!=null){
            existingRes.setPaymentInformation(requestRes.getPaymentInformation());
        }
        if(requestRes.getGuestsNumbers()!=null){
            existingRes.setGuestsNumbers(requestRes.getGuestsNumbers());
        }
        if(requestRes.getReservationDate()!=null){
              existingRes.setReservationDate(requestRes.getReservationDate());
        }
        if(requestRes.getReservationStatus()!=null){
            existingRes.setReservationStatus(requestRes.getReservationStatus());
        }
        existingRes.setModificationDate(LocalDate.now());
        return reservationRepository.save(existingRes);
    }

    @Override
    public Reservation cancelReservation(Long idReservation) {
        Reservation reservationToCancel=reservationRepository.findById(idReservation).orElseThrow(  ()->
                new BadRequestException(new ErrorResponse(ApiError.RESSOUCE_NOT_FOUND.getErrorCode(),
                        ApiError.RESSOUCE_NOT_FOUND.getMessage(), ApiError.RESSOUCE_NOT_FOUND.getDescription()))
        );
        reservationToCancel.setReservationStatus(Status.CANCELED);
        return reservationRepository.save(reservationToCancel);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getUserHistoryReservations(Long userId) {
        return reservationRepository.findReservationsByIdUser(userId);
    }

    @Override
    public List<Reservation> getEventReservations(Long eventId) {
        return reservationRepository.findReservationsByIdEvent(eventId);
    }

    @Override
    public void deleteReservationById(Long idReservation) {
       reservationRepository.findById(idReservation).orElseThrow(  ()->
                new BadRequestException(new ErrorResponse(ApiError.RESSOUCE_NOT_FOUND.getErrorCode(),
                        ApiError.RESSOUCE_NOT_FOUND.getMessage(), ApiError.RESSOUCE_NOT_FOUND.getDescription()))
        );
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Integer countNumbersEventReservations(Long eventId) {
        return reservationRepository.findReservationsByIdEvent(eventId).size();
    }

    @Override
    public Reservation updateReservationStatus(Status status,Long idReservation) {
      Reservation reservation=  reservationRepository.findById(idReservation).orElseThrow(  ()->
                new BadRequestException(new ErrorResponse(ApiError.RESSOUCE_NOT_FOUND.getErrorCode(),
                        ApiError.RESSOUCE_NOT_FOUND.getMessage(), ApiError.RESSOUCE_NOT_FOUND.getDescription()))
        );
      reservation.setReservationStatus(status);
      return reservationRepository.save(reservation);
    }
}
