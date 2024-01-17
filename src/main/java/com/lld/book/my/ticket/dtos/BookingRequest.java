package com.lld.book.my.ticket.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    private String showId;
    private List<String> seatNumbers;
}
