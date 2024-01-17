package com.lld.book.my.ticket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieShow {

   private String showId = UUID.randomUUID().toString();
   private Movie movie;
   private Date startTime;
   private CinemaHall cinemaHall;

}

