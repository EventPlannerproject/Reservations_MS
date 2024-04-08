package com.eventplanner.reservationms.dto.mappers;


import com.eventplanner.reservationms.dto.ReservationDTO;
import com.eventplanner.reservationms.models.Reservation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ReservationMapper {

    private final ModelMapper modelMapper;

    public ReservationDTO convertReservationToDto(Reservation reservation){
        return  modelMapper.map(reservation, ReservationDTO.class);
    }
    public List<ReservationDTO> convertReservationsToListOfDto(List<Reservation> reservationList){
        return  reservationList.stream().map(res->modelMapper.map(res, ReservationDTO.class)).collect(Collectors.toList());
    }
}
