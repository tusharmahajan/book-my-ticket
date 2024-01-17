package com.lld.book.my.ticket.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {

    private final String name;
    private final String description;
    private final String genre;
    private final int durationInMins;
}
