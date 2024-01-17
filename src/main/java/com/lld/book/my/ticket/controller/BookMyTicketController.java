package com.lld.book.my.ticket.controller;

import com.lld.book.my.ticket.dtos.BookingRequest;
import com.lld.book.my.ticket.dtos.MovieFilterRequest;
import com.lld.book.my.ticket.dtos.SeatResponse;
import com.lld.book.my.ticket.factory.MovieFilterFactory;
import com.lld.book.my.ticket.models.*;
import com.lld.book.my.ticket.models.filter.MovieFilter;
import com.lld.book.my.ticket.service.BookingService;
import com.lld.book.my.ticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portal")
public class BookMyTicketController {

    @Autowired
    private MovieFilterFactory movieFilterFactory;

    @Autowired
    private MovieService movieService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add-movie-theaters")
    public ResponseEntity<String> addMovieShows(@RequestBody MovieTheater movieTheater){
        try {
            movieService.addMovieShows(movieTheater);
            return ResponseEntity.ok("Added movie show");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/movie-shows")
    public ResponseEntity<List<MovieShow>> getMovieShows(MovieFilterRequest movieFilterRequest){
       List<MovieFilter> movieFilters = movieFilterFactory.getFilters(movieFilterRequest);
       return ResponseEntity.ok(movieService.getMovieShows(movieFilters));
    }

    @GetMapping("/available-seats")
    public ResponseEntity<?> listAvailableSeats(@RequestParam String showId){
        try {
            return ResponseEntity.ok(bookingService.listAvailableSeats(showId));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/book-seats")
    public ResponseEntity<?> bookSeats(@RequestBody BookingRequest bookingRequest){
        try {
            return ResponseEntity.ok(bookingService.bookSeats(bookingRequest.getShowId(), bookingRequest.getSeatNumbers()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
