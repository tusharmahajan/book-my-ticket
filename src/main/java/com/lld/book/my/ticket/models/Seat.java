package com.lld.book.my.ticket.models;

import lombok.Data;

@Data
public class Seat {

    private final String number;
    private final SeatType seatType;
    private boolean isReserved;

    public Seat(String number, SeatType seatType) {
        this.number = number;
        this.seatType = seatType;
        this.isReserved = false;
    }
}
