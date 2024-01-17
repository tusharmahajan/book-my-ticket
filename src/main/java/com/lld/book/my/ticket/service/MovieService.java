package com.lld.book.my.ticket.service;

import com.lld.book.my.ticket.models.CinemaHall;
import com.lld.book.my.ticket.models.MovieTheater;
import com.lld.book.my.ticket.models.filter.MovieFilter;
import com.lld.book.my.ticket.models.MovieShow;
import com.lld.book.my.ticket.repository.MovieTheaterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    public void addMovieShows(MovieTheater movieTheater) {
        String city = movieTheater.getCity();
        String theaterName = movieTheater.getName();

        if(city == null){
            throw new NullPointerException("City name cannot be null");
        }

        if(theaterName == null){
            throw new NullPointerException("Theater name cannot be null");
        }

        MovieTheaterRepository.addMovieTheaterDetails(movieTheater);
    }

    public List<MovieShow> getMovieShows(List<MovieFilter> movieFilters) {
        List<MovieTheater> movieTheaters = MovieTheaterRepository.getAllMovieTheaters();

        List<MovieShow> movieShows = new ArrayList<>();

        for(MovieFilter movieFilter: movieFilters){
            List<MovieTheater> filteredList = new ArrayList<>();
            for(MovieTheater movieTheater: movieTheaters){
                if(movieFilter.filter(movieTheater)){
                    filteredList.add(movieTheater);
                }
            }
            movieTheaters = filteredList;
        }

        for(MovieTheater movieTheater: movieTheaters){
            movieShows.addAll(movieTheater.getMovieShows());
        }
        return movieShows;
    }

}
