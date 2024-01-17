package com.lld.book.my.ticket.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BookingResponse {

    private final String bookingId;
    private final List<String> seatsReserved;
    private final String showId;

    public BookingResponse(List<String> seatsReserved, String showId) {
        this.bookingId = UUID.randomUUID().toString();
        this.seatsReserved = seatsReserved;
        this.showId = showId;
    }
}
