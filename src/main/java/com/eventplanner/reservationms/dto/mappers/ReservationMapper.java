package com.eventplanner.reservationms.dto.mappers;


import com.eventplanner.reservationms.dto.ReservationDTO;
import com.eventplanner.reservationms.models.Reservation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReservationMapper {

    private final ModelMapper modelMapper;

    public ReservationDTO convertReservationToDto(Reservation reservation){
        return  modelMapper.map(reservation, ReservationDTO.class);
    }
}
