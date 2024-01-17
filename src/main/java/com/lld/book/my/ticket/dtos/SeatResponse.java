package com.lld.book.my.ticket.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeatResponse {
    private List<String> seatNumbers;

    public SeatResponse(){
        this.seatNumbers = new ArrayList<>();
    }

    public void addSeat(String seatNumber){
        seatNumbers.add(seatNumber);
    }
}
