package com.lld.book.my.ticket.factory;

import com.lld.book.my.ticket.dtos.MovieFilterRequest;
import com.lld.book.my.ticket.models.filter.CityMovieFilter;
import com.lld.book.my.ticket.models.filter.MovieFilter;
import com.lld.book.my.ticket.models.filter.TheaterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieFilterFactory {

    private final Map<String, MovieFilter> movieFilters;

    @Autowired
    public MovieFilterFactory(Set<MovieFilter> movieFilterSet) {
        this.movieFilters = new HashMap<>();
        createMovieFilterMap(movieFilterSet);
    }

    private void createMovieFilterMap(Set<MovieFilter> movieFilterSet) {
        for(MovieFilter movieFilter: movieFilterSet){
            movieFilters.put(movieFilter.getClass().getSimpleName(), movieFilter);
        }
    }


    public List<MovieFilter> getFilters(MovieFilterRequest movieFilterRequest) {
        List<MovieFilter> movieFilterList = new ArrayList<>();

        if(movieFilterRequest.getCity() != null){
            movieFilterList.add(getByCity(movieFilterRequest.getCity()));
        }

        if(movieFilterRequest.getTheaterName() != null){
            movieFilterList.add(getByTheaterName(movieFilterRequest.getTheaterName()));
        }
        return movieFilterList;

    }

    private MovieFilter getByTheaterName(String theaterName) {
        TheaterFilter theaterFilter = (TheaterFilter) movieFilters.get("TheaterFilter");
        theaterFilter.setName(theaterName);
        return theaterFilter;
    }

    private MovieFilter getByCity(String city) {
        CityMovieFilter cityMovieFilter = (CityMovieFilter) movieFilters.get("CityMovieFilter");
        cityMovieFilter.setCity(city);
        return cityMovieFilter;
    }
}
