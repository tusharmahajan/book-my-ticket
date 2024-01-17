package com.lld.book.my.ticket.repository;

import com.lld.book.my.ticket.models.MovieShow;
import com.lld.book.my.ticket.models.MovieTheater;
import com.lld.book.my.ticket.models.Seat;

import java.util.*;

public class MovieTheaterRepository {

    private static final List<MovieTheater> movieTheaters = new ArrayList<>();
    private static final Map<String, List<Seat>> movieShowsSeatsMapping = new HashMap<>();

    private MovieTheaterRepository(){}

    public static void addMovieTheaterDetails(MovieTheater movieTheater){
        movieTheaters.add(movieTheater);
        for(MovieShow movieShow: movieTheater.getMovieShows()){
            movieShowsSeatsMapping.put(movieShow.getShowId(), movieShow.getCinemaHall().getSeats());
        }
    }

    public static List<MovieTheater> getAllMovieTheaters() {
        return movieTheaters;
    }

    public static List<Seat> getAvailableSeats(String showId) {
        List<Seat> seats = movieShowsSeatsMapping.get(showId);
        if(seats == null){
            throw new NullPointerException("Show id does not exist");
        }
        return seats;
    }

    public static boolean isSeatReserved(String showId, String seatNumber) {
        List<Seat> seats = movieShowsSeatsMapping.get(showId);
        if(seats == null){
            throw new NullPointerException("Show id does not exist");
        }
        Optional<Seat> seat = seats.stream().filter(seatParam -> seatParam.getNumber().equals(seatNumber)).findFirst();
        if(seat.isEmpty()){
            throw new NullPointerException(String.format("Seat number %s doesn't exist does not exist", seatNumber));
        }
        return seat.get().isReserved();
    }

    public static void markSeatAsReserved(String showId, String seatNumber){
        List<Seat> seats = movieShowsSeatsMapping.getOrDefault(showId, new ArrayList<>());
        for(Seat seat: seats){
            if(seat.getNumber().equals(seatNumber)){
                seat.setReserved(true);
            }
        }
    }

    public static void unmarkSeatAsReserved(String showId, String seatNumber) {
        List<Seat> seats = movieShowsSeatsMapping.getOrDefault(showId, new ArrayList<>());
        for(Seat seat: seats){
            if(seat.getNumber().equals(seatNumber)){
                seat.setReserved(false);
            }
        }
    }
}
