package com.lld.book.my.ticket.dtos;

import lombok.Data;

@Data
public class MovieFilterRequest {

    private String city;
    private String theaterName;
}
