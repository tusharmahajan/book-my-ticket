package com.lld.book.my.ticket.service;

import com.lld.book.my.ticket.dtos.SeatResponse;
import com.lld.book.my.ticket.models.BookingResponse;
import com.lld.book.my.ticket.models.Seat;
import com.lld.book.my.ticket.repository.MovieTheaterRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    public SeatResponse listAvailableSeats(String showId) {
        List<Seat> seats = MovieTheaterRepository.getAvailableSeats(showId);
        SeatResponse seatResponse = new SeatResponse();

        for(Seat seat: seats){
            if(!seat.isReserved()) seatResponse.addSeat(seat.getNumber());
        }
        return seatResponse;
    }

    public synchronized BookingResponse bookSeats(String showId, List<String> seatNumbers) {
        List<String> successfullyBookedSeats = new ArrayList<>();
        boolean allSeatsBooked = true;

        try {
            for(String seatNumber: seatNumbers){
                if (!MovieTheaterRepository.isSeatReserved(showId, seatNumber)) {
                    MovieTheaterRepository.markSeatAsReserved(showId, seatNumber);
                    successfullyBookedSeats.add(seatNumber);
                }
                else {
                    allSeatsBooked = false;
                    break;
                }
            }
            if (allSeatsBooked) {
                return new BookingResponse(successfullyBookedSeats, showId);
            } else {
                unmarkSeatsReserved(showId, successfullyBookedSeats);
                throw new RuntimeException("Booking cannot be made as all seats are not available!!");
            }
        }
        catch (Exception e){
            unmarkSeatsReserved(showId, successfullyBookedSeats);
            throw new RuntimeException(e.getMessage());
        }
    }

    private void unmarkSeatsReserved(String showId, List<String> successfullyBookedSeats) {
        for (String seatNumber : successfullyBookedSeats) {
            MovieTheaterRepository.unmarkSeatAsReserved(showId, seatNumber);
        }
    }
}
