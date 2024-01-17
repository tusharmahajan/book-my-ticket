package com.lld.book.my.ticket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTheater {

    private String id = UUID.randomUUID().toString();
    private String city;
    private String name;
    private List<MovieShow> movieShows;

}
